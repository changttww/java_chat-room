package com.example.chatchatbackend.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendOneMessageVO {
    int sendId;
    int toId;
    String message;
}
