package com.example.chatchatbackend.Controller;

import com.example.chatchatbackend.entity.RestBean;
import com.example.chatchatbackend.entity.vo.request.RegisterVO;
import com.example.chatchatbackend.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.function.Supplier;
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    @Resource
    AccountService accountService;
    @PostMapping("/register")
    public RestBean<Void> register(@RequestBody RegisterVO registerVO){
        return this.messageHandle(registerVO,accountService::registerAccount);
    }

    private <T> RestBean<Void> messageHandle(T vo, Function<T,String> function){
        return messageHandle(()->function.apply(vo));
    }
    //Supplier<T>函数式接口
    private RestBean<Void> messageHandle(Supplier<String> action){
        String message= action.get();
        return message == null ? RestBean.success() : RestBean.failure(400,message);
    }
}
