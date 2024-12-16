package com.example.chatroom.service;

import com.example.chatroom.entity.DTO.MessageDTO;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.entity.vo.response.MessageVO;
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
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setRoomId(sendMessageVO.getRoomId());
        messageDTO.setUid(sendMessageVO.getUid());
        messageDTO.setType(sendMessageVO.getType());
        messageDTO.setContent(sendMessageVO.getContent());
        messageDTO.setSendTime(LocalDateTime.now());
        messageDTO.setUserName(sendMessageVO.getUserName());
        messageDTO.setUserAvatar(sendMessageVO.getUserAvatar());

        try {
            messageRepository.saveAndFlush(messageDTO);
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
    public List<MessageVO> getMessagesSince(int roomId, LocalDateTime since) {
        List<MessageDTO> messages = messageRepository.findByRoomIdAndSendTimeAfter(roomId, since);

        List<MessageVO> messageVOList = new ArrayList<>();
        for (MessageDTO messageDTO : messages) {
            MessageVO messageVO = new MessageVO();
            messageVO.setRoomId(messageDTO.getRoomId());
            messageVO.setUid(messageDTO.getUid());
            messageVO.setType(messageDTO.getType());
            messageVO.setContent(messageDTO.getContent());
            messageVO.setSendTime(messageDTO.getSendTime());
            messageVO.setUserName(messageDTO.getUserName());
            messageVO.setUserAvatar(messageDTO.getUserAvatar());

            messageVOList.add(messageVO);
        }
        return messageVOList;
    }
}
