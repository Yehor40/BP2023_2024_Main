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

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        User user = userService.getUserById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        model.addAttribute("user", user);
        return "userMore";
    }
    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "userCreate";
    }
    @PostMapping("/create")
    public String createUser( @ModelAttribute("newUser")User user, @RequestParam List<String> roleNames) {
        userService.createUser(user,roleNames);
        return "redirect:/users";
    }
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) throws ChangeSetPersister.NotFoundException {
        User user = userService.getUserById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        model.addAttribute("user", user);
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
