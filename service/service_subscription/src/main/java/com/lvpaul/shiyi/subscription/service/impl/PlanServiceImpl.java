package com.lvpaul.shiyi.subscription.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.subscription.mapper.PlanMapper;
import com.lvpaul.shiyi.subscription.service.PlanService;
import org.springframework.stereotype.Service;

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

}
