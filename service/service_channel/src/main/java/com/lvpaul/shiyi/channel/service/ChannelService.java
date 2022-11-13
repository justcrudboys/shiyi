package com.lvpaul.shiyi.channel.service;

import com.lvpaul.shiyi.pojo.entity.channel.Channel;

import java.util.List;

public interface ChannelService {

    public List<Channel> getCreatorChannel(Long creator_id);
}
