package com.example.chatchatbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.chatchatbackend.entity.dto.Message;
import com.example.chatchatbackend.entity.vo.response.MessageVO;

import java.util.List;

public interface MessageService extends IService<Message> {
    public String getLastMessageById(int userId, int sendId);
    public String saveMessageById(int id, int toId, String message);
    public List<MessageVO> getMessage(int id, int sid);
    public List<MessageVO> getAllMessage(int id);
}
