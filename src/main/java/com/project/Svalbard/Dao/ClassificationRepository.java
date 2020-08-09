package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, UUID> {


    Optional<Classification> findByEid(UUID uuid);

//    Optional<Classification> findById(Long id);

    @Query(value = "SELECT * FROM classification\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 1", nativeQuery = true)
    Classification getRandomrow();
}
