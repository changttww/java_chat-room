package com.example.chatroom.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_type", nullable = false)
    private String roomType;


    @Column(name = "owner_uid", nullable = false)
    private Integer ownerUid;

    @Column(name = "invite_code")
    private String inviteCode;

    @Column(name = "head")
    private String roomAvatar;

    @Column(name="description")
    private String description;

    @Column(name = "online_count")
    private int roomPeopleCount;


    @ManyToMany
    @JoinTable(
            name = "room_member",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members = new ArrayList<>();  // 成员列表

    // 新增关系：一个房间对应多个标签
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomTag> roomTags = new ArrayList<>();  // 标签列表


    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    // Getter 和 Setter 方法
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(Integer ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString 方法
    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", ownerUid=" + ownerUid +
                ", inviteCode='" + inviteCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // equals 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId.equals(room.roomId);
    }

    // hashCode 方法
    @Override
    public int hashCode() {
        return roomId != null ? roomId.hashCode() : 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomTag> getRoomTags() {
        return roomTags;
    }

    public void setRoomTags(List<RoomTag> roomTags) {
        this.roomTags = roomTags;
    }


    public String getRoomAvatar() {
        return roomAvatar;
    }

    public void setRoomAvatar(String roomAvatar) {
        this.roomAvatar = roomAvatar;
    }

    public int getRoomPeopleCount() {
        return roomPeopleCount;
    }

    // 增加1个在线人数
    public void incrementRoomPeopleCount() {
        this.roomPeopleCount += 1;
    }

    // 减少1个在线人数
    public void decrementRoomPeopleCount() {
        if (this.roomPeopleCount > 0) {
            this.roomPeopleCount -= 1;
        }
    }
}


