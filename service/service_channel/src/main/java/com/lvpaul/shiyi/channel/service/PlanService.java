package com.lvpaul.shiyi.channel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;

import java.util.List;

/**
 * <p>
 * 频道赞助计划 服务类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
public interface PlanService extends IService<Plan> {
    public List<Plan> getChannelPlan(Long channelId);

}
