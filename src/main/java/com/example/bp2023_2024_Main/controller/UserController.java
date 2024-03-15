package com.example.bp2023_2024_Main.controller;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.User;
import com.example.bp2023_2024_Main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
//    @GetMapping("/users/{id}")
//    public String getUserById(@PathVariable Long id, Model model) {
//        try {
//            UserDto userDto = userService.getUserById(id)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//            model.addAttribute("user", userDto);
//            return "userMore";
//        } catch (ResponseStatusException e) {
//            throw e; // Re-throw to let Spring handle the exception
//        } catch (Exception e) {
//            // Log any unexpected exceptions
//            // Replace with your preferred logging framework and configuration
//            System.err.println("An unexpected error occurred: " + e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
//        }
//    }

}
