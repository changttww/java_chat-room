package com.example.chatchatbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.chatchatbackend.entity.dto.Account;
import com.example.chatchatbackend.entity.vo.request.RegisterVO;
import com.example.chatchatbackend.entity.vo.response.FriendsVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    public Account findAccountByUsername(String username);
    public String registerAccount(RegisterVO RegisterVO);
    public List<FriendsVO> findFriendsById(int id);
}
