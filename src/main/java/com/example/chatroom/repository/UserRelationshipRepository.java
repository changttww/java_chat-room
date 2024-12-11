package com.example.chatroom.repository;

import com.example.chatroom.entity.RoomMember;
import com.example.chatroom.entity.User;
import com.example.chatroom.entity.UserRelationship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationshipRepository extends JpaRepository<RoomMember, Integer> {

    List<UserRelationship> findByUserid(Integer userid);
}
