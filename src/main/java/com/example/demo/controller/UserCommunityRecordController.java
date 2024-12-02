package com.example.demo.controller;

import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.UserCommunityRecord;
import com.example.demo.serivce.UserCommunityRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/rooms")
public class UserCommunityRecordController {


    @Resource
    private UserCommunityRecordService userCommunityRecordService;


    @PostMapping("/insertUserCommunityRecord")
    @ResponseBody
    public ResultBody insertUserCommunityRecord(@RequestBody UserCommunityRecord Params) {
        return this.userCommunityRecordService.insertUserCommunityRecord(Params);
    }

}
