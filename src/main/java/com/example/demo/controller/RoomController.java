package com.example.demo.controller;

import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.Room;
import com.example.demo.serivce.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/rooms")
public class RoomController {


    @Resource
    private RoomService roomService;


    @PostMapping("/create")
    @ResponseBody
    public ResultBody insertUserCommunityRecord(@RequestBody Room Params) {
        return this.roomService.createRoom(Params);
    }

}
