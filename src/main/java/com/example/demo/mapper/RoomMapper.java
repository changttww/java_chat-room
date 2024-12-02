package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;


public interface RoomMapper extends BaseMapper<Room> {

    @Insert("INSERT INTO chat_room (room_name, room_type, owner_uid, max_members, invite_code) " +
            "VALUES ( #{room.roomName},#{room.roomType}, #{room.ownerUid}, #{room.maxMembers}, #{room.inviteCode})")
    int createRoom(@Param("room") Room room);


}
