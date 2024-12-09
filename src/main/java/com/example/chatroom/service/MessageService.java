package com.example.chatroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.chatroom.entity.DTO.Message;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.entity.vo.response.MessageVO;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService extends IService<Message> {
    public String saveMessage(SendMessageVO message);
    public List<MessageVO> getMessagesSince(int roomId, LocalDateTime since);
}
