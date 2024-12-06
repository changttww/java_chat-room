package com.example.chatroom.util;

import com.example.chatroom.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        // 检查请求头是否包含 "Bearer " 前缀
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

        // 解析 JWT,在每个请求中检查并验证 JWT（JSON Web Token）
            // 如果有效，则将用户的身份信息存入 Spring Security 的上下文中，以便后续的请求处理能够访问到用户的信息。
            try {
                String username = jwtUtil.extractUsername(token); //提取用户名

                //检查用户名和当前认证状态
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //验证 JWT 是否有效
                    if (jwtUtil.validateToken(token, username)) {
                        //将认证信息设置到 Spring Security 上下文
                        //如果 JWT 验证通过，代码就会创建一个 UsernamePasswordAuthenticationToken 对象，代表当前的用户身份信息
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        username,
                                        null,
                                        Collections.emptyList()
                                );
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {

                // JWT 解析异常或者 token 无效，直接返回 401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired JWT");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
