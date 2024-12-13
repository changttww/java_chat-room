package com.example.chatroom.entity.vo.request;

import com.example.chatroom.entity.vo.response.MessageVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageVO {
    Integer roomId;   // 房间 ID
    Integer uid;      // 用户 ID
    Integer type;     // 消息类型 (TEXT, IMAGE, EMOJI)
    MessageVO.Content content; // 消息内容
    Integer userName; // 用户名
    Integer userAvatar; // 用户头像

    public Integer getRoomId() {return roomId;}
    public Integer getUid() {return uid;}
    public Integer getType() {return type;}
    public MessageVO.Content getContent() {return content;}
    public Integer getUserName() {return userName;}
    public Integer getUserAvatar() {return userAvatar;}
}
