package com.lvpaul.shiyi.subscription.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.channel.Tag;
import com.lvpaul.shiyi.subscription.mapper.TagMapper;
import com.lvpaul.shiyi.subscription.service.TagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 频道分类标签 服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
