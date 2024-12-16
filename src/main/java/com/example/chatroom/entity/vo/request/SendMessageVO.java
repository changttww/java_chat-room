package com.example.chatroom.entity.vo.request;

import com.example.chatroom.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageVO {
    Integer roomId;   // 房间 ID
    Integer uid;      // 用户 ID
    String type;     // 消息类型 (TEXT, IMAGE, EMOJI)
    Message.Content content; // 消息内容
    Integer userName; // 用户名
    Integer userAvatar; // 用户头像

    public Integer getRoomId() {return roomId;}
    public Integer getUid() {return uid;}
    public String getType() {return type;}
    public Message.Content getContent() {return content;}
    public Integer getUserName() {return userName;}
    public Integer getUserAvatar() {return userAvatar;}
}
