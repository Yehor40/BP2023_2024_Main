package com.example.bp2023_2024_Main.repository;

import com.example.bp2023_2024_Main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByName(String name);


}
