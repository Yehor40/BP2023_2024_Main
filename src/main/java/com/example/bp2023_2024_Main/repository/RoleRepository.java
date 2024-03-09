package com.example.bp2023_2024_Main.repository;

import com.example.bp2023_2024_Main.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
