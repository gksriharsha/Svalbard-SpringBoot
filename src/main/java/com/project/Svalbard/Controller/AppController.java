package com.project.Svalbard.Controller;

import com.project.Svalbard.Dao.ClassificationRepository;
import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Model.Angular.GeneralCard;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Service.AppService;
import com.project.Svalbard.Util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private ClassificationRepository classificationRepository;

    @Autowired
    private DatasetRepository datasetRepository;


    @GetMapping(value = "/home")
    public ResponseEntity<List<Map<String, Object>>> getSomeCards() {
        List<GeneralCard> cards = appService.getCards(10);
        return ResponseEntity.ok(cards.stream().map(Mapper::getfromObject).collect(Collectors.toList()));
    }

    @GetMapping(value = "/home-flex")
    public ResponseEntity<List<HashMap<String, Object>>> getFlexCards() throws Exception {
        List<HashMap<String, Object>> FlexCards = appService.getFlexiCards(10);
        return ResponseEntity.ok(FlexCards);

    }

    @GetMapping(value = "/hp-search/{option}")
    public ResponseEntity<List<Map<String, Object>>> filterByHP(@PathVariable(value = "option") String option) throws Exception {
        List<Classification> cl = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("Classifier", "KNN");
        params.put("K", "29");
        cl = appService.hpsearch(params);
        List<String> required = new ArrayList<>();
        required.add("fid");
        required.add("name");
        required.add("dimensionality");
        if (option.equals("General")) {
            return ResponseEntity.ok(cl.stream().map(AppService::convertToGeneralCard)
                    .map(Mapper::getfromObject).collect(Collectors.toList()));
        }
        if (option.equals("Flex")) {
            return ResponseEntity.ok(cl.stream().map(row -> AppService.convertToFlexCard(row, required))
                    .map(Mapper::getfromObject).collect(Collectors.toList()));
        }
        return null;
    }

    @GetMapping(value = "/filter/{param}/{value}")
    public ResponseEntity<List<GeneralCard>> filterbyParameters(@PathVariable(value = "param") String param,
                                                                @PathVariable(value = "value") float value) throws Exception {
        if (param.equals("Accuracy")) {
            return ResponseEntity.ok(appService.accuracyfilter(value));
        }
        if (param.equals("FScore")) {
            return ResponseEntity.ok(appService.fscorefilter(value));
        }

        return null;
    }

}
