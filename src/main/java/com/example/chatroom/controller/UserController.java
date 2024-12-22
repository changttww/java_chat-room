package com.example.chatroom.controller;

import com.example.chatroom.entity.User;
import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.DTO.UserDTO;
import com.example.chatroom.service.UserService;
import com.example.chatroom.service.UserService.LoginResponse;
import com.example.chatroom.repository.UserRelationshipRepository;
import com.example.chatroom.repository.UserRepository;
import com.example.chatroom.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

        private Integer getCurrentUserId() {
        // 获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  // 获取当前用户名

            // 通过用户名查询用户实体
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent() ) {
                User user = userOptional.get();
                return user.getUserid();  // 返回用户的ID
            } else {
                throw new RuntimeException("User not found");
            }
        }
        throw new RuntimeException("User is not authenticated");
    }

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
    public Response<LoginResponse> loginUser(@RequestBody User user) {
        try {
            return userService.loginUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            return Response.error("Error logging in: " + e.getMessage());
        }
    }
    
    // 获取用户信息
    @PostMapping("/own")
    public Response<UserDTO> getUserInfo() {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            return userService.getUserInfo();
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error fetching user info: " + e.getMessage());
        }
    }

    // 更新用户头像
    @PostMapping("/upload-avatar")
    public Response<String> updateUserAvatar(String avatar) {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            return userService.updateUserAvatar(avatar);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error set avatar: " + e.getMessage());
        }
    }

    // 更新用户名
    @PostMapping("/upload-username")
    public Response<String> updateUsername(String username) {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            return userService.updateUsername(username);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error set username: " + e.getMessage());
        }
    }


    // 更新密码
    @PostMapping("/update-password")
    public Response<String> updateUserPassword(String password) {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            return userService.updatePassword(password);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error set password: " + e.getMessage());
        }
    }

    // // 更新关系列表
    // @PostMapping("/relationships")
    // public Response<String> updateRelationships(String username, string action) {
    //     Integer currentUserId = getCurrentUserId();
    //     Optional<User> userOptional = userRepository.findByUserid(currentUserId);
    //     if (userOptional.isEmpty()) {
    //         throw new RuntimeException("User not found");
    //     }

    //     try {
    //         return userService.updateUsername(username);
    //     } catch (Exception e) {
    //         // 如果发生错误，返回失败响应
    //         return Response.error("Error set relationships: " + e.getMessage());
    //     }
    // }
    
}
