package com.example.chatroom.WebSocket;

import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/chat/{roomId}/{userId}")
public class WebSocketServer {

    // 房间与会话的映射表
    private static final Map<Integer, Map<String, Session>> roomSessions = new ConcurrentHashMap<>();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * WebSocket 连接建立时触发
     * @param session WebSocket 会话
     * @param roomId 房间 ID
     * @param userId 用户 ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") int roomId, @PathParam("userId") String userId) {
        roomSessions.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>()).put(userId, session);
        System.out.println("用户 " + userId + " 加入房间 " + roomId);
    }

    /**
     * WebSocket 接收到消息时触发
     * @param message 消息内容
     * @param session WebSocket 会话
     * @param roomId 房间 ID
     * @param userId 用户 ID
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("roomId") int roomId, @PathParam("userId") String userId) {
        System.out.println("收到来自用户 " + userId + " 的消息: " + message);
        // 这里可以处理消息，例如转发或存储
    }

    /**
     * WebSocket 连接关闭时触发
     * @param session WebSocket 会话
     * @param roomId 房间 ID
     * @param userId 用户 ID
     */
    @OnClose
    public void onClose(Session session, @PathParam("roomId") int roomId, @PathParam("userId") String userId) {
        Map<String, Session> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(userId);
            if (sessions.isEmpty()) {
                roomSessions.remove(roomId);
            }
        }
        System.out.println("用户 " + userId + " 离开房间 " + roomId);
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
     * @param vo 消息对象
     */
    public void sendMessageToRoom(int roomId, SendMessageVO vo) {
        Map<String, Session> sessions = roomSessions.get(roomId);
        if (sessions == null || sessions.isEmpty()) {
            System.out.println("房间" + roomId + "没有活跃的连接。");
            return;
        }

        try {
            // 构建接收消息的格式
            Map<@org.jetbrains.annotations.NotNull String, @org.jetbrains.annotations.NotNull Object> message = Map.of(
                    "roomId", vo.getRoomId(),
                    "uid", vo.getUid(),
                    "type", vo.getType(),
                    "content", Map.of(
                            "text", vo.getContent().getText(),
                            "url", vo.getContent().getUrl(),
                            "meta", vo.getContent().getMeta() == null ? null : Map.of(
                                    "width", vo.getContent().getMeta().getWidth(),
                                    "height", vo.getContent().getMeta().getHeight()
                            )
                    ),
                    "sendTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    "userName", vo.getUserName(),
                    "userAvatar", vo.getUserAvatar()
            );

            // 序列化为 JSON
            String messageJson = objectMapper.writeValueAsString(message);

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
