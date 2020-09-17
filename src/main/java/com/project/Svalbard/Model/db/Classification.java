package com.project.Svalbard.Model.db;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Table(name = "classification")
@Entity
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eid", referencedColumnName = "eid")
    private ExecutionClf execution;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    private Dataset dataset;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_name", referencedColumnName = "name")
    private Task task;

    //region hyperparameters and metrics
    @Column(name = "hp_1")
    private String hp1;
    @Column(name = "hp_2")
    private String hp2;
    @Column(name = "hp_3")
    private String hp3;
    @Column(name = "hp_4")
    private String hp4;
    @Column(name = "hp_5")
    private String hp5;
    @Column(name = "hp_6")
    private String hp6;
    @Column(name = "hp_7")
    private String hp7;
    @Column(name = "hp_8")
    private String hp8;
    @Column(name = "accuracy")
    private float accuracy;
    @Column(name = "precision")
    private float precision;
    @Column(name = "fscore")
    private float fscore;
    @Column(name = "recall")
    private float recall;
    @Column(name = "time")
    private float time;
    @Column(name = "precision_1")
    private float precision1;
    @Column(name = "recall_1")
    private float recall1;
    @Column(name = "fscore_1")
    private float fscore1;
    //endregion

    public Classification() {
    }

    //region setters and getters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dataset getdataset() {
        return dataset;
    }

    public Task getTask() {
        return task;
    }

    public UUID getFid() {
        return dataset.getFid();
    }

    public void setFid(UUID fid) {
        dataset.setFid(fid);
    }

    public UUID getEid() {
        return execution.getEid();
    }

    public void setEid(UUID eid) {
        execution.setEid(eid);
    }

    public String getHp1() {
        return hp1;
    }

    public void setHp1(String hp1) {
        this.hp1 = hp1;
    }

    public String getHp2() {
        return hp2;
    }

    public void setHp2(String hp2) {
        this.hp2 = hp2;
    }

    public String getHp3() {
        return hp3;
    }

    public void setHp3(String hp3) {
        this.hp3 = hp3;
    }

    public String getHp4() {
        return hp4;
    }

    public void setHp4(String hp4) {
        this.hp4 = hp4;
    }

    public String getHp5() {
        return hp5;
    }

    public void setHp5(String hp5) {
        this.hp5 = hp5;
    }

    public String getHp6() {
        return hp6;
    }

    public void setHp6(String hp6) {
        this.hp6 = hp6;
    }

    public String getHp7() {
        return hp7;
    }

    public void setHp7(String hp7) {
        this.hp7 = hp7;
    }

    public String getHp8() {
        return hp8;
    }

    public void setHp8(String hp8) {
        this.hp8 = hp8;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getPrecision() {
        return precision;
    }

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public float getFscore() {
        return fscore;
    }

    public void setFscore(float fscore) {
        this.fscore = fscore;
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

    public float getPrecision1() {
        return precision1;
    }

    public void setPrecision1(float precision1) {
        this.precision1 = precision1;
    }

    public float getRecall1() {
        return recall1;
    }

    public void setRecall1(float recall1) {
        this.recall1 = recall1;
    }

    public float getFscore1() {
        return fscore1;
    }

    public void setFscore1(float fscore1) {
        this.fscore1 = fscore1;
    }

    //endregion


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("execution ", execution.getEid())
                .append("dataset", dataset.getFid())
                .toString();
    }
}
