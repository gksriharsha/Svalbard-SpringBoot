package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {


    @Query(value = "SELECT * FROM classification\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 1", nativeQuery = true)
    Classification getRandomrow();

    @Query(value = "SELECT * FROM classification\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 10", nativeQuery = true)
    List<Classification> getRandomrows();

    List<Classification> findByAccuracyGreaterThanEqualOrderByAccuracyDesc(float accuracy);

    List<Classification> findByFscoreGreaterThanEqualOrderByFscoreDesc(float fscore);

    //TODO Queries should be eliminated and instead Spring specifications or QuerydslPredicateExecutor should be used.
}
