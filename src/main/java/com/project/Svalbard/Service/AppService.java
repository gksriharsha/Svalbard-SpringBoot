package com.project.Svalbard.Service;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.project.Svalbard.Dao.ClassificationRepository;
import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Dao.TaskRepository;
import com.project.Svalbard.Model.Angular.Dataset;
import com.project.Svalbard.Model.Angular.GeneralCard;
import com.project.Svalbard.Model.Angular.Platform;
import com.project.Svalbard.Model.Angular.Results;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Model.db.Task;
import com.project.Svalbard.Util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private ClassificationRepository classificationRepository;

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<GeneralCard> getCards(int numbers) {
        Classification clf;

        List<GeneralCard> generalCardList = new ArrayList<>();
        for (int i = 0; i < numbers; i++) {
            clf = classificationRepository.getRandomrow();
            generalCardList.add(convertToGeneralCard(clf));
        }
        return generalCardList;
    }

    public List<HashMap<String, Object>> getFlexiCards(int numbers) throws Exception {

        List<HashMap<String, Object>> Flexcards = new ArrayList<>();

        for (int i = 0; i < numbers; i++) {
            List<String> required = new ArrayList<>();
            required.add("fid");
            required.add("name");
            required.add("dimensionality");

            Classification clf = classificationRepository.getRandomrow();
            Flexcards.add(convertToFlexCard(clf, required));
        }
        return Flexcards;
    }

    public List<GeneralCard> hpsearch(HashMap<String, String> searchparams) throws Exception {
        HashMap<String, String> hplist = new HashMap<>();
        Task task = taskRepository.findByName(searchparams.get("Classifier"));
        HashMap<String, Object> taskMap1 = (HashMap<String, Object>) Mapper.getfromObject(task);
        while (taskMap1.values().remove(null)) ;
        BiMap<Object, String> taskMap = HashBiMap.create(taskMap1).inverse();
        searchparams.forEach((k, v) -> {
            if (!k.contains("Classifier")) {
                hplist.put(k, v);
            }
        });

        Classification queryclf;
        HashMap<String, Object> clfmap = new HashMap<>();
        hplist.forEach((k, v) -> {
            String hpnumber = taskMap.get(k);
            clfmap.put(hpnumber, v);
        });
        queryclf = Mapper.getasClassification(clfmap);
        Example<Classification> clfex = Example.of(queryclf, ExampleMatcher.matching()
                .withIgnorePaths("id", "accuracy", "precision", "fscore", "recall", "time", "precision1", "recall1", "fscore1")
                .withIgnoreNullValues());

        return classificationRepository.findAll(clfex).stream().map(this::convertToGeneralCard).collect(Collectors.toList());
    }

    private GeneralCard convertToGeneralCard(Classification clf) {
        GeneralCard gc = new GeneralCard();
        gc.setTask(clf.getTask().getName());
        gc.setPlatform(new Platform("Python", "scikit-learn"));
        logger.info("Classification Id for - " + clf.getFid());
        gc.setDataset(new Dataset(clf.getFid(), clf.getdataset().getName(), clf.getdataset().getFeatures(),
                clf.getdataset().getInstances(), clf.getdataset().getNumberOfClasses()));
        gc.setProcess("Classification");
        gc.setResults(new Results(clf.getAccuracy(), clf.getFscore(), clf.getPrecision(), clf.getRecall(), clf.getTime()));
        return gc;
    }

    private HashMap<String, Object> convertToFlexCard(Classification clf, List<String> required) throws Exception {
        com.project.Svalbard.Model.db.Dataset d = clf.getdataset();
        HashMap<String, Object> options = (HashMap<String, Object>) Mapper.getfromObject(d);
        HashMap<String, Object> flexCard = new HashMap<>();
        flexCard.put("process", "Classification");
        flexCard.put("task", clf.getTask().getName());

        HashMap<String, Object> dataset = new HashMap<>();
        for (String item : required) {
            dataset.put(item, options.get(item.toLowerCase()));
        }
        flexCard.put("dataset", dataset);

        HashMap<String, Object> platform = new HashMap<>();
        platform.put("name", "Python");
        platform.put("library", "scikit-learn");
        flexCard.put("platform", platform);

        Results res = new Results(clf.getAccuracy(), clf.getFscore(), clf.getPrecision(), clf.getRecall(), clf.getTime());

        HashMap<String, Object> results = new HashMap<>();
        required = new ArrayList<>();
        required.add("accuracy");
        required.add("recall");
        options = (HashMap<String, Object>) Mapper.getfromObject(res);
        for (String item : required) {
            results.put(item, options.get(item));
        }
        flexCard.put("results", results);
        return flexCard;
    }
}
