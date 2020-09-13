package com.project.Svalbard.Model.Angular;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

public class Dataset {

    @NotNull
    private UUID fid;
    @NotNull
    private String name;
    @Positive
    private int features;
    @Positive
    private int observations;
    @Positive
    private int classes;

    public Dataset(UUID fid, String name, int features, int observations, int classes) {
        this.fid = fid;
        this.name = name;
        this.features = features;
        this.observations = observations;
        this.classes = classes;
    }

    public int getFeatures() {
        return features;
    }

    public void setFeatures(int features) {
        this.features = features;
    }

    public int getObservations() {
        return observations;
    }

    public void setObservations(int observations) {
        this.observations = observations;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public UUID getFid() {
        return fid;
    }

    public void setFid(UUID fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
