package com.lvpaul.shiyi.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.post.mapper.ChannelMapper;
import com.lvpaul.shiyi.post.service.PostService;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.post.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    PostService postService;
    @Autowired
    PostMapper postMapper;
    @Autowired
    ChannelMapper channelMapper;

    @Override
    public List<Post> getChannelPost(Long channel_id){
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("channel_id",channel_id);
        List<Post> postList = postMapper.selectList(wrapper);
        return postList;
    }

    @Override
    public Long getPostHost(Long postId) {
        Post post = postMapper.selectById(postId);
        Channel channel = channelMapper.selectById(post.getChannelId());
        return channel.getCreatorId();
    }
}
