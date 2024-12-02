package com.example.demo.serivce;
import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.Room;
import com.example.demo.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomMapper roomMapper;

    @Autowired
    private RoomService(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    public ResultBody createRoom(Room params) {
        return this.roomMapper.createRoom(params) > 0 ? ResultBody.success() : ResultBody.error();
    }

}
