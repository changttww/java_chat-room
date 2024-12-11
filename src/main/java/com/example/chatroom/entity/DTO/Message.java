package com.example.chatroom.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

import com.example.chatroom.entity.vo.request.SendMessageVO;

@Table(name="messages")
@AllArgsConstructor
@Data
public class Message {
    @TableId(type = IdType.AUTO)

    int roomId;
    int uid;
    String type;
    SendMessageVO.Content content;
    String userName;
    String userAvatar;
    LocalDateTime sendTime;
}
