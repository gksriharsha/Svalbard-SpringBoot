package com.project.Svalbard.Dao;

import com.project.Svalbard.Model.db.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {

    Task findByName(String name);
}
