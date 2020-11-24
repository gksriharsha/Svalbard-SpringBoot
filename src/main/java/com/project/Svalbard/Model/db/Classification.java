package com.project.Svalbard.Model.db;


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Data
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

    public Dataset getdataset() {
        return dataset;
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
