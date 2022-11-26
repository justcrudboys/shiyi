package com.lvpaul.shiyi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.entity.channel.TagRelation;

import java.util.List;

/**
 * <p>
 * 频道标签关系 服务类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
public interface TagRelationService extends IService<TagRelation> {
    public List<TagRelation> getChannelTagRelation(Long channelId);

    public List<TagRelation> channelByTagId(Long tagId);

    public Boolean updateOneChannelTagRelation(List<TagRelation> tagRelationList);

}
