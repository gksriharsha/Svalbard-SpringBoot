package com.project.Svalbard.Model.db;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "dask")
    private Status dask;
}
