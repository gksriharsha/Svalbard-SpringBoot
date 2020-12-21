package com.project.Svalbard.Agents;

import com.project.Svalbard.Model.db.Classification;

import java.util.HashMap;
import java.util.UUID;

public interface Agent {

    boolean ping();

    String submitClassificationTask(String url,HashMap parameters);

    void isTaskCompleted(UUID taskId);

    String getClassificationResult(UUID taskId);
}
