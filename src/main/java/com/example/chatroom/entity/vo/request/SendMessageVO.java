package com.example.chatroom.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageVO {
    private int roomId;   // 房间 ID
    private int uid;      // 用户 ID
    private String type;     // 消息类型 (TEXT, IMAGE, EMOJI)
    private Content content; // 消息内容
    private String userName; // 用户名
    private String userAvatar; // 用户头像

    @Data
    public static class Content {
        private String text; // 文本消息内容
        private String url;  // 图片或资源 URL
        private Meta meta;   // 附加信息

        @Data
        public static class Meta {
            private Integer width;  // 图片宽度
            private Integer height; // 图片高度
        }
    }
}
