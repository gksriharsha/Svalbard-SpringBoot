package com.project.Svalbard.Controller;

import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Service.AnalyticsService;
import com.project.Svalbard.Service.AppService;
import com.project.Svalbard.Util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private AppService appService;

    @GetMapping(value = "/metric/{metric}/{clf}")
    public List<Float> getMetricList(@PathVariable(value = "metric") String metric,
                                     @PathVariable(value = "clf") String clf) throws Exception {
        CompletableFuture<List<Float>> accuracies = new CompletableFuture<>();
        CompletableFuture<List<Float>> fscores = new CompletableFuture<>();
        switch (metric) {
            case "Accuracy":
                accuracies = analyticsService.getaccuracies(clf);
                break;
            case "Fscore":
                fscores = analyticsService.getaccuracies(clf);
                break;
            default:
                return null;
        }

        return (List<Float>) CompletableFuture.anyOf(accuracies, fscores).get();
    }

    @GetMapping(value = "/operation/{operation}/{datasetMetric}")
    public Map<String, Double> performOperation(@PathVariable(value = "operation") String operation,
                                                @PathVariable(value = "datasetMetric") String datasetMetric) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        Map<String, Double> return_val = new HashMap<>();


        params.put("Classifier", "KNN");
        params.put("K", "29");
        params.put("Weights", "distance");
        params.put("Algorithm", "brute");

        List<Classification> classifications = appService.hpsearch(params);
        if (datasetMetric.equals("all")) {
            List<String> datasetPropertyList = new ArrayList<>();
            Map<String, CompletableFuture<Double>> futureList = new HashMap<>();
            HashMap<String, Object> dtsetHashMap;
            dtsetHashMap = (HashMap<String, Object>) Mapper.getfromObject(classifications.get(0).getdataset());
            new ArrayList<>(dtsetHashMap.keySet()).forEach(a -> {
                if (!(a.contains("id") || a.contains("fid") || a.contains("name") ||
                        a.contains("hibernateLazyInitializer"))) {
                    CompletableFuture<Double> value = analyticsService.computeMetric(classifications, a, operation);
                    futureList.put(a, value);
                }
            });
            futureList.entrySet().stream().map(e -> {
                try {
                    return_val.put(e.getKey(), e.getValue().get());
                } catch (Exception f) {
                    System.out.println(f.toString());
                }
                return return_val;
            }).collect(Collectors.toList());
        } else {
            CompletableFuture<Double> value = analyticsService.computeMetric(classifications, datasetMetric, operation);

            return_val.put(datasetMetric, value.get());
        }

        return return_val;
    }

//    @GetMapping(value = "/operationSync/{operation}/{datasetMetric}")
//    public List<Double> performOperationSync(@PathVariable(value = "operation") String operation,
//                                         @PathVariable(value = "datasetMetric") String datasetMetric) throws Exception {
//        HashMap<String, String> params = new HashMap<>();
//        List<Double> return_val = new ArrayList<>();
//
//        params.put("Classifier", "KNN");
//        params.put("K", "29");
//        params.put("Weights","distance");
//        params.put("Algorithm","brute");
//
//        List<Classification> classifications = appService.hpsearch(params);
//        if(datasetMetric.equals("all"))
//        {
//            List<String> datasetPropertyList = new ArrayList<>();
//            HashMap<String,Object> dtsetHashMap = new HashMap<String,Object>();
//
//            dtsetHashMap = (HashMap<String, Object>) Mapper.getfromObject(classifications.get(0).getdataset());
//            new ArrayList<>(dtsetHashMap.keySet()).forEach(a -> {
//                if(!(a.contains("id") || a.contains("fid") || a.contains("name") ||a.contains("hibernateLazyInitializer"))) {
//                    datasetPropertyList.add(a.toString());
//                    System.out.println(a);
//                    return_val.add(analyticsService.computeMetricSync(classifications, a, operation));
//                }
//            });
//        }
//
//        return return_val;
//    }
}
