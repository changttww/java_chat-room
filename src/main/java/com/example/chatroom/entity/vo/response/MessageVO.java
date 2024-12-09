package com.example.chatroom.entity.vo.response;

import com.example.chatroom.entity.vo.request.SendMessageVO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageVO {
    private String roomId;       // 房间 ID
    private String uid;          // 用户 ID
    private String type;         // 消息类型
    private SendMessageVO.Content content; // 消息内容
    private LocalDateTime sendTime; // 消息发送时间
    private String userName;     // 用户名
    private String userAvatar;   // 用户头像
}
