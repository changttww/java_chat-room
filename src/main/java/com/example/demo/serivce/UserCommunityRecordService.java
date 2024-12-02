package com.example.demo.serivce;
import com.example.demo.common.response.ResultBody;
import com.example.demo.entity.UserCommunityRecord;
import com.example.demo.mapper.UserCommunityRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommunityRecordService {

    private UserCommunityRecordMapper userCommunityRecordMapper;

    @Autowired
    private UserCommunityRecordService(UserCommunityRecordMapper userCommunityRecordMapper) {
        this.userCommunityRecordMapper = userCommunityRecordMapper;
    }

    public ResultBody insertUserCommunityRecord(UserCommunityRecord params) {
        return this.userCommunityRecordMapper.insertUserCommunityRecord(params) > 0 ? ResultBody.success() : ResultBody.error();
    }

}
