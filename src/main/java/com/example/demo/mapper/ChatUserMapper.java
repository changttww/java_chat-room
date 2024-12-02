package com.example.demo.mapper;

import com.example.demo.entity.ChatUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nipengcheng
 * @since 2024-01-10
 */

public interface ChatUserMapper extends BaseMapper<ChatUser> {

    @Insert("INSERT INTO chat_user(username, password, name, head) VALUES (#{user.username}, #{user.password}, #{user.name}, #{user.head})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    int register(@Param("user") ChatUser user);

    @Select("SELECT id, username, name, head FROM chat_user WHERE id = #{id}")
    ChatUser selectById(@Param("id") Integer id);

    @Select("SELECT id, username, name, head FROM chat_user WHERE username = #{user.username} AND password = #{user.password}")
    ChatUser login(@Param("user") ChatUser user);
}
