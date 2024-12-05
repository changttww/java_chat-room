package com.example.chatroom.config;

import com.example.chatroom.util.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF，因为 WebSocket 连接不需要 CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 跨域配置
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("api/users/login", "api/users/register", "/ws/**").permitAll() // 登录、注册、WebSocket接口公开
                        .anyRequest().authenticated() // 其他接口需要认证
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // 注册 JWT 过滤器
                .httpBasic(httpBasic -> {});  // 如果需要支持基础 HTTP 身份验证（可以去掉）

        return http.build();
    }

    // 定义 BCrypt 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 跨域配置
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // 允许所有来源
        configuration.addAllowedMethod("*"); // 允许所有 HTTP 方法
        configuration.addAllowedHeader("*"); // 允许所有请求头
        configuration.setAllowCredentials(true); // 允许携带 Cookie
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
