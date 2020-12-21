package com.project.Svalbard.Model.db;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Component
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

    @Column(name = "language")
    private String language;

    @Column(name = "package")
    private String library;

    @Column(name = "version")
    private String version;

    @Column(name = "datetime")
    @UpdateTimestamp
    private Date date;


    @Enumerated(EnumType.STRING)
    @Column(name = "dask")
    private Status dask;
}
