package com.example.chatroom.service;

import com.example.chatroom.entity.DTO.MessageDTO;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.entity.Message;
import com.example.chatroom.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * 保存聊天信息
     *
     * @param sendMessageVO 发送来的消息
     * @return success null
     */
    public String saveMessage(SendMessageVO sendMessageVO) {
        Message message = new Message();
        message.setRoomId(sendMessageVO.getRoomId());
        message.setUid(sendMessageVO.getUid());
        message.setType(sendMessageVO.getType());
        message.setContent(sendMessageVO.getContent());
        message.setSendTime(LocalDateTime.now());
        message.setUserName(sendMessageVO.getUserName());
        message.setUserAvatar(sendMessageVO.getUserAvatar());

        try {
            messageRepository.saveAndFlush(message);
            return null;
        } catch (Exception e) {
            return "消息保存失败: " + e.getMessage();
        }
    }

    /**
     * 获取群聊信息
     *
     * @param roomId 房间号
     * @param since  起始时间
     * @return 群聊信息
     */
    public List<Message> getMessagesSince(int roomId, LocalDateTime since) {
        List<MessageDTO> messages = messageRepository.findByRoomIdAndSendTimeAfter(roomId, since);

        List<Message> messageList = new ArrayList<>();
        for (MessageDTO messageDTO : messages) {
            Message message = new Message();
            message.setRoomId(messageDTO.getRoomId());
            message.setUid(messageDTO.getUid());
            message.setType(messageDTO.getType());
            message.setContent(messageDTO.getContent());
            message.setSendTime(messageDTO.getSendTime());
            message.setUserName(messageDTO.getUserName());
            message.setUserAvatar(messageDTO.getUserAvatar());

            messageList.add(message);
        }
        return messageList;
    }
}
