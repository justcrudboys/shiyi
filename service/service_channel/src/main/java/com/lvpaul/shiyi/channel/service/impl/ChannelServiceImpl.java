package com.lvpaul.shiyi.channel.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.channel.mapper.ChannelMapper;
import com.lvpaul.shiyi.channel.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {
    @Autowired
    ChannelMapper channelMapper;
    @Override
    public List<Channel> getCreatorChannel(Long creator_id) {
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.eq("creator_id", creator_id);
        List<Channel> channelList = channelMapper.selectList(wrapper);
        return channelList;
    }
}
