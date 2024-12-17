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
    String userName; // 用户名
    String userAvatar; // 用户头像

    public SendMessageVO() {}

    public Integer getRoomId() {return roomId;}
    public void setRoomId(Integer roomId) {this.roomId = roomId;}

    public Integer getUid() {return uid;}
    public void setUid(Integer uid) {this.uid = uid;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public Message.Content getContent() {return content;}
    public void setContent(Message.Content content) {this.content = content;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getUserAvatar() {return userAvatar;}
    public void setUserAvatar(String userAvatar) {this.userAvatar = userAvatar;}
}
