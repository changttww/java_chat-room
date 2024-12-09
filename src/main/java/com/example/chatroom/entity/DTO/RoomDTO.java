package com.example.chatroom.entity.DTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RoomDTO {

    // 房间基本信息
    private Integer roomId;  // 房间ID，适用于更新房间或加入房间
    private String roomType; // 房间类型：group, private, public
    private String roomName; // 房间名称，适用于公共和群聊类型
    private Integer maxMembers; // 最大成员数，适用于群聊和公共房间
    private String inviteCode; // 邀请码，仅适用于群聊
    private String head; // 房间头像
    private Integer ownerUid; // 房间创建者，适用于创建房间
    private Integer receiverUid; // 仅用于私聊，接收者的用户ID
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String description;
    private List<String> tags;

    // 房间成员信息，用于加入房间
    private Integer userId;  // 用户ID，适用于加入房间的请求
    private String action;   // 操作类型，"join" 或 "leave" 等，用于加入/退出房间的请求

    // 列表，可以包含多个用户ID，用于群聊或批量操作
    private List<Integer> userIds;
    private List<Map<String, Object>> members; // 新增成员详细信息字段

    private String query;


    // Getter 和 Setter 方法
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Integer getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(Integer ownerUid) {
        this.ownerUid = ownerUid;
    }

    public Integer getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(Integer receiverUid) {
        this.receiverUid = receiverUid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Map<String, Object>> getMembers() {
        return members;
    }

    public void setMembers(List<Map<String, Object>> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMembersCount(int i) {
    }

    public void setJoinedAt(String string) {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}

