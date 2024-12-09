package com.example.chatroom.repository;

import com.example.chatroom.entity.RoomTag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomTagRepository extends JpaRepository<RoomTag, Integer> {

    // 根据房间ID查询所有标签
    List<RoomTag> findByRoom_RoomId(Integer roomId);

    // 根据标签查询所有房间
    List<RoomTag> findByTag(String tag);


}

