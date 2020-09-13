package com.project.Svalbard.Controller;

import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Model.db.Dataset;
import com.project.Svalbard.Model.db.ExecutionClf;
import com.project.Svalbard.Model.db.Task;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public String home() {
        return "Hello";
    }
}
