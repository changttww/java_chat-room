package com.example.chatroom.controller;

import com.example.chatroom.common.response.Response;
import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.WebSocket.WebSocketServer;

import com.example.chatroom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    private WebSocketServer webSocketServer;

    //发送消息到房间
    @PostMapping("/send")
    public Response<Void> sendMessage(@RequestBody SendMessageVO vo) {
        try {
            // 推送消息到指定房间
            webSocketServer.sendMessageToRoom(vo.getRoomId(), vo);
            // 保存消息到数据库
            messageService.saveMessage(vo);
        } catch (Exception e) {
            //log.error("消息发送失败: {}", e.getMessage());
            return Response.error("发送消息失败");
        }
        return Response.success("Message sent successfully",null);
    }

    /**
     * 同步消息
     */
    @GetMapping("/sync")
    public Response<List<Message>> syncMessages(@RequestParam int roomId,
                                                  @RequestParam LocalDateTime lastTime) {
        try {
            // 从数据库获取指定房间自 lastTime 以来的消息
            List<Message> messages = messageService.getMessagesSince(roomId, lastTime);
            return Response.success("Messages fetched successfully",messages);
        } catch (Exception e) {
            //log.error("消息同步失败: " + e.getMessage(), e);
            return Response.error("消息同步失败");
        }
    }
}
