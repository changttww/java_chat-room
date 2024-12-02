package com.example.chatchatbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chatchatbackend.entity.dto.Message;
import com.example.chatchatbackend.entity.vo.response.MessageVO;
import com.example.chatchatbackend.mapper.MessageMapper;
import com.example.chatchatbackend.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    /**
     * 根据用户id与发送者id查询最新消息
     * @param userId 用户id
     * @param sendId 发送者id
     * @return 最新消息
     */
    @Override
    public String getLastMessageById(int userId, int sendId) {
        List<Message> messages =  this.query()
                .eq("to_id", userId)
                .eq("send_id", sendId)
                .or(i -> i.eq("to_id", sendId).eq("send_id", userId))
                .list();
        if (messages.isEmpty())
            return null;
        return messages.get(messages.size() - 1).getMessage();
    }

    /**
     * 保存聊天信息
     * @param id 用户id
     * @param toId 去往id
     * @param message 聊天信息
     * @return success null
     */
    @Override
    public String saveMessageById(int id, int toId, String message) {
        if(!this.save(new Message(null, id, toId, message)))
            return "信息保存失败";
        return null;
    }

    /**
     * 获取聊天信息
     * @param id 用户id
     * @param sid 发送者id
     * @return 聊天信息表
     */
    @Override
    public List<MessageVO> getMessage(int id, int sid) {
        List<Message> messages = this.query()
                .eq("send_id", id)
                .eq("to_id", sid)
                .or(i -> i.eq("to_id", id).eq("send_id", sid))
                .list();
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message message : messages) {
            messageVOList.add(new MessageVO(message.getId(),message.getSendId(),message.getToId(),message.getMessage()));
        }
        return messageVOList;
    }

    /**
     * 获取群聊信息
     * @param id 用户id
     * @return 群聊信息
     */
    @Override
    public List<MessageVO> getAllMessage(int id) {
        List<Message> messages = this.query()
                .eq("to_id", 0)
                .list();
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message message : messages) {
            messageVOList.add(new MessageVO(message.getId(),message.getSendId(),message.getToId(),message.getMessage()));
        }
        return messageVOList;
    }
}
