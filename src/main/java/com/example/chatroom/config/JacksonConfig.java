package com.example.chatroom.config;

import com.example.chatroom.entity.SendMessageVO;
import com.example.chatroom.service.aboutMsg.SendMessageVODeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(SendMessageVO.class, new SendMessageVODeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
