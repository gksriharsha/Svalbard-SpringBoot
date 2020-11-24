package com.project.Svalbard.Model.db;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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
}
