package com.example.chatroom.controller;

import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.RoomTag;
import com.example.chatroom.repository.MessageRepository;
import com.example.chatroom.entity.Room;
import com.example.chatroom.service.RoomService;
import com.example.chatroom.WebSocket.WebSocketServer;
import com.example.chatroom.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{roomId}")
    public Response<RoomDTO> getRoomDetails(@PathVariable("roomId") Integer roomId) {
        try {
            // 调用服务层获取房间详情
            RoomDTO roomDetails = roomService.getRoomDetails(roomId);

            // 返回成功响应，返回房间详情
            return Response.success("Room details fetched successfully", roomDetails);
        } catch (Exception e) {
            // 如果发生错误，返回失败响应
            return Response.error("Error fetching room details: " + e.getMessage());
        }
    }

    @PostMapping("/{roomId}/leave")
    public Response<String> leaveRoom(@PathVariable("roomId") Integer roomId) {
        try {
            // 调用 Service 层的离开房间方法
            String result = roomService.leaveRoom(roomId);
            return Response.success(result, null);
        } catch (Exception e) {
            return Response.error("Error leaving room: " + e.getMessage());
        }
    }


    @DeleteMapping("/{roomId}")
    public Response<String> deleteRoom(@PathVariable("roomId") Integer roomId) {
        try {
            // 调用 Service 层的删除房间方法
            String result = roomService.deleteRoom(roomId);
            return Response.success(result, null);
        } catch (Exception e) {
            return Response.error("Error deleting room: " + e.getMessage());
        }
    }

    @PutMapping("/{roomId}/update")
    public Response<String> updateRoom(@PathVariable("roomId") Integer roomId, @RequestBody RoomDTO roomDTO) {
        try {
            // 调用服务层进行房间更新
            roomService.updateRoomInfo(roomId, roomDTO);
            return Response.success(null,"Room updated successfully");
        } catch (Exception e) {
            return Response.error("Error updating room: " + e.getMessage());
        }
    }

    @PostMapping("/{roomId}/transfer-ownership")
    public Response<String> transferOwnership(@PathVariable("roomId") Integer roomId, @RequestBody Map<String, Integer> request) {
        try {
            Integer newOwnerId = request.get("newOwnerId");
            roomService.transferOwnership(roomId, newOwnerId);
            return Response.success(null,"Ownership transferred successfully");
        } catch (Exception e) {
            return Response.error("Error transferring ownership: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public Response<List<Room>> searchRooms(@RequestBody RoomDTO roomDTO) {
        try {
            List<Room> rooms = roomService.searchRooms(roomDTO);
            return Response.success("Rooms fetched successfully", rooms);
        } catch (Exception e) {
            return Response.error("Error searching rooms: " + e.getMessage());
        }
    }

    @GetMapping("/myroom-search")
    public Response<List<Room>> searchMyRooms(@RequestBody RoomDTO roomDTO) {
        try {
            List<Room> rooms = roomService.searchRooms(roomDTO);
            return Response.success("Rooms fetched successfully", rooms);
        } catch (Exception e) {
            return Response.error("Error searching rooms: " + e.getMessage());
        }
    }

    @GetMapping("/my-rooms")
    public Response<List<Room>> getMyRooms() {
        try {
            List<Room> rooms = roomService.getMyRooms();
            return Response.success("Rooms fetched successfully", rooms);
        } catch (Exception e) {
            return Response.error("Error fetching rooms: " + e.getMessage());
        }
    }

    @PostMapping("/{roomId}/remove-member")
    public Response<String> removeMember(@PathVariable("roomId") Integer roomId, @RequestBody Map<String, Integer> request) {
        try {
            Integer userId = request.get("userId");
            roomService.removeMember(roomId, userId);
            return Response.success(null,"Member removed successfully");
        } catch (Exception e) {
            return Response.error("Error removing member: " + e.getMessage());
        }
    }

    @PostMapping("/{roomId}/mute-member")
    public Response<String> muteMember(@PathVariable("roomId") Integer roomId, @RequestBody Map<String, Object> request) {
        try {
            Integer userId = (Integer) request.get("userId");
            Integer durationMinutes = (Integer) request.get("durationMinutes");
            roomService.muteMember(roomId, userId, durationMinutes);
            return Response.success(null,"Member muted successfully");
        } catch (Exception e) {
            return Response.error("Error muting member: " + e.getMessage());
        }
    }

    //获得6个推荐房间
    @GetMapping("/get6rooms")
    public Response<List<Room>> get6RecommendedRooms() {
        List<Room> rooms = roomService.get6RecommendedRooms();
        return Response.success("Recommended rooms fetched successfully", rooms);
    }

    // 2.10. Get all tags
    @GetMapping("/tags")
    public Response<List<String>> getAllTags() {
        try {
            List<String> tags = roomService.getAllTags();
            return Response.success("Tags fetched successfully", tags);
        } catch (Exception e) {
            return Response.error("Error fetching tags: " + e.getMessage());
        }
    }

    // 2.11. Get all rooms the user has joined with basic information
    @GetMapping("/room-choose")
    public Response<List<RoomDTO>> getUserJoinedRooms(@RequestHeader("Authorization") String token) {
        try {
            List<RoomDTO> rooms = roomService.getUserJoinedRooms();
            return Response.success("Rooms fetched successfully", rooms);
        } catch (Exception e) {
            return Response.error("Error fetching joined rooms: " + e.getMessage());
        }
    }

    // 2.12. Get 20 recommended room tags
    @GetMapping("/sugTags")
    public Response<List<RoomTag>> getTop20RoomTags() {
        try {
            List<RoomTag> tags = roomService.getTop20RoomTags();
            return Response.success("Tags fetched successfully", tags);
        } catch (Exception e) {
            return Response.error("Error fetching suggested tags: " + e.getMessage());
        }
    }

    // 2.13. Get rooms by tag
    @GetMapping("/getRoomsByTag")
    public Response<List<Room>> getRoomsByTag(@RequestParam String tag) {
        try {
            List<Room> rooms = roomService.getRoomsByTag(tag);
            return Response.success("Rooms fetched successfully", rooms);
        } catch (Exception e) {
            return Response.error("Error fetching rooms by tag: " + e.getMessage());
        }
    }





}
