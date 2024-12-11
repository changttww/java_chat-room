package com.example.chatroom.entity.DTO;

import com.example.chatroom.entity.Room;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class UserDTO {

    private Integer userid;

    private String username;

    private String avatar;

    private Timestamp createdAt;

    private List<Room> rooms;

    private List<Map<String, Object>> relationships;

    // 构造方法
    public UserDTO() {
    }

    // Getter 和 Setter 方法
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Map<String, Object>> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Map<String, Object>> relationships){
        this.relationships=relationships;
    }
}
