package com.users.userservice.controller;


import com.users.userservice.domain.User;
import com.users.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get-users-by-page")
    public List<User> getUsersByPage(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return userRepository.findAll(page, size);
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/save-all-users")
    public List<User> saveAllUsers(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

    @PutMapping("/update-user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new Exception("User not found with id: " + id);
        }
    }

    // Other RESTful endpoints for CRUD operations
}
