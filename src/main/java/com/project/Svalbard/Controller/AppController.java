package com.project.Svalbard.Controller;

import com.project.Svalbard.Dao.ClassificationRepository;
import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Model.Angular.GeneralCard;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = "/eid/{uuid}")
    public ResponseEntity<Classification> getCard(@PathVariable(value = "uuid") UUID uuid) throws Exception {
        Classification clf = new Classification();
        clf = classificationRepository.findByEid(uuid).orElseThrow(() -> new Exception("Not found eid"));
        return ResponseEntity.ok(clf);
    }

    @GetMapping(value = "/home")
    public ResponseEntity<List<GeneralCard>> getSomeCards() throws Exception {
        List<GeneralCard> cards = appService.getCards(10);
        return ResponseEntity.ok(cards);
    }

    @GetMapping(value = "/home-flex")
    public ResponseEntity<List<HashMap<String, Object>>> getFlexCards() throws Exception {
        List<HashMap<String, Object>> FlexCards = appService.getFlexiCards(10);
        return ResponseEntity.ok(FlexCards);

    }

    @GetMapping(value = "/hp-search")
    public ResponseEntity<List<GeneralCard>> getEx() throws Exception {
        List<GeneralCard> cl = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("Classifier", "KNN");
        params.put("K", "29");
        cl = appService.hpsearch(params);
        return ResponseEntity.ok(cl);
    }

}
