package com.lvpaul.shiyi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;

import java.util.List;

public interface ChannelService extends IService<Channel> {

    public List<Channel> getCreatorChannel(Long creator_id);

    public List<Channel> channelSearch(String key);

    public boolean isNameValidate(String name,Long creator_id);
}
