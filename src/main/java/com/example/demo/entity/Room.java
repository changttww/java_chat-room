package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "room_id", type = IdType.INPUT)  // room_id 是主键，类型为 VARCHAR
    private String roomId;

    @TableField("room_name")
    private  String roomName;

    @TableField("room_type")  // room_type 表示房间类型，枚举值
    private String roomType;

    @TableField("owner_uid")  // owner_uid 表示房主的用户ID
    private Integer ownerUid;

    @TableField("max_members")  // max_members 表示最大成员数
    private Integer maxMembers;

    @TableField("invite_code")  // invite_code 表示邀请码，某些房间类型可能为空
    private String inviteCode;

    @TableField("created_at")  // created_at 表示房间创建时间
    private Timestamp createdAt;

    @TableField("updated_at")  // updated_at 表示房间更新时间
    private Timestamp updatedAt;

}

