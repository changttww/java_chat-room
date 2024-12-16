package com.example.chatroom.repository;

import com.example.chatroom.entity.DTO.RoomDTO;
import com.example.chatroom.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomId(Integer roomId);
    // 查询房间类型是 private
    List<Room> findByRoomType(String roomType);
    List<Room> findByOwnerUid(Integer ownerUid);
    List<Room> findByRoomNameContaining(String query);

    // 获取在线人数最多的前6个房间
    @Query(value = "SELECT * FROM chat_room ORDER BY online_count DESC LIMIT 6", nativeQuery = true)
    List<Room> findTop6ByOnlineCount();

}
