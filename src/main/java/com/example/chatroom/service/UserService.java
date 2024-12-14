package com.example.chatroom.service;

import com.example.chatroom.entity.Room;
import com.example.chatroom.entity.RoomMember;
import com.example.chatroom.entity.User;
import com.example.chatroom.entity.UserRelationship;
import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.DTO.UserDTO;
import com.example.chatroom.repository.UserRelationshipRepository;
import com.example.chatroom.repository.UserRepository;
import com.example.chatroom.common.response.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.example.chatroom.util.JwtUtil.EXPIRATION_TIME;
import static com.example.chatroom.util.JwtUtil.SECRET_KEY;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRelationshipRepository userRelationshipRepository;

    // 通过用户名获取用户ID
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

    private User getCurrentUser() {
        // 获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  // 获取当前用户名

            // 通过用户名查询用户实体
            Optional<User> userOptional = userRepository.findByUsername(username);

            if (userOptional.isPresent() ) {
                User user = userOptional.get();
                return user;  // 返回用户的ID
            } else {
                throw new RuntimeException("User not found");
            }
        }
        throw new RuntimeException("User is not authenticated");
    }

    // 用户注册
    public Response<User> registerUser(User user) {
        // 检查用户名是否已经存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return Response.error("Username already taken");
        }

        // 直接存储明文密码
        // 不再加密密码，直接存储
        user.setPassword(user.getPassword());

        // 保存用户信息
        User savedUser = userRepository.save(user);

        // 返回用户信息
        return Response.success("Registration successful", savedUser);
    }

    // 用户登录
    public Response<String> loginUser(String username, String password) {
        // 查找用户
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            return Response.error("Invalid username");
        }

        // 直接比较明文密码
        if (!password.equals(user.get().getPassword())) {
            return Response.error("Invalid password");
        }

        // 使用已经生成的密钥（SECRET_KEY）
        // 创建JWT Token
        String token = Jwts.builder()
                .setSubject(user.get().getUsername())  // 设置用户名为JWT的主题
                .setIssuedAt(new Date())         // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SECRET_KEY)  // 使用秘钥进行签名
                .compact();

        return Response.success("Login successful", token);
    }

    // 获取用户信息
    public Response<UserDTO> getUserInfo(){
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserid(user.getUserid());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatar(user.getAvatar());

        // 获取关系列表成员
        List<UserRelationship> userRelationships = userRelationshipRepository.findByUserid(currentUserId);

        // 构建成员信息列表
        List<Map<String, Object>> relationships = userRelationships.stream()
                .map(other -> {
                    Map<String, Object> otherInfo = new HashMap<>();
                    otherInfo.put("userId", other.getUser().getUserid());
                    otherInfo.put("username", other.getUser().getUsername());
                    otherInfo.put("avatar", other.getUser().getAvatar());
                    return otherInfo;
                })
                .collect(Collectors.toList());

        userDTO.setRelationships(relationships);

        return Response.success("User info fetched successfully", userDTO);
    }

    // 更新用户头像
    public Response<String> updateUserAvatar(String avatar){
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        user.setAvatar(avatar);
        userRepository.save(user);
        
        // 失败情况
        return Response.success("User info updated successfully",null);
    }

    // 更新用户名
    public Response<String> updateUsername(String username){
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        user.setUsername(username);
        userRepository.save(user);
        
        // 失败情况
        return Response.success("User info updated successfully",null);
    }

    // 更新用户密码
    public Response<String> updatePassword(String password){
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        user.setPassword(password);
        userRepository.save(user);
        
        return Response.success("User info updated successfully",null);
    }

    // 更新黑名单
    public Response<String> addRelationships(Integer otherid, String action){
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // 获取另一位用户 ID
        userOptional = userRepository.findByUserid(otherid);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User other = userOptional.get();

        if(action=="add"){

        }
        else if(action=="remove"){
            return Response.error("User not found");
        }
        else{
            return Response.error("Invalid action");
        }


        userRepository.save(user);
        
        // 失败情况
        return Response.success("User info updated successfully",null);
    }
}