package com.project.Svalbard.Model.db;

import javax.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    //endregion

    public void setHp8(String hp8) {
        this.hp8 = hp8;
    }
}
