package com.example.bp2023_2024_Main.repository;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.Evidence;
import com.example.bp2023_2024_Main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvidenceRepository  extends JpaRepository<Evidence, Long>
{

}
