package com.lvpaul.shiyi.channel.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.channel.mapper.ChannelMapper;
import com.lvpaul.shiyi.channel.mapper.PlanMapper;
import com.lvpaul.shiyi.channel.service.PlanService;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.entity.channel.TagRelation;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.pojo.vo.channel.ChannelPlanCreateRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 频道赞助计划 服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
    @Autowired
    PlanMapper planMapper;
    @Override
    public List<Plan> getChannelPlan(Long channel_id){
        QueryWrapper<Plan> qw = new QueryWrapper<>();
        qw.eq("channel_id", channel_id);
        List<Plan> planList = planMapper.selectList(qw);
        return planList;
    }
    public Boolean createChannelPlan(ChannelPlanCreateRequestVo channelPlanRequest) {
        Long channelId = channelPlanRequest.getChannelId();
        int amount = channelPlanRequest.getAmount();
        String name = channelPlanRequest.getName();
        String introduction = channelPlanRequest.getIntroduction();
        Plan plan = new Plan();
        plan.setChannelId(channelId);
        plan.setAmount(amount);
        plan.setName(name);
        plan.setIntroduction(introduction);
        if(planMapper.insert(plan)==1)
            return true;
        else
            return false;
    }

}
