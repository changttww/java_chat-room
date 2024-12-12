package com.example.chatroom.repository;

import com.example.chatroom.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findByRoomId(Integer roomId);
    // 查询房间类型是 private
    List<Room> findByRoomType(String roomType);
    List<Room> findByOwnerUid(Integer ownerUid);


    List<Room> findByRoomNameContaining(String query);
}
