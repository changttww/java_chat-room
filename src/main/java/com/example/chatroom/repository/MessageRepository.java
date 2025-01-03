// MessageRepository
package com.example.chatroom.repository;

import com.example.chatroom.entity.Message;
import com.example.chatroom.entity.DTO.MessageDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    /**
     * 根据房间号和起始时间获取消息
     *
     * @param roomId 房间 ID
     * @param since  起始时间
     * @return 消息列表
     */
    List<Message> findByRoomIdAndSendTimeAfter(int roomId, LocalDateTime since);
    /**
     * 根据房间号和起始时间获取消息
     *
     * @param roomId 房间 ID
     * @return 消息列表
     */
    List<Message> findByRoomId(int roomId);
}
