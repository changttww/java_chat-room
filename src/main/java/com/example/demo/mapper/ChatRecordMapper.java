package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ChatRecord;
import com.example.demo.entity.ChatUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

    //插入数据
    @Insert("INSERT INTO chat_record(community, message) VALUES (#{chatRecord.community}, #{chatRecord.message})")
    int insertMessage(@Param("chatRecord") ChatRecord chatRecord);

    @Select("SELECT * FROM chat_record WHERE community = #{community}")
    List<ChatRecord> messageRecordByCommunity(@Param("community") String community);

}
