package com.example.chatroom.controller;

import com.example.chatroom.entity.User;
import com.example.chatroom.service.UserService;
import com.example.chatroom.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户注册
    @PostMapping("/register")
    public Response<User> registerUser(@RequestBody User user) {
        try {
            return userService.registerUser(user);
        } catch (Exception e) {
            return Response.error("Error registering user: " + e.getMessage());
        }
    }

    // 用户登录
    @PostMapping("/login")
    public Response<String> loginUser(@RequestBody User user) {
        try {
            return userService.loginUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            return Response.error("Error logging in: " + e.getMessage());
        }
    }
}
