package com.example.demo.type;


//    { type: 'UserMessage' | 'HintMessage'; message: string; name: string; userId: number; head: string; time: string }

import lombok.Data;

@Data
public class IMessage {
    
    public String type;

    public String message;

    public String name;

    public Integer userId;

    public String time;

    public String head;
}
