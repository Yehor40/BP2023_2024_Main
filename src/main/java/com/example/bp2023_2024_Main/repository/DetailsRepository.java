package com.example.bp2023_2024_Main.repository;

import com.example.bp2023_2024_Main.entity.Task_Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Task_Details, Long> {

}
