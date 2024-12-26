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
import org.springframework.web.bind.annotation.PathVariable;

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
                return user.getUserid();  // 返回用户的ID
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




    public Room createRoom(RoomDTO roomDTO) {
        Integer currentUserId = getCurrentUserId();

        if ("private".equalsIgnoreCase(roomDTO.getRoomType()))
        {
            List<Room> privateRooms = roomRepository.findByRoomType("private");

                for (Room existingRoom : privateRooms)
                {
                    List<RoomMember> roomMembers = roomMemberRepository.findByRoom_RoomId(existingRoom.getRoomId());
                    List<Integer> memberIds = roomMembers.stream()
                            .map(member -> member.getUser().getUserid())
                            .toList();

                    // 检查房间成员是否只有两个，并且包含当前用户和接收用户
                    if (memberIds.contains(currentUserId) &&
                            memberIds.contains(roomDTO.getReceiverUid()))
                    {
                        return existingRoom;
                    }
                }

            Room room1 = new Room();
            room1.setOwnerUid(currentUserId);
            room1.setRoomType(roomDTO.getRoomType());
            room1.incrementRoomPeopleCount();
            room1.incrementRoomPeopleCount();
            room1 = roomRepository.saveAndFlush(room1);
            RoomMember roomMember1 = new RoomMember();
            roomMember1.setRoom(room1);
            Optional<User> userOptional = UserRepository.findById(roomDTO.getReceiverUid());
            if (userOptional.isEmpty()) {
                throw new RuntimeException("Receiver user not found");
            }
            roomMember1.setUser(userOptional.get());
            roomMember1.setJoinedAt(new Timestamp(System.currentTimeMillis()));
            roomMemberRepository.saveAndFlush(roomMember1);
            // Add the current user and receiver to the room
            RoomMember roomMember = new RoomMember();
            roomMember.setRoom(room1);
            roomMember.setUser(getCurrentUser());
            roomMember.setJoinedAt(new Timestamp(System.currentTimeMillis()));
            roomMemberRepository.saveAndFlush(roomMember);
            return room1;

        }

        else
        {
            Room room = new Room();
            room.setOwnerUid(currentUserId);
            room.setRoomType(roomDTO.getRoomType());
            room.setRoomName(roomDTO.getRoomName());
            room.setInviteCode("group".equals(roomDTO.getRoomType()) ? UUID.randomUUID().toString().replace("-", "").substring(0, 8) : null);
            if (roomDTO.getRoomAvatar() == null || roomDTO.getRoomAvatar().isEmpty()) {
                room.setRoomAvatar("/images/ENFP-竞选者.png");
            } else {
                room.setRoomAvatar(roomDTO.getRoomAvatar());
            }
            room.setDescription(roomDTO.getDescription());
            room.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            room.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            room.incrementRoomPeopleCount();


            // Save room first and flush to ensure persistence
            room = roomRepository.saveAndFlush(room);

            // Add the current user and receiver to the room
            RoomMember roomMember = new RoomMember();
            roomMember.setRoom(room);
            roomMember.setUser(getCurrentUser());
            roomMember.setJoinedAt(new Timestamp(System.currentTimeMillis()));
            roomMemberRepository.saveAndFlush(roomMember);

            // Process tags
            if (roomDTO.getTags() != null && !roomDTO.getTags().isEmpty()) {
                for (String tagName : roomDTO.getTags()) {
                    RoomTag roomTag = new RoomTag();
                    roomTag.setRoom(room);
                    roomTag.setTag(tagName);
                    roomTag.setColor("white");
                    room.getRoomTags().add(roomTag);

                    roomTagRepository.saveAndFlush(roomTag);
                }
            }
            return room;
        }

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
        Optional<RoomMember> roomMemberOptional = roomMemberRepository.findByRoom_RoomIdAndUserUserid(room.getRoomId(), currentUserId);
        if (roomMemberOptional.isPresent()) {
            return Response.error("User already a member of the room");
        }

        // 添加用户到房间成员列表
        RoomMember roomMember = new RoomMember();
        roomMember.setRoom(room);
        roomMember.setUser(getCurrentUser());
        roomMember.setJoinedAt(new Timestamp(System.currentTimeMillis()));
        roomMemberRepository.save(roomMember);
        room.incrementRoomPeopleCount();
        roomRepository.save(room);

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
        roomDTO.setRoomAvatar(room.getRoomAvatar());
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setInviteCode(room.getInviteCode());
        roomDTO.setCreatedAt(room.getCreatedAt());
        roomDTO.setUpdatedAt(room.getUpdatedAt());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setRoomPeopleCount(room.getRoomPeopleCount());

        // 获取房间成员
        List<RoomMember> roomMembers = roomMemberRepository.findByRoom_RoomId(roomId);

        // 构建成员信息列表
        List<Map<String, Object>> members = roomMembers.stream()
                .map(member -> {
                    Map<String, Object> memberInfo = new HashMap<>();
                    memberInfo.put("userId", member.getUser().getUserid());
                    memberInfo.put("username", member.getUser().getUsername());
                    memberInfo.put("joinedAt", member.getJoinedAt().toString());// 转为字符串格式
                    memberInfo.put("head", member.getUser().getAvatar());
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
        Optional<RoomMember> roomMemberOptional = roomMemberRepository.findByRoom_RoomIdAndUserUserid(roomId, currentUserId);
        if (roomMemberOptional.isEmpty()) {
            throw new RuntimeException("User is not a member of the room");
        }

        // 删除用户房间成员记录
        RoomMember roomMember = roomMemberOptional.get();
        roomMemberRepository.delete(roomMember);

        room.decrementRoomPeopleCount();
        roomRepository.save(room);

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
        if (roomDTO.getRoomAvatar() != null) {
            room.setRoomAvatar(roomDTO.getRoomAvatar());
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

        Optional<RoomMember> newOwnerOptional = roomMemberRepository.findByRoom_RoomIdAndUserUserid(roomId, newOwnerId);
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

    public List<Room> searchMyRooms(RoomDTO roomDTO) {
        // 查询房间名称包含关键词的房间
        List<Room> rooms = roomRepository.findByRoomNameContaining(roomDTO.getQuery());
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            Optional<RoomMember> roomMemberOptional = roomMemberRepository.findByRoom_RoomIdAndUserUserid(room.getRoomId(), getCurrentUserId());
            if (roomMemberOptional.isPresent()) {
                result.add(room);
            }
        }
        return result;
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

        Optional<RoomMember> memberOptional = roomMemberRepository.findByRoom_RoomIdAndUserUserid(roomId, userId);
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

        Optional<RoomMember> memberOptional = roomMemberRepository.findByRoom_RoomIdAndUserUserid(roomId, userId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found in the room");
        }

        RoomMember member = memberOptional.get();
        member.setMutedUntil(Timestamp.valueOf(LocalDateTime.now().plusMinutes(durationMinutes)));
        roomMemberRepository.save(member);
    }


    // 2.9. 获取6个推荐房间
    public List<RoomDTO> get6RecommendedRooms() {
        List<Room> rooms = roomRepository.findTop6ByOnlineCount();
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for(Room room: rooms){
            RoomDTO roomDTO = getRoomDetails(room.getRoomId());
            roomDTOs.add(roomDTO);
        }
        return roomDTOs;
    }


    // 2.10. 获取所有标签
    public List<String> getAllTags() {
        return roomTagRepository.findAllTags();
    }

    // 2.11. 获取用户加入的房间基本信息
    public List<RoomDTO> getUserJoinedRooms() {
        // 查找该用户参与的所有 RoomMember 记录
        List<RoomMember> roomMembers = roomMemberRepository.findByUser_Userid(getCurrentUserId());

        // 获取房间信息并转换为 RoomDTO
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for (RoomMember roomMember : roomMembers) {
            Room room = roomMember.getRoom();
            RoomDTO roomDTO = getRoomDetails(room.getRoomId());
            roomDTOs.add(roomDTO);
        }

        return roomDTOs;
    }


    // 2.12. 获取20个推荐房间标签
    public List<RoomTag> getTop20RoomTags() {
        return roomTagRepository.findTop20Tags();
    }

    // 2.13. 通过标签获得房间
    public List<Room> getRoomsByTag(String tag) {
        // Fetch rooms that have the given tag
        List<RoomTag> rooms = roomTagRepository.findByTag(tag);
        List<Room> result = new ArrayList<>();
        for (RoomTag roomTag : rooms) {
            result.add(roomTag.getRoom());
        }
        return result;
    }

    public List<Room> getRoomsByRoomId(Integer roomId){
         Optional<Room> roomOptional = roomRepository.findByRoomId(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found");
        }
        return  roomOptional.stream().toList();
    }





}


