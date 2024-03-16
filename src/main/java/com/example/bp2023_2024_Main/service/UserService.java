package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user, List<String> roleNames);
    void deleteUser(Long id);
    User updateUser(Long id,User updatedUser);
    //User createUser(User user);
}
