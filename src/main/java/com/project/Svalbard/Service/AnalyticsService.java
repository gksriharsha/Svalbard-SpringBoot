package com.project.Svalbard.Service;

import com.project.Svalbard.Dao.AnalyticsRepository;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Util.CustomStrCmp;
import com.project.Svalbard.Util.Mapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Async
    public CompletableFuture<List<Float>> getaccuracies(String clf) {
        List<Float> accuracies = analyticsRepository.getacc(clf);
        return CompletableFuture.completedFuture(accuracies);
    }

    @Async
    public CompletableFuture<List<Float>> getfscore(String clf) {
        List<Float> accuracies = analyticsRepository.getfscore(clf);
        return CompletableFuture.completedFuture(accuracies);
    }

    @Async
    public CompletableFuture<Double> computeMetric(List<Classification> classifications, String datasetMetric, String operation) {
        List<Double> resultMetricList = new ArrayList<>();
        List<Double> datasetMetricList = new ArrayList<>();

        classifications.stream().map(c -> {
            resultMetricList.add((double) c.getAccuracy());
            HashMap<String, Object> dtsetHashMap = new HashMap<String, Object>();
            dtsetHashMap = (HashMap<String, Object>) Mapper.getfromObject(c.getdataset());
            datasetMetricList.add(Double.valueOf(dtsetHashMap.get(
                    CustomStrCmp.getequivalent(datasetMetric, new ArrayList<>(dtsetHashMap.keySet()))).toString()));
            return c;
        }).collect(Collectors.toList());

        double[] resultList = ArrayUtils.toPrimitive(resultMetricList.toArray(new Double[resultMetricList.size()]));
        double[] datasetMetricList2 = ArrayUtils.toPrimitive(datasetMetricList.toArray(new Double[datasetMetricList.size()]));

        if (operation.equals("Correlation")) {//TODO add more operations
            return CompletableFuture.completedFuture(new PearsonsCorrelation().correlation(resultList, datasetMetricList2));
        }
        if (operation.equals("Covariance")) //TODO implement covariance properly.
        {
            return null;
        }
        return null;
    }


//    public Double computeMetricSync(List<Classification> classifications,String datasetMetric, String operation)
//    {
//        List<Double> resultMetricList = new ArrayList<>();
//        List<Double> datasetMetricList = new ArrayList<>();
//
//        classifications.stream().map(c -> {
//            resultMetricList.add((double) c.getAccuracy());
//            HashMap<String,Object> dtsetHashMap = new HashMap<String,Object>();
//            dtsetHashMap = (HashMap<String, Object>) Mapper.getfromObject(c.getdataset());
//            datasetMetricList.add( Double.valueOf(dtsetHashMap.get(datasetMetric).toString()));
//            return c;
//        }).collect(Collectors.toList());
//
//        double[] resultList = ArrayUtils.toPrimitive(resultMetricList.toArray(new Double[resultMetricList.size()]));
//        double[] datasetMetricList2 = ArrayUtils.toPrimitive(datasetMetricList.toArray(new Double[datasetMetricList.size()]));
//
//        if (operation.equals("Correlation")) {//TODO add more operations
//            return new PearsonsCorrelation().correlation(resultList, datasetMetricList2);
//        }
//        if (operation.equals("Covariance")) //TODO implement covariance properly.
//        {
//            return null;
//        }
//        return null;
//    }
}
