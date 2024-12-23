package com.example.chatroom.service;

import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.DTO.MessageDTO;
import com.example.chatroom.entity.SendMessageVO;
import com.example.chatroom.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

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
    public Message saveMessage(SendMessageVO sendMessageVO) {
        Message message = new Message();
        message.setRoomId(sendMessageVO.getRoomId());
        message.setUid(sendMessageVO.getUid());
        message.setType(sendMessageVO.getType());
        message.setContent(sendMessageVO.getContent());
        message.setSendTime(LocalDateTime.now());
        message.setUserName(sendMessageVO.getUserName());
        message.setUserAvatar(sendMessageVO.getUserAvatar());

        messageRepository.saveAndFlush(message);
        return message;
    }

    /**
     * 获取群聊信息
     *
     * @param roomId 房间号
     * @param since  起始时间
     * @return 群聊信息
     */
    public List<Message> getMessagesSince(int roomId, LocalDateTime since) {
        System.out.println("roomId = " + roomId + ", since = " + since);
        List<Message> messages;

        if(since == null) {
            messages = messageRepository.findByRoomId(roomId);
        }else{
            messages = messageRepository.findByRoomIdAndSendTimeAfter(roomId, since);
        }

        /*List<Message> messageVOList = new ArrayList<>();
        for (MessageDTO messageDTO : messages) {
            Message messageVO = new Message();
            Message.Content content = new Message.Content();
            Message.Content.Meta meta = null;

            messageVO.setRoomId(messageDTO.getRoomId());
            messageVO.setUid(messageDTO.getUser_id());
            messageVO.setType(messageDTO.getType());

            content.setText(messageDTO.getText());
            content.setUrl(messageDTO.getUrl());
            messageVO.setContent(content);

            if(Objects.equals(messageVO.getType(), "IMAGE")){
                meta = new Message.Content.Meta();
                meta.setWidth(messageDTO.getWidth());
                meta.setHeight(messageDTO.getHeight());
            }
            content.setMeta(meta);

            messageVO.setSendTime(messageDTO.getSendTime());
            messageVO.setUserName(messageDTO.getUserName());
            messageVO.setUserAvatar(messageDTO.getUserAvatar());

            messageVOList.add(messageVO);
        }*/
        return messages;
    }

    /**
     * 获取群聊信息
     *
     * @param roomId 房间号
     * @param since  起始时间
     * @return 群聊信息
     */
    public List<String> getMessagesStringSince(int roomId, LocalDateTime since) {
        System.out.println("roomId = " + roomId + ", since = " + since);

        List<String> messagesList = new ArrayList<>();

        List<Message> messages;
        if(since == null) {
            messages = messageRepository.findByRoomId(roomId);
        }else{
            messages = messageRepository.findByRoomIdAndSendTimeAfter(roomId, since);
        }

        for (Message message : messages) {
            String messageString = message.toString();
            messagesList.add(messageString);
        }

        return messagesList;
    }

    /**
     * 获取群聊信息
     *
     * @param roomId 房间号
     * @param since  起始时间
     * @return 群聊信息
     */
    public List<MessageDTO> getMessageDTOsSince(int roomId, LocalDateTime since) {
        System.out.println("roomId = " + roomId + ", since = " + since);
        List<Message> messages;

        if(since == null) {
            messages = messageRepository.findByRoomId(roomId);
        }else{
            messages = messageRepository.findByRoomIdAndSendTimeAfter(roomId, since);
        }

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            Message.Content content = message.getContent();

            messageDTO.setRoomId(message.getRoomId());
            messageDTO.setUid(message.getUid());
            messageDTO.setType(message.getType());

            MessageDTO.Content contentDTO = new MessageDTO.Content();
            messageDTO.setContent(contentDTO);

            if(Objects.equals(messageDTO.getType(),"TEXT")){
                contentDTO.setText(content.getText());
            }

            if(Objects.equals(messageDTO.getType(), "IMAGE") || Objects.equals(messageDTO.getType(), "EMOJI")){
                contentDTO.setUrl(content.getUrl());

                Message.Content.Meta meta = content.getMeta();
                MessageDTO.Content.Meta metaDTO = new MessageDTO.Content.Meta();
                contentDTO.setMeta(metaDTO);

                metaDTO.setWidth(meta.getWidth());
                metaDTO.setHeight(meta.getHeight());
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = message.getSendTime().format(formatter);
            messageDTO.setSendTime(time);

            messageDTO.setUserName(message.getUserName());
            messageDTO.setUserAvatar(message.getUserAvatar());

            messageDTOList.add(messageDTO);
        }
        return messageDTOList;
    }
}
