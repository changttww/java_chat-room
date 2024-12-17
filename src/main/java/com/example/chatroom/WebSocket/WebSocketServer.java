package com.example.chatroom.WebSocket;

import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.SendMessageVO;
import com.example.chatroom.repository.MessageRepository;
import com.example.chatroom.service.MessageService;

import com.example.chatroom.service.SendMessageVODeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/chat/{roomId}/{uid}")
public class WebSocketServer {

    private com.example.chatroom.repository.MessageRepository MessageRepository;
    private final MessageService messageService = new MessageService(MessageRepository);

    // 房间与会话的映射表
    static final Map<Integer, Map<Integer, Session>> roomSessions = new ConcurrentHashMap<>();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * WebSocket 连接建立时触发
     * @param session WebSocket 会话
     * @param roomId 房间 ID
     * @param uid 用户 ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") int roomId, @PathParam("uid") int uid) {
        roomSessions.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>()).put(uid, session);
        System.out.println("用户 " + uid + " 加入房间 " + roomId);
    }

    /**
     * WebSocket 接收到消息时触发
     * @param message 消息内容
     * @param session WebSocket 会话
     * @param roomId 房间 ID
     * @param uid 用户 ID
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("roomId") int roomId, @PathParam("uid") int uid) throws JsonProcessingException {
        System.out.println("收到来自用户 " + uid + " 的消息");
        // 这里处理消息，转发存储
        SendMessageVO sendMessageVO = objectMapper.readValue(message, SendMessageVO.class);
        Message message1 = messageService.saveMessage(sendMessageVO);
        sendMessageToRoom(roomId, message1);
    }

    /**
     * WebSocket 连接关闭时触发
     * @param session WebSocket 会话
     * @param roomId 房间 ID
     * @param uid 用户 ID
     */
    @OnClose
    public void onClose(Session session, @PathParam("roomId") int roomId, @PathParam("uid") int uid) {
        Map<Integer, Session> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(uid);
            if (sessions.isEmpty()) {
                roomSessions.remove(roomId);
            }
        }
        System.out.println("用户 " + uid + " 离开房间 " + roomId);
    }

    /**
     * WebSocket 出现错误时触发
     * @param session WebSocket 会话
     * @param throwable 错误信息
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket 发生错误: " + throwable.getMessage());
    }

    /**
     * 向指定房间广播消息
     * @param roomId 房间 ID
     * @param message 消息对象
     */
    public void sendMessageToRoom(int roomId, Message message) {
        Map<Integer, Session> sessions = roomSessions.get(roomId);
        if (sessions == null || sessions.isEmpty()) {
            System.out.println("房间" + roomId + "没有活跃的连接。");
            return;
        }

        try {
            // 构建接收消息的格式
            Map<@NotNull String, @NotNull Object> send = Map.of(
                    "roomId", message.getRoomId(),
                    "uid", message.getUid(),
                    "type", message.getType(),
                    "content", Map.of(
                            "text", message.getContent().getText(),
                            "url", message.getContent().getUrl(),
                            "meta", message.getContent().getMeta() == null ? null : Map.of(
                                    "width", message.getContent().getMeta().getWidth(),
                                    "height", message.getContent().getMeta().getHeight()
                            )
                    ),
                    "sendTime", message.getSendTime(),
                    "userName", message.getUserName(),
                    "userAvatar", message.getUserAvatar()
            );

            // 序列化为 JSON
            String messageJson = objectMapper.writeValueAsString(send);

            // 广播消息
            for (Session session : sessions.values()) {
                synchronized (session) {
                    if (session.isOpen()) {
                        session.getBasicRemote().sendText(messageJson);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("向房间" + roomId + "发送消息失败: " + e.getMessage());
        }
    }
}
