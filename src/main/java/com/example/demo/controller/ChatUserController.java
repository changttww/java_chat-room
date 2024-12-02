package com.example.demo.controller;

import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.ChatUser;
import com.example.demo.serivce.ChatUserService;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/users")  // 为整个控制器添加 /users 前缀
public class ChatUserController {

    @Resource
    private ChatUserService chatUserService;


    @PostMapping("/register")
    @CrossOrigin
    @ResponseBody
    public ResultBody<String> register(@RequestBody ChatUser body) {

        // 设置默认的HEAd
        if (body.getHead() == null) {
            body.setHead("😄");
        }

        System.out.println(body);
        return chatUserService.register(body);
    }

    @PostMapping("/login")
    @CrossOrigin
    @ResponseBody
    public ResultBody login(@RequestBody ChatUser body) {
        return chatUserService.login(body);
    }
}
