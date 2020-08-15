package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, UUID> {


    Optional<Classification> findByEid(UUID uuid);

    @Query(value = "SELECT * FROM classification\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 1", nativeQuery = true)
    Classification getRandomrow();

    @Query(value = "SELECT * FROM classification\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 10", nativeQuery = true)
    List<Classification> getRandomrows();

    List<Classification> findByAccuracyGreaterThanEqualOrderByAccuracyDesc(float accuracy);

    List<Classification> findByFscoreGreaterThanEqualOrderByFscore(float fscore);

    //TODO Queries should be eliminated and instead SPring specifications or QuerdslPredicateExecutor should be used.

    @Query(value = " SELECT  accuracy from svalbard.classification where (:task is null or task_name = :task)", nativeQuery = true)
    List<Float> getacc(@Param("task") String task);

    @Query(value = " SELECT fscore from svalbard.classification where (:task is null or task_name = :task)", nativeQuery = true)
    List<Float> getfscore(@Param("task") String task);
}
