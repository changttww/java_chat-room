package com.example.chatroom.controller;

import com.example.chatroom.entity.Room;
import com.example.chatroom.entity.User;
import com.example.chatroom.entity.UserRelationship;
import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.DTO.UserDTO;
import com.example.chatroom.service.UserService;
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
    public Response<String> loginUser(@RequestBody User user) {
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

    // 获取任一用户信息
    @GetMapping("/getInfo/{userid}")
    public Response<UserDTO> getAnyUserInfo(@PathVariable("userid") Integer userid) {
        Optional<User> userOptional = userRepository.findByUserid(userid);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            return userService.getAnyUserInfo(userid);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error fetching user info: " + e.getMessage());
        }
    }

    // 更新用户头像
    @PostMapping("/upload-avatar")
    public Response<String> updateUserAvatar(@RequestBody Map<String, String> request) {
        String avatar=request.get("avatar");
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
    public Response<String> updateUsername(@RequestBody Map<String, String> request) {
        String username=request.get("username");
        System.out.println("Received username: " + username);
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
    public Response<String> updateUserPassword(@RequestBody Map<String, String> request) {
        String password=request.get("password");
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

    // 加入黑名单
    @PostMapping("/addblacklist")
    public Response<String> addBlacklist(@RequestBody Map<String, Integer> request) {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            Integer otherid=request.get("userId");
            return userService.addVillain(otherid);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error set blacklist: " + e.getMessage());
        }
    }

    // 移除黑名单
    @PostMapping("/removeblacklist")
    public Response<String> removeBlacklist(@RequestBody Map<String, Integer> request) {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            Integer otherid=request.get("userId");
            return userService.removeVillain(otherid);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error set blacklist: " + e.getMessage());
        }
    }

    // 展示黑名单
    @GetMapping("/getblacklist")
    public Response<List<UserRelationship>> getBlacklist() {
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        try {
            return userService.getBlacklist();
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error get blacklist: " + e.getMessage());
        }
    }


    // // 更新好友
    // @PostMapping("/friendlist")
    // public Response<String> updateFriendlist(@RequestBody Map<String, Integer> request) {
    //     Integer currentUserId = getCurrentUserId();
    //     Optional<User> userOptional = userRepository.findByUserid(currentUserId);
    //     if (userOptional.isEmpty()) {
    //         throw new RuntimeException("User not found");
    //     }

    //     try {
    //         Integer otherid=request.get("userId");
    //         return userService.addFriend(otherid);
    //     } catch (Exception e) {
    //         // 如果发生错误，返回失败响应
    //         return Response.error("Error set friendlist: " + e.getMessage());
    //     }
    // }
    
}
