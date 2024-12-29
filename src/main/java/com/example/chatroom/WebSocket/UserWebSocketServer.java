package com.example.chatroom.WebSocket;

import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.SendMessageVO;
import com.example.chatroom.repository.MessageRepository;
import com.example.chatroom.service.MessageService;
import com.example.chatroom.context.SpringContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configurable
@Component
@ServerEndpoint("/ws/{uid}")
public class UserWebSocketServer {

    @Autowired
    private MessageRepository messageRepository = SpringContext.getBean(MessageRepository.class);
    @Autowired
    MessageService messageService = new MessageService(messageRepository);

    // 映射表
    static final Map<Integer, Session> userSessions = new ConcurrentHashMap<>();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * WebSocket 连接建立时触发
     * @param session WebSocket 会话
     * @param uid 用户 ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") int uid) {
        userSessions.put(uid, session);
        System.out.println("用户 " + uid + " 建立连接 ");
    }

    /**
     * WebSocket 接收到消息时触发
     * @param message 消息内容
     * @param session WebSocket 会话
     * @param uid 用户 ID
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("uid") int uid) throws JsonProcessingException {
        System.out.println("收到来自用户 " + uid + " 的消息");
        // 这里处理消息，转发存储
        SendMessageVO sendMessageVO = objectMapper.readValue(message, SendMessageVO.class);
        int toUid = Integer.parseInt(sendMessageVO.getContent().getText());
        Message message1 = messageService.convertSendMessageVO2Message(sendMessageVO);
        sendMessageToUser(toUid, message1);
        sendMessageToUser(uid, message1);
    }

    /**
     * WebSocket 连接关闭时触发
     * @param session WebSocket 会话
     * @param uid 用户 ID
     */
    @OnClose
    public void onClose(Session session, @PathParam("uid") int uid) {
        userSessions.remove(uid);
        System.out.println("用户 " + uid + " 离开 ");
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
     * 向指定用户广播消息
     * @param toUid 发送对象
     * @param message 消息对象
     */
    public void sendMessageToUser(int toUid, Message message) {
        Session session = userSessions.get(toUid);
        if (session == null) {
            System.out.println("用户" + toUid + "没有活跃的连接。");
            return;
        }

        try {
            // 序列化为 JSON
            String messageJson = message.toString();
            System.out.println(messageJson);

            // 广播消息
            synchronized (session) {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(messageJson);
                }
            }
        } catch (IOException e) {
            System.err.println("向用户" + toUid + "发送消息失败: " + e.getMessage());
        }
    }
}
