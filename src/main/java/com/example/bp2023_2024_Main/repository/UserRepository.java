package com.example.bp2023_2024_Main.repository;

import com.example.bp2023_2024_Main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
