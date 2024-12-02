package com.example.chatchatbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.chatchatbackend.entity.dto.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
