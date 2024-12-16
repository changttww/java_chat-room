// MessageRepository
package com.example.chatroom.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.chatroom.entity.DTO.MessageDTO;
import com.example.chatroom.entity.vo.request.SendMessageVO;
import com.example.chatroom.entity.vo.response.MessageVO;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<MessageDTO, Integer> {
    /**
     * 保存消息
     * @param message 消息对象
     * @return 成功返回 null，失败返回错误信息
     */
    String saveMessage(SendMessageVO message);

    /**
     * 获取某个房间中自指定时间以来的消息
     * @param roomId 房间 ID
     * @param since 时间点
     * @return 消息列表
     */
    List<MessageVO> getMessagesSince(int roomId, LocalDateTime since);

    /**
     * 根据房间号和起始时间获取消息
     *
     * @param roomId 房间 ID
     * @param since  起始时间
     * @return 消息列表
     */
    List<MessageDTO> findByRoomIdAndSendTimeAfter(int roomId, LocalDateTime since);
}
