package com.project.Svalbard.Controller;

import com.project.Svalbard.Agents.PythonAgent;
import com.project.Svalbard.Model.Requests.AuthenticationResponse;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Model.db.Dataset;
import com.project.Svalbard.Model.db.ExecutionClf;
import com.project.Svalbard.Model.db.Task;
import com.project.Svalbard.Util.PrettyPrintingMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@Api(tags = "Agents Endpoint", description = "All the APIs for the agents to submit the result of ML tasks are here")
public class ApiController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Classification classification;

    @Autowired
    private Dataset dataset;

    @Autowired
    private ExecutionClf executionClf;

    @Autowired
    private Task task;

    @Autowired
    private PythonAgent pythonAgent;

    @GetMapping("/")
    public String home() {
        return "Hello";
    }

    @ApiOperation("Get Results from python agent")
    @PostMapping("/classification")
    public void get_results(@RequestBody HashMap<String,String> results)
    {
        System.out.println("This is results block");
        System.out.println(new PrettyPrintingMap<String,String>(results));
    }


    @ApiOperation("Submit Task to python agent")
    @RequestMapping(value = "/api/ping", method = RequestMethod.GET)
    public ResponseEntity<AuthenticationResponse> pingTest()
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("Classifier", "KNN");
        params.put("K", "29");
        params.put("weights","uniform");
        params.put("algorithm","brute");
        params.put("leaf_size","30");
        params.put("p","2");
        System.out.println(pythonAgent.submitClassificationTask("https://www.openml.org/data/get_csv/52415/JapaneseVowels.arff",params));

        return ResponseEntity.ok(new AuthenticationResponse("lol",";lp",new Date()));
    }
}
