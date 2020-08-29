package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.API;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIRepository extends JpaRepository<API, Long> {
    API findByProgram(String program);
}
