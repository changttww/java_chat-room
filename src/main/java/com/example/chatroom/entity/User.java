
package com.example.chatroom.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userid;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @ManyToMany
    @JoinTable(
        name = "user_relationship", // 中间表名称
        joinColumns = @JoinColumn(name = "user_id"), // 当前用户在中间表的外键列
        inverseJoinColumns = @JoinColumn(name = "other_id") // 相关用户在中间表的外键列
    )
    private List<UserRelationship> relationships = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<Room> rooms = new ArrayList<>();  // 用户加入的房间列表

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Getter 和 Setter 方法
    public Integer getUserid() {
        return userid;
    }

    public void setuserId(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    //
    
    public List<Room> getRooms() {
        return rooms;
    }

    public List<UserRelationship> getRelationships() {
        return relationships = new ArrayList<>();
    }

    // toString, equals 和 hashCode 方法
    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userid.equals(user.userid);
    }

    @Override
    public int hashCode() {
        return userid != null ? userid.hashCode() : 0;
    }
}