package com.lvpaul.shiyi.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import org.apache.ibatis.annotations.Select;

public interface ChannelMapper extends BaseMapper<Channel> {
    @Select("select creator_id from channel where id = #{channelId}")
    Long selectAuthorIdByChannelId(Long channelId);
}
