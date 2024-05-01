package com.example.bp2023_2024_Main.controller;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.User;
import com.example.bp2023_2024_Main.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model){
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userMore";
    }
    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "userCreate";
    }
    @PostMapping("/create")
    public String createUser( @ModelAttribute("newUser")UserDto user) {
        userService.createUser(user);
        return "redirect:/users";
    }
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        User userDto = userService.getUserById(id).orElseThrow();
        model.addAttribute("user", userDto);
        return "userEdit";
    }
    @PostMapping("/users/{id}/edit")
    public String editUser(@PathVariable Long id,
                           @ModelAttribute("user") User updatedUser,
                           RedirectAttributes redirectAttributes) {
        // Validate and update the user
        try {
            userService.updateUser(id, updatedUser);
        } catch (EntityNotFoundException e) {

        }
        return "redirect:/users";
    }
}
