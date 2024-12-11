package com.example.chatroom.entity.vo.response;

import com.example.chatroom.entity.vo.request.SendMessageVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageVO {
    int roomId;       // 房间 ID
    int uid;          // 用户 ID
    String type;         // 消息类型
    SendMessageVO.Content content; // 消息内容
    LocalDateTime sendTime; // 消息发送时间
    String userName;     // 用户名
    String userAvatar;   // 用户头像

}
