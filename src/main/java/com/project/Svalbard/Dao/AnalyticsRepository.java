package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalyticsRepository extends JpaRepository<Classification, Long> {

    @Query(value = " SELECT  accuracy from svalbard.classification where (:task is null or task_name = :task)", nativeQuery = true)
    List<Float> getacc(@Param("task") String task);

    @Query(value = " SELECT fscore from svalbard.classification where (:task is null or task_name = :task)", nativeQuery = true)
    List<Float> getfscore(@Param("task") String task);
}
