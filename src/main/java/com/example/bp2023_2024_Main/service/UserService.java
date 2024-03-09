package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
