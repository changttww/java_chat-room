// MessageService
package com.example.chatroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.chatroom.entity.DTO.MessageDTO;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.entity.vo.response.MessageVO;
import com.example.chatroom.mapper.MessageMapper;
import com.example.chatroom.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDTO> implements MessageRepository {

    /**
     * 保存聊天信息
     * @param sendMessageVO 发送来的消息
     * @return success null
     */
    @Override
    public String saveMessage(SendMessageVO sendMessageVO) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(null);
        messageDTO.setRoomId(sendMessageVO.getRoomId());
        messageDTO.setUid(sendMessageVO.getUid());
        messageDTO.setType(sendMessageVO.getType());
        messageDTO.setContent(sendMessageVO.getContent());
        messageDTO.setSendTime(LocalDateTime.now());
        messageDTO.setUserName(sendMessageVO.getUserName());
        messageDTO.setUserAvatar(sendMessageVO.getUserAvatar());



        if (!this.save(messageDTO)) {
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
        List<MessageDTO> messages = this.query()
                .eq("roomId", roomId)
                .ge("send_time", since)
                .list();
        List<MessageVO> messageVOList = new ArrayList<>();
        for (MessageDTO messageDTO : messages) {
            MessageVO messageVO = new MessageVO();
            messageVO.setRoomId(messageDTO.getRoomId());
            messageVO.setUid(messageDTO.getUid());
            messageVO.setType(messageDTO.getType());
            messageVO.setContent(messageDTO.getContent());
            messageDTO.setSendTime(messageDTO.getSendTime());
            messageVO.setUserName(messageDTO.getUserName());
            messageVO.setUserAvatar(messageDTO.getUserAvatar());

            messageVOList.add(messageVO);
        }
        return messageVOList;
    }
}
