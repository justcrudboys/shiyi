package com.lvpaul.shiyi.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.post.PostAttachment;
import com.lvpaul.shiyi.post.mapper.PostAttachmentMapper;
import com.lvpaul.shiyi.post.service.PostAttachmentService;
import org.springframework.stereotype.Service;

@Service
public class PostAttachmentServiceImpl extends ServiceImpl<PostAttachmentMapper, PostAttachment> implements PostAttachmentService {
}
