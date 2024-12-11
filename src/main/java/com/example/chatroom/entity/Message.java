package com.example.chatroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msgId;

    @Column(name = "roomId", nullable = false)
    private int roomId;

    @Column(name = "uid", nullable = false)
    private int uid;

    @Column(name = "type", nullable = false)
    private String type; // TEXT, IMAGE, EMOJI

    @Column(name = "content_text")
    private String contentText;

    @Column(name = "content_url")
    private String contentUrl;

    @Column(name = "content_meta_width")
    private Integer contentMetaWidth;

    @Column(name = "content_meta_height")
    private Integer contentMetaHeight;

    @Column(name = "userName")
    private String userName;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "sendTime", nullable = false)
    private LocalDateTime sendTime;

    // Getters and Setters

}
