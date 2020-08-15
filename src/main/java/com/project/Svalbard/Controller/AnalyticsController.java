package com.project.Svalbard.Controller;

import com.project.Svalbard.Dao.ClassificationRepository;
import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Model.db.Classification;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/analytics")
public class AnalyticsController {
    @Autowired
    private ClassificationRepository classificationRepository;

    @Autowired
    private DatasetRepository datasetRepository;

    @GetMapping(value = "/metric/{metric}")
    public List<Float> getMetricList(@PathVariable(value = "metric") String metric) {
        if (metric.equals("Accuracy")) {
            return classificationRepository.getacc("KNN");
        } else if (metric.equals("Fscore")) {
            return classificationRepository.getfscore("KNN");
        } else {

        }
        return null;
    }

    @GetMapping(value = "/operation/{operation}")
    public double performOperation(@PathVariable(value = "operation") String operation) {
        List<Classification> classifications = classificationRepository.getRandomrows();
        List<Double> resultMetric = new ArrayList<>();
        List<Double> datasetMetric = new ArrayList<>();

        double[] resultList = ArrayUtils.toPrimitive(resultMetric.toArray(new Double[resultMetric.size()]));
        double[] datasetMetricList = ArrayUtils.toPrimitive(datasetMetric.toArray(new Double[datasetMetric.size()]));

        classifications = classifications.stream().map(c -> {
            resultMetric.add((double) c.getAccuracy());
            datasetMetric.add(c.getdataset().getMajorityClassPercentage());
            //TODO this function is hardwired to Majority class percentage. This should be fetchable from hashmap.
            return c;
        }).collect(Collectors.toList());

        if (operation.equals("Correlation")) {
            return new PearsonsCorrelation().correlation(resultList, datasetMetricList);
        }
        if (operation.equals("Covariance")) //TODO add more operations
        {
            return new Covariance().covariance(resultList, datasetMetricList);
        }
        return 0.0;
    }
}
