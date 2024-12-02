package com.example.chatchatbackend.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendsVO {
    int id;
    String username;
    String avatar;
    String message;
}
