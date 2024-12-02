package com.example.demo.serivce;


import cn.hutool.json.JSONUtil;
import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.ChatRecord;
import com.example.demo.mapper.ChatRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.response.ResultBody.success;

@Service
public class ChatRecordService {


    private ChatRecordMapper chatRecordMapper;

    @Autowired
    public ChatRecordService(ChatRecordMapper chatRecordMapper) {
        this.chatRecordMapper = chatRecordMapper;
    }

    public ResultBody messageRecordByCommunity(String community) {
       List<ChatRecord> cr =  this.chatRecordMapper.messageRecordByCommunity(community);
        return ResultBody.success(cr);
    }
}
