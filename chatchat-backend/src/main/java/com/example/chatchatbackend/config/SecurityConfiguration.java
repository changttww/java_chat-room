package com.example.chatchatbackend.config;

import com.example.chatchatbackend.entity.RestBean;
import com.example.chatchatbackend.entity.dto.Account;
import com.example.chatchatbackend.entity.vo.response.AccountVO;
import com.example.chatchatbackend.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
public class SecurityConfiguration {
    @Resource
    AccountService accountService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/api/auth/**","/error", "/websocket/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(conf->conf
                        .loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onAuthenticationSuccess)
                        .failureHandler(this::onAuthenticationFailure)
                )
                .logout(conf->conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint(this::onUnauthorized)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
    /*登录成功返回用户信息给客户端*/
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        User user=(User) authentication.getPrincipal();
        Account account = accountService.findAccountByUsername(user.getUsername());
        AccountVO vo = new AccountVO(account.getId(),user.getUsername(),account.getRole(), account.getAvatar(), account.getRegisterTime());
        response.getWriter().println(RestBean.success(vo).asJSONString());
    }
    /*登录失败返回失败信息*/
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(RestBean.unAuthorized(exception.getMessage()).asJSONString());
    }
    /*退出登录成功*/
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }
    /*未认证访问其他页面返回失败信息*/
    public void onUnauthorized(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
        response.getWriter().write(RestBean.unAuthorized(exception.getMessage()).asJSONString());
    }
}
