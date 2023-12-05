package com.probaIT.ProbaIt.domain.controller;

import com.probaIT.ProbaIt.domain.entities.User;
import com.probaIT.ProbaIt.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(value = "/login")
    public User login(@RequestBody User user) {
        return service.login(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @GetMapping(value = "/user")
    public User getUserById(@RequestParam(value = "id") Long id) {
        return service.getUserById(id);
    }

    @PostMapping(value = "/user")
    public User signup(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping(value = "/user")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping(value = "/user")
    public void deleteUserById(@RequestParam(value = "id") Long id) {
        service.removeUser(id);
    }

}
