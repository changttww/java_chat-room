package com.example.demo.controller;


import com.example.demo.type.IMessageRecordParams;
import com.example.demo.common.response.ResultBody;
import com.example.demo.serivce.ChatRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/chatRecord")
public class ChatRecordController {

    @Resource
    private ChatRecordService chatRecordService;

    @PostMapping("/add")
    @ResponseBody
    public String add() {
        return "add";
    }
    
    @PostMapping("/messageRecord")
    @CrossOrigin
    @ResponseBody
    public ResultBody messageRecordByCommunity(@RequestBody IMessageRecordParams body){
        System.out.println(body.community);
        return this.chatRecordService.messageRecordByCommunity(body.community);
    }
}
