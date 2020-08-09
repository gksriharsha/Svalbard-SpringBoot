package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, UUID>, QueryByExampleExecutor<Dataset> {

    Optional<Dataset> findByFid(UUID uuid);
}
