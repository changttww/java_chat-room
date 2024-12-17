package com.example.chatroom.WebSocket;

import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Slf4j

public class WebSocketServerTest {

    @Test
    public void testOnOpen() {
        WebSocketServer webSocketServer = new WebSocketServer();
        Session mockSession = mock(Session.class);
        webSocketServer.onOpen(mockSession, 1, 729);

        Map<Integer, Session> room = WebSocketServer.roomSessions.get(1);
        assert room != null && room.containsKey(729);
    }

    @Test
    public void testSendMessageToRoom() throws IOException {
        WebSocketServer webSocketServer = new WebSocketServer();
        Session mockSession = mock(Session.class);
        when(mockSession.isOpen()).thenReturn(true);

        // 模拟房间连接
        WebSocketServer.roomSessions.put(1, Map.of(729, mockSession));

        // 构造消息对象
        SendMessageVO vo = new SendMessageVO();
        Message.Content content = new Message.Content();
        vo.setRoomId(1);
        vo.setUid(729);
        vo.setType("TEXT");
        vo.setContent(content);
        content.setText("hello");
        vo.setUserName("UserA");
        vo.setUserAvatar("https://example.com/picture.jpg");


        // 设置消息内容等字段...

        webSocketServer.sendMessageToRoom(1, vo);

        // 验证消息发送逻辑
        verify(mockSession, times(1)).getBasicRemote();
    }
}
