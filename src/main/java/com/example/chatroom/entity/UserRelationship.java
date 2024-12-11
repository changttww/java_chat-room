package com.example.chatroom.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_relationship")
public class UserRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "other_id", nullable = false)
    private User other;

    @Column(name = "relationship_type")
    private String relationshipType;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // 构造函数
    public UserRelationship() {
    }

    public UserRelationship(User user, User other, String relationshipType, String remarks, Timestamp createdAt) {
        this.user = user;
        this.other = other;
        this.relationshipType = relationshipType;
        this.remarks = remarks;
        this.createdAt = createdAt;
    }

    // Get和Set方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOther() {
        return other;
    }

    public void setOther(User other) {
        this.other = other;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // toString方法
    @Override
    public String toString() {
        return "UserRelationship{" +
                "id=" + id +
                ", user=" + user.getUserid() +
                ", other=" + other.getUserid() +
                ", relationshipType='" + relationshipType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    // equals和hashCode方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRelationship)) return false;
        UserRelationship that = (UserRelationship) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getOther(), that.getOther()) &&
                getRelationshipType() == that.getRelationshipType() &&
                Objects.equals(getRemarks(), that.getRemarks()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getOther(), getRelationshipType(), getRemarks(), getCreatedAt());
    }
}
