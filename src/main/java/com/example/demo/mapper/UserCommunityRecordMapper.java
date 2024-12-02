package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserCommunityRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


public interface UserCommunityRecordMapper  extends BaseMapper<UserCommunityRecord> {

    @Insert("INSERT INTO chat_user_community_record(user_id, community) VALUES (#{userCommunityRecord.id}, #{userCommunityRecord.community})")
    int insertUserCommunityRecord(@Param("userCommunityRecord") UserCommunityRecord userCommunityRecord);
}
