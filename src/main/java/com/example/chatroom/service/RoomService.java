package com.example.chatroom.service;

import com.example.chatroom.common.response.Response;
import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.Room;
import com.example.chatroom.entity.RoomMember;
import com.example.chatroom.entity.User;
import com.example.chatroom.repository.RoomMemberRepository;
import com.example.chatroom.repository.RoomRepository;
import com.example.chatroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMemberRepository roomMemberRepository;  // 假设你有一个房间成员表


    // 通过用户名获取用户ID
    private Integer getCurrentUserId() {
        // 获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  // 获取当前用户名

            // 通过用户名查询用户实体
            Optional<User> userOptional = UserRepository.findByUsername(username);

            if (userOptional.isPresent() ) {
                User user = userOptional.get();
                return user.getId();  // 返回用户的ID
            } else {
                throw new RuntimeException("User not found");
            }
        }
        throw new RuntimeException("User is not authenticated");
    }

    private User getCurrentUser() {
        // 获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();  // 获取当前用户名

            // 通过用户名查询用户实体
            Optional<User> userOptional = UserRepository.findByUsername(username);

            if (userOptional.isPresent() ) {
                User user = userOptional.get();
                return user;  // 返回用户的ID
            } else {
                throw new RuntimeException("User not found");
            }
        }
        throw new RuntimeException("User is not authenticated");
    }

    // 创建房间
    public Room createRoom(RoomDTO roomDTO) {
        Integer currentUserId = getCurrentUserId();
        Room room = new Room();
        room.setOwnerUid(currentUserId);
        room.setRoomType(roomDTO.getRoomType());
        room.setRoomName(roomDTO.getRoomName());
        room.setMaxMembers(roomDTO.getMaxMembers());
        room.setInviteCode("group".equals(roomDTO.getRoomType()) ? UUID.randomUUID().toString().substring(0, 8) : null);
        room.setHead(roomDTO.getHead());

        // Save room and add the current user and receiver (if private)
        room = roomRepository.save(room);

        if ("private".equalsIgnoreCase(room.getRoomType())) {
            // Add the current user and receiver to the room
            RoomMember roomMember = new RoomMember();
            roomMember.setRoom(room);  // 设置房间
            Optional <User>userOptional = UserRepository.findById(roomDTO.getReceiverUid());
            if (userOptional.isEmpty()) {
                throw new RuntimeException("User not found");
            }
            roomMember.setUser(userOptional.get());  // 设置用户
            roomMember.setJoinedAt(new Timestamp(System.currentTimeMillis()));  // 设置加入时间

            roomMemberRepository.save(roomMember);  // 保存成员到数据库
        }
        return room;
    }

    public Response<String> joinRoom(RoomDTO roomDTO) {
        // 查找房间
        Optional<Room> roomOptional = roomRepository.findByRoomId(roomDTO.getRoomId());
        if (roomOptional.isEmpty()) {
            return Response.error("Room not found");
        }

        Room room = roomOptional.get();

        // 如果是群聊类型，需要验证邀请码
        if ("group".equals(room.getRoomType()) && (roomDTO.getInviteCode() == null || !room.getInviteCode().equals(roomDTO.getInviteCode()))) {
            return Response.error("Invalid invite code");
        }


        Integer currentUserId = getCurrentUserId();
        // 检查用户是否已经是房间成员
        Optional<RoomMember> roomMemberOptional = roomMemberRepository.findByRoom_RoomIdAndUserId(room.getRoomId(), currentUserId);
        if (roomMemberOptional.isPresent()) {
            return Response.error("User already a member of the room");
        }

        // 添加用户到房间成员列表
        RoomMember roomMember = new RoomMember();
        roomMember.setRoom(room);
        roomMember.setUser(getCurrentUser());
        roomMember.setJoinedAt(new Timestamp(System.currentTimeMillis()));
        roomMemberRepository.save(roomMember);

        return Response.success("User joined room successfully",null);
    }


}
