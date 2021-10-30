package com.example7.demo7.controller;

import java.util.concurrent.ExecutionException;

import com.example7.demo7.entity.User;
import com.example7.demo7.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/users")
    public String saveUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return usersService.saveUser(user);
    }

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username ) throws InterruptedException, ExecutionException{
        return usersService.getUser(username);
    }
    
}
 