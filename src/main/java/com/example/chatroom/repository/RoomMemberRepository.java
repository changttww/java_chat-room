package com.example.chatroom.repository;

import com.example.chatroom.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomMemberRepository extends JpaRepository<RoomMember, Integer> {

    // 通过 room_id 和 user_id 查找某个房间中的用户成员
    Optional<RoomMember> findByRoom_RoomIdAndUserId(Integer roomId, Integer userId);

    // 通过 room_id 查找所有成员
    List<RoomMember> findByRoom_RoomId(Integer roomId);

    // 通过 user_id 查找所有参与的房间
    List<RoomMember> findByUserId(Integer userId);

    // 根据 room_id 删除某个成员
    void deleteByRoom_RoomIdAndUserId(Integer roomId, Integer userId);

}
