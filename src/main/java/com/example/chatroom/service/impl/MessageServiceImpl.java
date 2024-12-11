// MessageService
package com.example.chatroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chatroom.entity.DTO.Message;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.entity.vo.response.MessageVO;
import com.example.chatroom.mapper.MessageMapper;
import com.example.chatroom.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageRepository {

    /**
     * 保存聊天信息
     * @param sendMessageVO 发送来的消息
     * @return success null
     */
    @Override
    public String saveMessage(SendMessageVO sendMessageVO) {
        Message message = new Message(
                sendMessageVO.getRoomId(),
                sendMessageVO.getUid(),
                sendMessageVO.getType(),
                sendMessageVO.getContent(),
                sendMessageVO.getUserName(),
                sendMessageVO.getUserAvatar(),
                LocalDateTime.now()
        );
        if (!this.save(message)) {
            return "消息保存失败";
        }
        return null;
    }

    /**
     * 获取群聊信息
     * @param roomId 房间号
     * @return 群聊信息
     */
    @Override
    public List<MessageVO> getMessagesSince(int roomId, LocalDateTime since) {
        List<Message> messages = this.query()
                .eq("roomId", roomId)
                .ge("send_time", since)
                .list();
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message message : messages) {
            messageVOList.add(new MessageVO(
                    message.getRoomId(),
                    message.getUid(),
                    message.getType(),
                    message.getContent(),
                    message.getSendTime(),
                    message.getUserName(),
                    message.getUserAvatar()
            ));
        }
        return messageVOList;
    }
}
