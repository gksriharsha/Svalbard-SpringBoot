package com.project.Svalbard.Model.Angular;

import java.util.UUID;

public class Dataset {

    private UUID fid;
    private String name;
    private int features;
    private int observations;
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
