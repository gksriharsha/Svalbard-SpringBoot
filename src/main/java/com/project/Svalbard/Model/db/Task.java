package com.project.Svalbard.Model.db;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Entity
@Table(name = "task")
public class Task {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Id
    @Column(name = "name")
    private String name;

    //region hyper parameters

    @Column(name = "hp1")
    private String hp1;

    @Column(name = "hp2")
    private String hp2;

    @Column(name = "hp3")
    private String hp3;

    @Column(name = "hp4")
    private String hp4;

    @Column(name = "hp5")
    private String hp5;

    @Column(name = "hp6")
    private String hp6;

    @Column(name = "hp7")
    private String hp7;

    @Column(name = "hp8")
    private String hp8;

    //endregion

    //region getters and setters

    public Task() {
    }

    //endregion
}
