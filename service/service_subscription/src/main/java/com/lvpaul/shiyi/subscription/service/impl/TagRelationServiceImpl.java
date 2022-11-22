package com.lvpaul.shiyi.subscription.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.channel.TagRelation;
import com.lvpaul.shiyi.subscription.mapper.TagRelationMapper;
import com.lvpaul.shiyi.subscription.service.TagRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 频道标签关系 服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Service
public class TagRelationServiceImpl extends ServiceImpl<TagRelationMapper, TagRelation> implements TagRelationService {

}
