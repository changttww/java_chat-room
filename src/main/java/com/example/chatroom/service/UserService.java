package com.example.chatroom.service;

import com.example.chatroom.entity.Room;
import com.example.chatroom.entity.RoomMember;
import com.example.chatroom.entity.User;
import com.example.chatroom.entity.UserRelationship;
import com.example.chatroom.entity.UserRelationship;
import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.DTO.UserDTO;
import com.example.chatroom.repository.UserRelationshipRepository;
import com.example.chatroom.repository.UserRepository;
import com.example.chatroom.common.response.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Timestamp;

import org.aspectj.asm.internal.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.management.relation.Relation;

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
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
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

    public class LoginResponse {
        private String token;
        private Integer uid;

        LoginResponse(String token,Integer uid){
            token=this.token;
            uid=this.uid;
        }

        String getToken(){
            return token;
        }

        Integer getUid(){
            return uid;
        }
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

        LoginResponse loginResponse = new LoginResponse(token, user.get().getUserid());
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


        return Response.success("User info fetched successfully", userDTO);
    }

    // 获取任一用户信息
    public Response<UserDTO> getAnyUserInfo(Integer userid){
        Optional<User> userOptional = userRepository.findByUserid(userid);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserid(user.getUserid());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatar(user.getAvatar());


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

    // 移出黑名单
    public Response<String> removeVillain(Integer otherid) {
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Current user not found");
        }

        User user = userOptional.get();

        // 获取另一位用户 ID
        userOptional = userRepository.findByUserid(otherid);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Other user not found");
        }

        User other = userOptional.get();

        // 查找并移除关系
        Optional<UserRelationship> relationshipOptional = user.getRelationships().stream()
            .filter(relation -> relation.getOther().equals(other) && "malo".equals(relation.getRelationshipType()))
            .findFirst();

        if (relationshipOptional.isPresent()) {
            UserRelationship relationship = relationshipOptional.get();
            // 从当前用户的关系列表中移除
            user.getRelationships().remove(relationship);
            // 从数据库中删除关系记录
            userRelationshipRepository.delete(relationship);
            // 保存更新后的用户信息
            userRepository.save(user);
        } else {
            // 如果关系不存在，可以选择抛出异常或返回错误信息
            throw new RuntimeException("Relationship does not exist");
        }

        // 成功情况
        return Response.success("Villain removed successfully", null);
    }


    // 加入黑名单
    public Response<String> addVillain(Integer otherid) {
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Current user not found");
        }

        User user = userOptional.get();

        // 获取另一位用户 ID
        userOptional = userRepository.findByUserid(otherid);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Other user not found");
        }

        User other = userOptional.get();
        UserRelationship re = new UserRelationship(user,other,"malo","Un malo",new Timestamp(System.currentTimeMillis()));

        boolean relationshipExists = user.getRelationships().stream()
        .anyMatch(relation -> relation.getOther().getUserid().equals(other.getUserid()));


        if (!relationshipExists&&other!=user) {
            // 将other添加到user的关系列表中
            userRelationshipRepository.save(re);
            //user.getRelationships().add(re);
            userRepository.save(user);
        } else {
            // 如果关系已存在，可以选择抛出异常或返回错误信息
            throw new Error("Invalid create relationship");
        }

        // 成功情况
        return Response.success("Villain added successfully", null);
    }

    // 展示黑名单
    public Response<List<UserRelationship>> getBlacklist() {
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();
        Optional<User> userOptional = userRepository.findByUserid(currentUserId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Current user not found");
        }

        User user = userOptional.get();
        List<UserRelationship> ships = user.getRelationships();

        // 成功情况
        return Response.success("Show villains successfully", ships);
    }



    // // 更新好友
    // public Response<String> addFriend(Integer otherid) {
    //     // 获取当前用户 ID
    //     Integer currentUserId = getCurrentUserId();
    //     Optional<User> userOptional = userRepository.findByUserid(currentUserId);
    //     if (userOptional.isEmpty()) {
    //         throw new RuntimeException("Current user not found");
    //     }

    //     User user = userOptional.get();

    //     // 获取另一位用户 ID
    //     userOptional = userRepository.findByUserid(otherid);
    //     if (userOptional.isEmpty()) {
    //         throw new RuntimeException("Other user not found");
    //     }

    //     User other = userOptional.get();
    //     UserRelationship re = new UserRelationship(user,other,"amigo","Un amigo",new Timestamp(System.currentTimeMillis()));

    //     boolean relationshipExists = user.getRelationships().stream()
    //     .anyMatch(relation -> relation.getOther().equals(other));

    //     if (!relationshipExists) {
    //         // 将other添加到user的关系列表中
    //         userRelationshipRepository.save(re);
    //         user.getRelationships().add(re);
    //         userRepository.save(user);
    //     } else {
    //         // 如果关系已存在，可以选择抛出异常或返回错误信息
    //         throw new RuntimeException("Relationship already exists");
    //     }

    //     // 成功情况
    //     return Response.success("Friend added successfully", null);
    // }
};