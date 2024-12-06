package com.example.chatroom.controller;

import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.Room;
import com.example.chatroom.service.RoomService;
import com.example.chatroom.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public Response<Room> createRoom(@RequestBody RoomDTO roomDTO) {
        try {
            // 调用服务层创建房间
            Room createdRoom = roomService.createRoom(roomDTO);

            // 返回成功响应，直接返回Room实体
            return Response.success("Room created successfully", createdRoom);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error creating room: " + e.getMessage());
        }
    }

    @PostMapping("/join")
    public Response<String> joinRoom(@RequestBody RoomDTO roomDTO) {
        try {
            // 调用服务层加入房间
            // 返回成功响应，直接返回字符串
            return roomService.joinRoom(roomDTO);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error joining room: " + e.getMessage());
        }
    }
}
