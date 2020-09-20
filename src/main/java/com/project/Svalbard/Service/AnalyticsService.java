package com.project.Svalbard.Service;

import com.project.Svalbard.Annotations.CacheValue;
import com.project.Svalbard.Annotations.LogExecutionTime;
import com.project.Svalbard.Dao.AnalyticsRepository;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Util.CustomStrCmp;
import com.project.Svalbard.Util.Mapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Async
    @LogExecutionTime
    public CompletableFuture<List<Float>> getaccuracies(String clf) {
        List<Float> accuracies = analyticsRepository.getacc(clf);
        return CompletableFuture.completedFuture(accuracies);
    }

    @Async
    @LogExecutionTime
    public CompletableFuture<List<Float>> getfscore(String clf) {
        List<Float> accuracies = analyticsRepository.getfscore(clf);
        return CompletableFuture.completedFuture(accuracies);
    }

    @CacheValue
    public Map<String,Double> cachedMetric(List<Classification> classifications, String datasetMetric,
                                           String operation,Map<String,Double> cachedValues)
    {
        Map<String, Double> return_val = new HashMap<>();
        List<String> datasetColumns = new ArrayList<>(Mapper.getfromObject(classifications.get(0).getdataset()).keySet());
        if (datasetMetric.equals("all"))
        {
            Map<String, CompletableFuture<Double>> futureList = new HashMap<>();
            datasetColumns.forEach(column ->
            {
                if(cachedValues.containsKey(column))
                {
                    return_val.put(column, cachedValues.get(column));
                    return;
                }
                if (!(column.contains("id") || column.contains("fid") || column.contains("name") ||
                        column.contains("hibernateLazyInitializer")))
                {
                    CompletableFuture<Double> value = computeMetric(classifications, column, operation);
                    futureList.put(column, value);
                }
            });
            futureList.entrySet().forEach(e -> {
                try {
                    return_val.put(e.getKey(), e.getValue().get());
                } catch (Exception f) {
                    f.printStackTrace();
                }
            });
        }
        else {
            CompletableFuture<Double> value = computeMetric(classifications, datasetMetric, operation);
            try{
                return_val.put(datasetMetric, value.get());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return return_val;
    }

    @Async
    public CompletableFuture<Double> computeMetric(List<Classification> classifications, String datasetMetric, String operation) {
        List<Double> resultMetricList = new ArrayList<>();
        List<Double> datasetMetricList = new ArrayList<>();

        Iterator<Classification> itr_classification = classifications.iterator();
        HashMap<String, Object> dtsetHashMap;
        while(itr_classification.hasNext())
        {
            Classification tmp = itr_classification.next();
            resultMetricList.add((double) tmp.getAccuracy());
            dtsetHashMap = (HashMap<String, Object>) Mapper.getfromObject(tmp.getdataset());
            datasetMetricList.add(Double.valueOf(dtsetHashMap.get(
                    CustomStrCmp.getequivalent(datasetMetric, new ArrayList<>(dtsetHashMap.keySet()))).toString()));
        }
        double[] resultList = ArrayUtils.toPrimitive(resultMetricList.toArray(new Double[resultMetricList.size()]));
        double[] datasetMetricList2 = ArrayUtils.toPrimitive(datasetMetricList.toArray(new Double[datasetMetricList.size()]));

        if (operation.equals("Correlation")) {//TODO add more operations
            if (Collections.max(datasetMetricList).equals(Collections.min(datasetMetricList))) {
                return CompletableFuture.completedFuture(0.00);
            }
            return CompletableFuture.completedFuture(new PearsonsCorrelation().correlation(resultList, datasetMetricList2));
        }
        if (operation.equals("Covariance")) //TODO implement covariance properly.
        {
            return null;
        }
        return null;
    }
}
