package com.example.bp2023_2024_Main.service.impl;

import com.example.bp2023_2024_Main.dto.UserDto;
import com.example.bp2023_2024_Main.entity.Role;
import com.example.bp2023_2024_Main.entity.User;
import com.example.bp2023_2024_Main.repository.RoleRepository;
import com.example.bp2023_2024_Main.repository.UserRepository;
import com.example.bp2023_2024_Main.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(user -> convertEntityToDto(user))
                    .collect(Collectors.toList());
        } catch (IndexOutOfBoundsException e) {
            // Handle the exception appropriately (e.g., log the error, return an empty list)
            System.err.println("Error converting user: " + e.getMessage());
            return Collections.emptyList(); // Or handle in another way
        }
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public User createUser(User user, List<String> roleNames) {
        List<Role> roles = new LinkedList<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                throw new IllegalArgumentException("Role not found: " + roleName);
            }
            roles.add(role);
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long id) {
            userRepository.deleteById(id);
    }
    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = new User();
        existingUser = userRepository.findById(id).orElseThrow();
        // Update the properties of the existing user
        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRoles(updatedUser.getRoles());
        return userRepository.save(existingUser);
    }
    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();

        String[] nameParts = user.getName().split(" ");
        if (nameParts.length > 1) {
            userDto.setFirstName(nameParts[0]);
            userDto.setLastName(nameParts[1]);
        } else if (nameParts.length == 1) {
            // Handle single-word names (e.g., assign to both first and last name)
            userDto.setFirstName(nameParts[0]);
            userDto.setLastName("");
        } else {
            // Handle empty names (e.g., set to empty strings)
            userDto.setFirstName("");
            userDto.setLastName("");
        }

        userDto.setEmail(user.getEmail());
        return userDto;
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
