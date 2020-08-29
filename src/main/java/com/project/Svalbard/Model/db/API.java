package com.project.Svalbard.Model.db;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Table(name = "api")
@Entity
public class API {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "Program")
    private String program;

    @Column(name = "APIKey")
    private String apikey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
