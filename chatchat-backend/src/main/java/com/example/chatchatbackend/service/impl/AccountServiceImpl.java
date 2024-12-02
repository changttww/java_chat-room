package com.example.chatchatbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chatchatbackend.entity.dto.Account;
import com.example.chatchatbackend.entity.vo.request.RegisterVO;
import com.example.chatchatbackend.entity.vo.response.FriendsVO;
import com.example.chatchatbackend.mapper.AccountMapper;
import com.example.chatchatbackend.service.AccountService;
import com.example.chatchatbackend.service.MessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Resource
    PasswordEncoder encoder;
    @Resource
    MessageService messageService;

    /**
     * 实现UserDetailsService,实现用户登录
     * @param username 账户名
     * @return UserDetails
     * @throws UsernameNotFoundException 用户未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByUsername(username);
        if(account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    /**
     * 根据用户名查询对应账户
     * @param username 账户名
     * @return Account账户
     */
    @Override
    public Account findAccountByUsername(String username) {
        return this.query()
                .eq("username", username)
                .one();
    }

    /**
     * 账户注册
     * @param registerVO 注册实体类vo
     * @return success null
     */
    @Override
    public String registerAccount(RegisterVO registerVO) {
        String password = encoder.encode(registerVO.getPassword());
        Account account = new Account(null, registerVO.getUsername(), password, registerVO.getEmail(),
                "user", null, new Date());
        if(this.save(account))
            return null;
        return "内部错误，请联系管理员";
    }

    /**
     * 根据用户id查询好友列表
     * @param id 用户id
     * @return 好友列表
     */
    @Override
    public List<FriendsVO> findFriendsById(int id) {
        List<Account> accounts = this.query()
                .ne("id", id)
                .list();
        List<FriendsVO> friendsVOS = new ArrayList<>();
        for (Account account : accounts) {
            String message = messageService.getLastMessageById(id, account.getId());
            friendsVOS.add(new FriendsVO(account.getId(), account.getUsername(), account.getAvatar(), message));
        }
        return friendsVOS;
    }
}
