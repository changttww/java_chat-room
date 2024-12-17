package com.example.chatroom.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.chatroom.entity.Message;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Table(name="messages")
@AllArgsConstructor
@Data
public class MessageDTO {
    @TableId(type = IdType.AUTO)
    Integer id;

    int roomId;
    int uid;
    String type;
    Message.Content content;
    LocalDateTime sendTime;
    Integer userName;
    Integer userAvatar;

    public MessageDTO() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public int getRoomId() {return roomId;}
    public void setRoomId(int roomId) {this.roomId = roomId;}

    public int getUid() {return uid;}
    public void setUid(int uid) {this.uid = uid;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public Message.Content getContent() {return content;}
    public void setContent(Message.Content content) {this.content = content;}

    public LocalDateTime getSendTime() {return sendTime;}
    public void setSendTime(LocalDateTime sendTime) {this.sendTime = sendTime;}

    public Integer getUserName() {return userName;}
    public void setUserName(Integer userName) {this.userName = userName;}

    public Integer getUserAvatar() {return userAvatar;}
    public void setUserAvatar(Integer userAvatar) {this.userAvatar = userAvatar;}

}