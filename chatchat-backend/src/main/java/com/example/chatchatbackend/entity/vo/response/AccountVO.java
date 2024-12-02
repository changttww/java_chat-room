package com.example.chatchatbackend.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AccountVO {
    Integer id;
    String username;
    String role;
    String avatar;
    Date registerTime;
}
