package com.example.chatroom.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "room_member")
public class RoomMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 新主键

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;  // 关联的房间

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;  // 关联的用户

    @Column(name = "joined_at", nullable = false, updatable = false)
    private Timestamp joinedAt;  // 加入时间

    @Column(name = "muted_until")
    private Timestamp mutedUntil;

    // Getter 和 Setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Timestamp joinedAt) {
        this.joinedAt = joinedAt;
    }

    // toString 方法
    @Override
    public String toString() {
        return "RoomMember{" +
                "id=" + id +
                ", room=" + room +
                ", user=" + user +
                ", joinedAt=" + joinedAt +
                '}';
    }

    // equals 和 hashCode 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomMember that = (RoomMember) o;
        return room.equals(that.room) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, user);
    }

    public void setMutedUntil(Timestamp timestamp) {
    }

    public Timestamp getMutedUntil() {
        return mutedUntil;
    }
}
