package com.lvpaul.shiyi.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvpaul.shiyi.pojo.entity.user.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select avatar from user where id = #{userId}")
    String selectAvatarById(Long userId);

    @Select("select username from user where id = #{userId}")
    String selectNameById(Long userId);
}
