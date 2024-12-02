package com.example.chatchatbackend.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageVO {
    int id;
    int sendId;
    int toId;
    String message;
}
