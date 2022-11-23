package com.lvpaul.shiyi.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.post.Post;

import java.util.List;

public interface PostService extends IService<Post> {
    public List<Post> getChannelPost(Long channel_id);

    public Long getPostHost(Long postId);
}
