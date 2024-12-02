package com.example.chatchatbackend.Controller;

import com.example.chatchatbackend.entity.RestBean;
import com.example.chatchatbackend.entity.vo.response.FriendsVO;
import com.example.chatchatbackend.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AccountController {
    @Resource
    AccountService accountService;
    @GetMapping("/friends")
    public RestBean<List<FriendsVO>> getFriend(@RequestParam int id){
        return RestBean.success(accountService.findFriendsById(id));
    }
}
