package com.example.chatroom.service;

import com.example.chatroom.entity.Room;
import com.example.chatroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // 根据 Room 对象创建房间
    public Room createRoom(Room params) {
        // 如果房间类型为 "group"，生成邀请码
        if ("group".equalsIgnoreCase(params.getRoomType())) {
            // 使用 UUID 生成唯一的 8 位邀请码
            params.setInviteCode(UUID.randomUUID().toString().substring(0, 8));
        } else if ("private".equalsIgnoreCase(params.getRoomType())) {
            // 对于 private 类型的房间，不需要房间名、最大成员数和头像
            params.setRoomName(null);
            params.setMaxMembers(null);
            params.setInviteCode(null);
            params.setHead(null); // 私聊不需要头像
        } else if ("public".equalsIgnoreCase(params.getRoomType())) {
            // 对于 public 类型的房间，可以有头像，邀请码为 null
            params.setHead("🎨");  // 设置一个默认的头像
            params.setInviteCode(null); // public 类型没有邀请码
        }

        // 保存房间信息并返回保存后的房间对象
        return roomRepository.save(params);
    }

    // 根据提供的参数创建房间
    public Room createRoom(String roomType, String roomName, Integer ownerUid, Integer maxMembers) {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setOwnerUid(ownerUid);

        // 创建时间和更新时间会在数据库中自动生成，不需要手动设置

        if ("private".equalsIgnoreCase(roomType)) {
            room.setRoomName(null);  // 私聊房间不需要房间名
            room.setMaxMembers(null);  // 私聊房间不需要最大人数
            room.setInviteCode(null);  // 私聊房间没有邀请码
            room.setHead(null);  // 私聊房间不需要头像
        } else if ("public".equalsIgnoreCase(roomType)) {
            room.setRoomName(roomName);  // public 房间需要房间名
            room.setMaxMembers(maxMembers);  // public 房间需要最大人数
            room.setHead("🎨");  // 设置 public 房间头像
            room.setInviteCode(null);  // public 房间没有邀请码
        } else if ("group".equalsIgnoreCase(roomType)) {
            room.setRoomName(roomName);  // group 房间需要房间名
            room.setMaxMembers(maxMembers);  // group 房间需要最大人数
            room.setHead("💡");  // 设置 group 房间头像
            room.setInviteCode(UUID.randomUUID().toString().substring(0, 8));  // 生成 group 房间的邀请码
        }

        // 保存并返回房间
        return roomRepository.save(room);
    }
}
