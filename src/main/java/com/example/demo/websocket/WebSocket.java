package com.example.demo.websocket;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.ChatRecord;
import com.example.demo.entity.ChatUser;
import com.example.demo.mapper.ChatRecordMapper;
import com.example.demo.mapper.ChatUserMapper;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{community}/{userId}")
@Component
public class WebSocket {

    private static ApplicationContext applicationContext;

    private static ChatUserMapper chatUserMapper;

    private static ChatRecordMapper chatRecordMapper;

    // 注入 applicationContext
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocket.applicationContext = applicationContext;
        // 通过 ApplicationContext 获取 ChatUserMapper bean
        WebSocket.chatUserMapper = applicationContext.getBean(ChatUserMapper.class);
        WebSocket.chatRecordMapper = applicationContext.getBean(ChatRecordMapper.class);
    }

    @Getter
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, WebSocket>> communityWebSocketMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, WebSocket>>();

    ConcurrentHashMap<String, WebSocket> userWebMap = new ConcurrentHashMap<String, WebSocket>();

    //实例一个session，这个session是websocket的session
    private Session session;

    //新增一个方法用于主动向客户端发送消息
    public static void sendMessage(Object message, String userId, String community) {
        WebSocket webSocket = communityWebSocketMap.get(community).get(userId);

        if (webSocket != null) {
            try {
                webSocket.session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
                System.out.println("【websocket消息】发送消息成功,用户=" + userId + ",消息内容" + message.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("community") String community, @PathParam("userId") Integer userId) {
        this.session = session;
        String userIdStr = String.valueOf(userId);

        // 检查是否已存在该社区的连接映射
        userWebMap = communityWebSocketMap.get(community);


        if (userWebMap == null) {
            userWebMap = new ConcurrentHashMap<>();
            communityWebSocketMap.put(community, userWebMap);
        }
        userWebMap.put(userIdStr, this);

        ChatUser user = chatUserMapper.selectById(userId);

        JSONObject json = new JSONObject();
        json.put("type", "HintMessage");
        json.put("name", user.getName());
        json.put("head", user.getHead());
        json.put("userId", user.getId());
        json.put("message", "加入 [" + community + "] 社区服务器！");

        // 向社区内所有用户发送消息
        communityWebSocketMap.get(community).forEach((key, value) -> {
            sendMessage(json, key, community);
        });

        // 查看当前社区的所有用户
        System.out.println("社区：" + community + ":" + communityWebSocketMap.get(community).keySet());
    }

    @OnClose
    public void onClose(@PathParam("community") String community, @PathParam("userId") Integer userId) {
        String userIdStr = String.valueOf(userId);
        ConcurrentHashMap<String, WebSocket> userWebMap = communityWebSocketMap.get(community);

        ChatUser user = chatUserMapper.selectById(userId);

        // 在线人数
        Integer current = userWebMap.size() - 1;

        JSONObject json = new JSONObject();
        json.put("type", "HintMessage");
        json.put("name", user.getName());
        json.put("head", user.getHead());
        json.put("userId", user.getId());
        json.put("message", "离开 [" + community + "] 社区服务器！ 当前在线人数：" + current + "人");

        if (userWebMap != null) {
            userWebMap.remove(userIdStr);

            communityWebSocketMap.get(community).forEach((key, value) -> {
                sendMessage(json, key, community);
            });

            if (userWebMap.isEmpty()) {
                communityWebSocketMap.remove(community);
            }
        }
    }

    @OnMessage
    public void onMessage(String message) {

        System.out.println(message);
        JSONObject jsonMessage = JSONUtil.parseObj(message);
        Integer userId = jsonMessage.getInt("userId");
        String community = jsonMessage.getStr("community");
        String time = jsonMessage.getStr("time");

        String content = jsonMessage.getStr("content");

        ChatUser chatUser = chatUserMapper.selectById(userId);

        System.out.println(chatUser);

        JSONObject json = new JSONObject();
        json.put("type", "UserMessage");
        json.put("name", chatUser.getName());
        json.put("head", chatUser.getHead());
        json.put("userId", chatUser.getId());
        json.put("time", time);
        json.put("message", content);

        ChatRecord chatRecord = new ChatRecord();

        chatRecord.setCommunity(community);
        chatRecord.setMessage(JSONUtil.toJsonStr(json));


        //存消息
        chatRecordMapper.insertMessage(chatRecord);

        communityWebSocketMap.get(community).forEach((key, value) -> {
            System.out.println("key= " + key + " and value= " + value);
            sendMessage(json, key, community);
        });

        if (!message.equals("ping")) {
            System.out.println("【websocket消息】收到客户端发来的消息:" + "UserId" + userId + "Content" + content);
        }
    }
}

