package com.example.chatroom.repository;

import com.example.chatroom.entity.RoomTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomTagRepository extends JpaRepository<RoomTag, Integer> {

    // 根据房间ID查询所有标签
    List<RoomTag> findByRoom_RoomId(Integer roomId);

    // 根据标签查询所有房间
    List<RoomTag> findByTag(String tag);

    // 查询所有标签
    @Query("SELECT rt.tag FROM RoomTag rt GROUP BY rt.tag")  // 使用 GROUP BY 来确保标签不重复
    List<String> findAllTags();

    @Query(value = "SELECT rt.* " +
            "FROM room_tags rt " +
            "GROUP BY rt.tag, rt.color " +
            "ORDER BY COUNT(rt.tag) DESC " +
            "LIMIT 20",
            nativeQuery = true)
    List<RoomTag> findTop20Tags();


}

