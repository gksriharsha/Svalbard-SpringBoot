package com.project.Svalbard.Model.db;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ExecutionClf")
public class ExecutionClf {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "eid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID eid;

    @Column(name = "Crossvalidation")
    private String crossvalidation;

    @Enumerated(EnumType.STRING)
    @Column(name = "dask")
    private Status dask;


    public UUID getEid() {
        return eid;
    }

    public void setEid(UUID eid) {
        this.eid = eid;
    }

    public Status getDask() {
        return dask;
    }

    public void setDask(Status dask) {
        this.dask = dask;
    }

    public String getCrossvalidation() {
        return crossvalidation;
    }

    public void setCrossvalidation(String crossvalidation) {
        this.crossvalidation = crossvalidation;
    }
}
