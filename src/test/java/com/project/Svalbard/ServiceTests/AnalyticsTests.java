package com.project.Svalbard.ServiceTests;

import com.project.Svalbard.Exceptions.CompletableFutureException;
import com.project.Svalbard.Exceptions.EntryNotFoundException;
import com.project.Svalbard.Exceptions.InvalidInputException;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Service.AnalyticsService;
import com.project.Svalbard.Service.AppService;
import com.project.Svalbard.Service.UtilService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Analytics Tests")
public class AnalyticsTests {

    @Autowired
    AnalyticsService analyticsService;

    @Autowired
    AppService appService;

    @Autowired
    UtilService utilService;

    @BeforeEach
    void setUp() {
        analyticsService.clearCachefn();
    }


    @Test
    @DisplayName("Operation Test - Correlation - all metrics")
    @Transactional
    void correlationTest()
    {
        cachedMetricTest();
    }

    @Test
    @DisplayName("Operation Test - Correlation - one metric - Dimensionality")
    @Transactional
    void correlationForOneElementTest()
    {
        oneMetricCorrelationTest();
    }

    @Test
    @DisplayName("Operation Test - Covariance")
    @Transactional
    void covarianceTest()
    {
        HashMap classificationParams = new HashMap();
        classificationParams.put("Classifier", "KNN");
        classificationParams.put("K", "11");
        classificationParams.put("Weights", "uniform");
        classificationParams.put("Algorithm","brute");

        List<Classification> clfs;
        try {
            clfs = appService.hpsearch(classificationParams);
            assertTrue(analyticsService.cachedMetric(clfs, "Dimensionality", "Covariance", new HashMap<>()).isEmpty());
        }
        catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        } catch (CompletableFutureException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Operation Test - Correlation - One Metric Multiple times")
    @Transactional
    void repeatedCorrelationTest() throws CompletableFutureException, InvalidInputException, EntryNotFoundException {
        HashMap classificationParams = new HashMap();
        classificationParams.put("Classifier", "KNN");
        classificationParams.put("K", "11");
        classificationParams.put("Weights", "uniform");
        classificationParams.put("Algorithm","brute");
        List clfs;
        clfs = appService.hpsearch(classificationParams);
        assertEquals(
                analyticsService.cachedMetric(
                clfs, "Dimensionality", "Correlation", new HashMap<>())
                        .get("Dimensionality")
            ,
                analyticsService.cachedMetric(
            clfs, "Dimensionality", "Correlation", new HashMap<>())
                        .get("Dimensionality")
            );
    }


    @Test
    @DisplayName("Cache Time Test")
    @Transactional
    void cacheTest()
    {
        List<Long> executionTimes = new ArrayList<>();
        Long start = System.currentTimeMillis();
        cachedMetricTest();
        executionTimes.add(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        cachedMetricTest();
        executionTimes.add(System.currentTimeMillis() - start);
        assertTrue(executionTimes.get(0) > 300);
        assertTrue(executionTimes.get(1) < executionTimes.get(0));
        assertTrue(executionTimes.get(1) < 100);
    }

    @Test
    @DisplayName("Partial Cache Hit - Time test")
    @Transactional
    void partialCacheTest()
    {
        List<Long> executionTimes = new ArrayList<>();
        Long start = System.currentTimeMillis();
        cachedMetricTest();
        executionTimes.add(System.currentTimeMillis() - start);
        analyticsService.clearCachefn();
        oneMetricCorrelationTest();
        start = System.currentTimeMillis();
        cachedMetricTest();
        assertTrue(System.currentTimeMillis() - start < executionTimes.get(0));
    }

    private void oneMetricCorrelationTest() {
        HashMap classificationParams = new HashMap();
        classificationParams.put("Classifier", "KNN");
        classificationParams.put("K", "11");
        classificationParams.put("Weights", "uniform");
        classificationParams.put("Algorithm","brute");
        List<Classification> clfs;
        try {
            clfs = appService.hpsearch(classificationParams);
            Double correlationMetric = analyticsService.cachedMetric(
                    clfs, "Dimensionality", "Correlation", new HashMap<>()).get("Dimensionality");
            assertTrue(correlationMetric < 1 && correlationMetric > 0);
            assertTrue(correlationMetric <= 0.041 && correlationMetric >= 0.04);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        } catch (CompletableFutureException e) {
            e.printStackTrace();
        }
    }

    private void cachedMetricTest() {
        HashMap classificationParams = new HashMap();
        classificationParams.put("Classifier", "KNN");
        classificationParams.put("K", "11");
        classificationParams.put("Weights", "uniform");
        classificationParams.put("Algorithm","brute");

        List<Classification> clfs ;

        try {
            clfs = appService.hpsearch(classificationParams);
            List<String> datasetMetrics_expected = utilService.getDatasetMetrics();
            List<String> datasetMetrics_actual = new ArrayList<>(analyticsService.cachedMetric(
                    clfs, "all", "Correlation", new HashMap<>()).keySet());
            assertTrue(datasetMetrics_actual.containsAll(datasetMetrics_expected) && datasetMetrics_expected.containsAll(datasetMetrics_actual));
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        } catch (CompletableFutureException e) {
            e.printStackTrace();
        }
    }
}
