package com.example.chatroom.service;

import com.example.chatroom.common.response.Response;
import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.Room;
import com.example.chatroom.entity.RoomMember;
import com.example.chatroom.entity.RoomTag;
import com.example.chatroom.entity.User;
import com.example.chatroom.repository.RoomMemberRepository;
import com.example.chatroom.repository.RoomRepository;
import com.example.chatroom.repository.RoomTagRepository;
import com.example.chatroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMemberRepository roomMemberRepository;  // 假设你有一个房间成员表

    @Autowired
    private RoomTagRepository roomTagRepository;  // 注入 RoomTagRepository



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
        room.setDescription(roomDTO.getDescription());
        room.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        room.setUpdatedAt(new Timestamp(System.currentTimeMillis()));


        // Save room and add the current user and receiver (if private);
        // Save room first and flush to ensure persistence
        room = roomRepository.saveAndFlush(room);

        // 处理房间标签
        if (roomDTO.getTags() != null && !roomDTO.getTags().isEmpty()) {
            for (String tagName : roomDTO.getTags()) {
                // Check if tag already exists

                RoomTag roomTag = new RoomTag();
                    roomTag.setRoom(room);
                    roomTag.setTag(tagName);
                room.getRoomTags().add(roomTag); // 同步更新 Room 的 roomTags 列表
                roomTagRepository.save(roomTag);
            }
        }

        if ("private".equalsIgnoreCase(room.getRoomType())) {
            // 查找所有房间类型为 private 的房间
            List<Room> privateRooms = roomRepository.findByRoomType("private");

            // 遍历所有私聊房间，查找符合条件的房间
            for (Room room1 : privateRooms) {
                Integer roomId = room1.getRoomId();

                // 获取房间成员
                List<RoomMember> roomMembers = roomMemberRepository.findByRoom_RoomId(roomId);
                List<Integer> memberIds = roomMembers.stream()
                        .map(member -> member.getUser().getId())
                        .toList();

                // 判断是否符合条件
                boolean isCurrentUserMember = memberIds.contains(currentUserId);
                boolean isReceiverMember = memberIds.contains(roomDTO.getReceiverUid());

                // 判断情况1：当前用户是创建者且唯一成员是接收者
                if (room.getOwnerUid().equals(currentUserId) && isReceiverMember) {
                    return room1; // 返回现有房间
                }

                // 判断情况2：接收者是创建者且唯一成员是当前用户
                if (room.getOwnerUid().equals(roomDTO.getReceiverUid()) && isCurrentUserMember) {
                    return room1; // 返回现有房间
                }
            }

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

    // 查询房间详情
    public RoomDTO getRoomDetails(Integer roomId) {
        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setOwnerUid(room.getOwnerUid());
        roomDTO.setMaxMembers(room.getMaxMembers());
        roomDTO.setHead(room.getHead());
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setInviteCode(room.getInviteCode());
        roomDTO.setCreatedAt(room.getCreatedAt());
        roomDTO.setUpdatedAt(room.getUpdatedAt());
        roomDTO.setDescription(room.getDescription());

        // 获取房间成员
        List<RoomMember> roomMembers = roomMemberRepository.findByRoom_RoomId(roomId);

        // 构建成员信息列表
        List<Map<String, Object>> members = roomMembers.stream()
                .map(member -> {
                    Map<String, Object> memberInfo = new HashMap<>();
                    memberInfo.put("userId", member.getUser().getId());
                    memberInfo.put("username", member.getUser().getUsername());
                    memberInfo.put("joinedAt", member.getJoinedAt().toString());// 转为字符串格式
                    memberInfo.put("head", member.getUser().getHead());
                    return memberInfo;
                })
                .collect(Collectors.toList());
        roomDTO.setMembers(members); // 设置成员信息列表

        List<RoomTag> roomTags = roomTagRepository.findByRoom_RoomId(roomId);
        roomDTO.setTags(roomTags.stream()
                .map(RoomTag::getTag)
                .collect(Collectors.toList()));

        return roomDTO;
    }

    // 离开房间
    public String leaveRoom(Integer roomId) {
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();

        // 查找房间
        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();

        // 检查用户是否是房间成员
        Optional<RoomMember> roomMemberOptional = roomMemberRepository.findByRoom_RoomIdAndUserId(roomId, currentUserId);
        if (roomMemberOptional.isEmpty()) {
            throw new RuntimeException("User is not a member of the room");
        }

        // 删除用户房间成员记录
        RoomMember roomMember = roomMemberOptional.get();
        roomMemberRepository.delete(roomMember);

        // 如果用户是房主且房间中没有其他成员，解散房间
        if (room.getOwnerUid().equals(currentUserId) && roomMemberRepository.countByRoom_RoomId(roomId) == 0) {
            roomRepository.delete(room); // 删除房间
        }

        return "Left room successfully";
    }

    // 删除房间
    public String deleteRoom(Integer roomId) {
        // 获取当前用户 ID
        Integer currentUserId = getCurrentUserId();

        // 查找房间
        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();

        // 检查是否是房主
        if (!room.getOwnerUid().equals(currentUserId)) {
            throw new RuntimeException("Only the room owner can delete the room");
        }

        // 删除房间成员记录
        List<RoomMember> roomMembers = roomMemberRepository.findByRoom_RoomId(roomId);
        roomMemberRepository.deleteAll(roomMembers);

        // 删除房间
        roomRepository.delete(room);

        return "Room deleted successfully";
    }

    public void updateRoomInfo(Integer roomId,RoomDTO roomDTO) {
        // 获取当前用户ID
        Integer currentUserId = getCurrentUserId();

        // 查找房间
        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();

        // 检查当前用户是否是房主
        if (!room.getOwnerUid().equals(currentUserId)) {
            throw new RuntimeException("You are not the owner of the room");
        }

        // 更新房间信息
        if (roomDTO.getRoomName() != null) {
            room.setRoomName(roomDTO.getRoomName());
        }
        if (roomDTO.getMaxMembers() != null) {
            room.setMaxMembers(roomDTO.getMaxMembers());
        }
        if (roomDTO.getHead() != null) {
            room.setHead(roomDTO.getHead());
        }
        if (roomDTO.getDescription() != null) {
            room.setDescription(roomDTO.getDescription());
        }

        // 保存更新后的房间信息
        roomRepository.save(room);
    }

    public void transferOwnership(Integer roomId, Integer newOwnerId) {
        Integer currentUserId = getCurrentUserId();
        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();

        if (!room.getOwnerUid().equals(currentUserId)) {
            throw new RuntimeException("Only the current owner can transfer ownership");
        }

        Optional<RoomMember> newOwnerOptional = roomMemberRepository.findByRoom_RoomIdAndUserId(roomId, newOwnerId);
        if (newOwnerOptional.isEmpty()) {
            throw new RuntimeException("New owner must be a member of the room");
        }

        room.setOwnerUid(newOwnerId);
        roomRepository.save(room);
    }

    public List<Room> searchRooms(RoomDTO roomDTO) {
        // 查询房间名称包含关键词的房间
        return roomRepository.findByRoomNameContaining(roomDTO.getQuery());
    }


    public List<Room> getMyRooms() {
        Integer currentUserId = getCurrentUserId();
        return roomRepository.findByOwnerUid(currentUserId);
    }

    public void removeMember(Integer roomId, Integer userId) {
        Integer currentUserId = getCurrentUserId();

        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();

        if (!room.getOwnerUid().equals(currentUserId)) {
            throw new RuntimeException("Only the owner can remove members");
        }

        Optional<RoomMember> memberOptional = roomMemberRepository.findByRoom_RoomIdAndUserId(roomId, userId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found in the room");
        }

        roomMemberRepository.delete(memberOptional.get());
    }

    public void muteMember(Integer roomId, Integer userId, Integer durationMinutes) {
        Integer currentUserId = getCurrentUserId();

        Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        Room room = roomOptional.get();

        if (!room.getOwnerUid().equals(currentUserId)) {
            throw new RuntimeException("Only the owner can mute members");
        }

        Optional<RoomMember> memberOptional = roomMemberRepository.findByRoom_RoomIdAndUserId(roomId, userId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found in the room");
        }

        RoomMember member = memberOptional.get();
        member.setMutedUntil(Timestamp.valueOf(LocalDateTime.now().plusMinutes(durationMinutes)));
        roomMemberRepository.save(member);
    }






}


