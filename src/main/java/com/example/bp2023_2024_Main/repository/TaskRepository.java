package com.example.bp2023_2024_Main.repository;

import com.example.bp2023_2024_Main.entity.Project_Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Project_Task, Long> {
}
