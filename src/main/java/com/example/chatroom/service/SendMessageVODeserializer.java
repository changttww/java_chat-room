package com.example.chatroom.service;

import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.SendMessageVO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Objects;

public class SendMessageVODeserializer extends JsonDeserializer<SendMessageVO> {

    @Override
    public SendMessageVO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // 使用 JsonParser 获取 JSON 树
        JsonNode node = p.getCodec().readTree(p);

        // 解析顶层字段
        Integer roomId = node.has("roomId") && !node.get("roomId").isNull() ? node.get("roomId").asInt() : null;
        System.out.println("roomId = " + roomId);

        Integer uid = node.has("uid") && !node.get("uid").isNull() ? node.get("uid").asInt() : null;
        System.out.println("uid = " + uid);

        String type = node.has("type") && !node.get("type").isNull() ? node.get("type").asText() : null;
        System.out.println("type = " + type);

        String userName = node.has("userName") && !node.get("userName").isNull() ? node.get("userName").asText() : null;
        System.out.println("userName = " + userName);

        String userAvatar = node.has("userAvatar") && !node.get("userAvatar").isNull() ? node.get("userAvatar").asText() : null;
        System.out.println("userAvatar = " + userAvatar);

        // 解析嵌套对象 content

        // 解析嵌套对象 content
        JsonNode contentNode = node.get("content");
        Message.Content content = null;
        if (contentNode != null && !contentNode.isNull()) {
            content = new Message.Content();

            if(Objects.equals(type, "TEXT")){
                content.setText(contentNode.get("text").asText());
                System.out.println("text = " + content.getText());

                content.setUrl(null);
                content.setMeta(null);
            }else if (contentNode.has("url") && !contentNode.get("url").isNull()) {
                content.setText(null);

                content.setUrl(contentNode.get("url").asText());
                System.out.println("url = " + content.getUrl());

                // 解析嵌套的 meta
                JsonNode metaNode = contentNode.get("meta");
                Message.Content.Meta meta = null;
                if (metaNode != null && !metaNode.isNull()) {
                    meta = new Message.Content.Meta();
                    if (metaNode.has("width")) {
                        meta.setWidth(metaNode.get("width").asInt());
                        System.out.println("width = " + meta.getWidth());
                    }
                    if (metaNode.has("height")) {
                        meta.setHeight(metaNode.get("height").asInt());
                        System.out.println("height = " + meta.getHeight());
                    }
                    content.setMeta(meta);
                }
            }
        }

        // 构造并返回 SendMessageVO 对象
        SendMessageVO sendMessageVO = new SendMessageVO();
        sendMessageVO.setRoomId(roomId);
        sendMessageVO.setUid(uid);
        sendMessageVO.setType(type);
        sendMessageVO.setContent(content);
        sendMessageVO.setUserName(userName);
        sendMessageVO.setUserAvatar(userAvatar);
        return sendMessageVO;
    }
}
