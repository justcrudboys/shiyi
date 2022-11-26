package com.lvpaul.shiyi.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.post.ChannelPlanPostRelation;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态帖权限赞助方案关系 服务类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
public interface ChannelPlanPostRelationService extends IService<ChannelPlanPostRelation> {

    public List<Map<String,Object>> getPostPlan(Long channel_id);
}
