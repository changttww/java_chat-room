package com.example.chatroom.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;     //主键id，自动生成

    @Column(name = "room_id", nullable = false)
    int roomId;   // 房间 ID

    @Column(name = "user_id", nullable = false)
    int uid;      // 用户 ID

    @Column(name = "type", nullable = false)
    String type;     // 消息类型 (TEXT, IMAGE, EMOJI)

    @Embedded
    Content content; // 消息内容

    @Column(name = "send_time", nullable = false)
            @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime sendTime;

    @Column(name = "user_name", nullable = false)
    String userName; // 用户名

    @Column(name = "user_avatar", nullable = false)
    String userAvatar; // 用户头像

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int user_id) {
        this.uid = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }


    @Embeddable
    public static class Content {
        @Column(name = "text")
        private String text;  // 消息文本内容

        @Column(name = "url")
        private String url;  // 图片资源URL

        @Embedded
        private Meta meta;  // 图片的元数据（宽高等）


        // Getters and Setters

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(", \"content\":{")
                    .append(" \"text\": \"").append(text).append('\"')
                    .append(", \"url\":").append((url == null)?" ":" \"").append(url).append((url == null)?"":"\"")
                    .append(", \"meta\": ").append(meta)  // 会调用 Meta 的 toString 方法
                    .append('}');
            return sb.toString();
        }

        @Embeddable
        public static class Meta {
            @Column(name = "width")
            Integer width;  // 图片宽度

            @Column(name = "height")
            Integer height; // 图片高度

            // Getters and Setters

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            @Override
            public String toString() {
                return "{" +
                        " \"width\": " + width +
                        ", \"height\": " + height +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = sendTime.format(formatter);

        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append(" \"roomId\": ").append(roomId)
                .append(", \"uid\": ").append(uid)
                .append(", \"type\": \"").append(type).append('\"')
                .append(content)  // 会调用 Content 的 toString 方法
                .append(", \"sendTime\": \"").append(time).append('\"')
                .append(", \"userName\": \"").append(userName).append('\"')
                .append(", \"userAvatar\": \"").append(userAvatar).append('\"')
                .append('}');
        return sb.toString();
    }
}
