package com.example.chatroom.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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
    int user_id;
    String type;
    String text;
    String url;
    Integer width;
    Integer height;
    LocalDateTime sendTime;
    String userName;
    String userAvatar;

    public MessageDTO() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public int getRoomId() {return roomId;}
    public void setRoomId(int roomId) {this.roomId = roomId;}

    public int getUser_id() {return user_id;}
    public void setUser_id(int user_id) {this.user_id = user_id;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public Integer getWidth() {return width;}
    public void setWidth(Integer width) {this.width = width;}

    public Integer getHeight() {return height;}
    public void setHeight(Integer height) {this.height = height;}

    public LocalDateTime getSendTime() {return sendTime;}
    public void setSendTime(LocalDateTime sendTime) {this.sendTime = sendTime;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getUserAvatar() {return userAvatar;}
    public void setUserAvatar(String userAvatar) {this.userAvatar = userAvatar;}

}