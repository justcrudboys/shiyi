package com.lvpaul.shiyi.channel.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.channel.mapper.ChannelMapper;
import com.lvpaul.shiyi.channel.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public List<Channel> channelSearch(String key) {
        QueryWrapper<Channel> qw = new QueryWrapper<>();
        qw.like(!StringUtils.isEmpty(key), "name", key);
        List<Channel> channelList = channelMapper.selectList(qw);
        return channelList;
    }

    @Override
    public List<Channel> recommendChannel() {
        QueryWrapper<Channel> qw = new QueryWrapper<>();
        qw.orderByDesc("views");
        Page<Channel> channelPage = new Page<>(1,10);//查询前10条
        channelMapper.selectPage(channelPage,qw);
        return channelPage.getRecords();
    }

    @Override
    public boolean isNameValidate(String name,Long creator_id){
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.eq("creator_id",creator_id).eq("name",name);
        Channel channel = channelMapper.selectOne(wrapper);
        if(channel==null){ //查不到说明该创作者的频道还没被占用
            return true;
        } else{
            return false;
        }
    }

}
