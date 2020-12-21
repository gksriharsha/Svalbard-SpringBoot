package com.project.Svalbard.Agents;

import com.project.Svalbard.Model.db.Classification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PythonAgent implements Agent {

    private final RestTemplate restTemplate;

    @Value("${url.python}")
    private String url;

    public PythonAgent(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public boolean ping() {
        return this.restTemplate.getForEntity(url, String.class).getStatusCode().is2xxSuccessful();
    }

    @Override
    public String submitClassificationTask(String dataseturl,HashMap parameters) {
        String urlForTaskSubmission = url + "classification";
        System.out.println(urlForTaskSubmission);
        Map<String, Object> map = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("1");

        map.put("eID",UUID.randomUUID().toString());
        map.put("datasetLink", dataseturl);
        map.put("Parameters", parameters);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        return this.restTemplate.postForObject(urlForTaskSubmission,entity,String.class);
    }

    @Override
    public void isTaskCompleted(UUID taskId) {
        String resp = this.restTemplate.getForEntity(url+"task/"+"d9d488be-d110-48c9-9d9d-510a98ade6f5",String.class).getBody();
        System.out.println(resp);
        System.out.println(resp.contains("true"));
    }

    @Override
    public String getClassificationResult(UUID taskId) {
        String urlForClassificationResult = url + "/classification/"+taskId;
        return this.restTemplate.getForObject(urlForClassificationResult, String.class);
    }
}
