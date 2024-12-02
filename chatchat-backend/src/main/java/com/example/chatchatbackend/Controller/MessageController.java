package com.example.chatchatbackend.Controller;

import com.example.chatchatbackend.entity.RestBean;
import com.example.chatchatbackend.entity.vo.request.SendOneMessageVO;
import com.example.chatchatbackend.entity.vo.response.MessageVO;
import com.example.chatchatbackend.service.MessageService;
import com.example.chatchatbackend.socket.WebSocketServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
    @Resource
    private WebSocketServer webSocketServer;
    @Resource
    MessageService messageService;
    /*后端往前端发送数据*/
    @PostMapping("/sendAllMessage")
    public RestBean<Void> sendAllMessage(@RequestBody SendOneMessageVO vo){
        try {
            webSocketServer.sendAllMessage(vo.getMessage(), String.valueOf(vo.getSendId()));
            messageService.saveMessageById(vo.getSendId(),vo.getToId(),vo.getMessage());
        } catch (Exception e) {
            log.error("群发消息发送失败" + e.getMessage());
            return RestBean.failure(400,"发送消息失败");
        }
        return RestBean.success();
    }

    @PostMapping("/sendOneMessage")
    public RestBean<Void> sendOneMessage(@RequestBody SendOneMessageVO vo){
        try {
            webSocketServer.sendOneMessage(String.valueOf(vo.getToId()),vo.getMessage(), String.valueOf(vo.getSendId()));
            messageService.saveMessageById(vo.getSendId(),vo.getToId(),vo.getMessage());
        } catch (Exception e) {
            log.error("单点发送消息失败" + e.getMessage());
            return RestBean.failure(400, "发送消息失败");
        }
        return RestBean.success();
    }
    @GetMapping("/getMessage")
    public RestBean<List<MessageVO>> getMessage(@RequestParam int id, @RequestParam int sid){
        return RestBean.success(messageService.getMessage(id, sid));
    }
    @GetMapping("/getAllMessage")
    public RestBean<List<MessageVO>> getAllMessage(@RequestParam int id){
        return RestBean.success(messageService.getAllMessage(id));
    }
}
