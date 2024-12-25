package com.example.chatroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.chatroom.entity","package com.example.chatroom.entity.vo.response"})
public class ChatroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomApplication.class, args);
    }
}
