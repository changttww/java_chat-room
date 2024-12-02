package com.example.chatchatbackend.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_message")
@AllArgsConstructor
public class Message {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer sendId;
    Integer toId;
    String message;

}
