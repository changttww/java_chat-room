package com.example.demo.serivce;

import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.ChatUser;
import com.example.demo.mapper.ChatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ChatUserService {

    private ChatUserMapper chatUserMapper;


    @Autowired
    public ChatUserService(ChatUserMapper chatUserMapper) {
        this.chatUserMapper = chatUserMapper;
    }


    public ResultBody<String> register(@RequestBody ChatUser user) {
        return this.chatUserMapper.register(user) > 0 ? ResultBody.success("uid="+user.getId()) : ResultBody.error("id_error");
    }

    public ResultBody<ChatUser> login(ChatUser user) {
        ChatUser u = this.chatUserMapper.login(user);
        System.out.println(u);
        if (u == null) {
            return ResultBody.error();
        }
        return ResultBody.success(u);
    }

}