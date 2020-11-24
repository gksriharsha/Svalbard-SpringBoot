package com.project.Svalbard.Controller;

import com.project.Svalbard.Aspects.CachingAspect;
import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Service.AnalyticsService;
import com.project.Svalbard.Service.AppService;
import com.project.Svalbard.Util.Mapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/analytics")
@Api(tags = "Analytics Endpoint", description = "All the APIs for analyzing the data are here")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private AppService appService;

    @GetMapping(value = "/metric/{metric}/{clf}")
    public ResponseEntity<List<Float>> getMetricList(@PathVariable(value = "metric") String metric,
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

        return ResponseEntity.ok((List<Float>) CompletableFuture.anyOf(accuracies, fscores).get());
    }

    @PostMapping(value = "/operation/{operation}/{datasetMetric}")
    public ResponseEntity<Map<String, Double>> performOperation(@PathVariable(value = "operation") String operation,
                                                @PathVariable(value = "datasetMetric") String datasetMetric,
                                                @RequestBody HashMap<String,String> params) throws Exception {
       
        Map<String, Double> return_val;

        List<Classification> classifications = appService.hpsearch(params);

        return_val = analyticsService.cachedMetric(classifications,datasetMetric, operation, new HashMap<>());

        return ResponseEntity.ok(return_val);
    }
}
