package com.example.chatroom.service;

import com.example.chatroom.entity.User;
import com.example.chatroom.repository.UserRepository;
import com.example.chatroom.common.response.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.example.chatroom.util.JwtUtil.EXPIRATION_TIME;
import static com.example.chatroom.util.JwtUtil.SECRET_KEY;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}