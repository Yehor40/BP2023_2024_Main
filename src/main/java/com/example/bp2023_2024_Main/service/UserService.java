package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    void saveUser(UserDto userDto);
    Long getUserIdByUsername(String username);
    User findByEmail(String email);

    List<User> findAllUsers();
    Optional<User> getUserById(Long id);
    void createUser(UserDto userDto);
    void deleteUser(Long id);
    User updateUser(Long id,User updatedUser);
}
