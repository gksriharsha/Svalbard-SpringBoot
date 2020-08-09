package com.project.Svalbard.Model.Angular;

public class Results {
    private float accuracy;
    private float f1_score;
    private float precision;
    private float recall;
    private float time;

    public Results(float accuracy, float f1_score, float precision, float recall, float time) {
        this.accuracy = accuracy;
        this.f1_score = f1_score;
        this.precision = precision;
        this.recall = recall;
        this.time = time;
    }

    public float getAccuracy() {

        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getF1_score() {
        return f1_score;
    }

    public void setF1_score(float f1_score) {
        this.f1_score = f1_score;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getRecall() {
        return recall;
    }

    public void setRecall(float recall) {
        this.recall = recall;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
