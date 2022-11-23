package com.lvpaul.shiyi.subscription.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.post.ChannelPlanPostRelation;
import com.lvpaul.shiyi.pojo.entity.subscription.Subscription;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionRequestVo;
import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionDetailVo;
import com.lvpaul.shiyi.subscription.rpc.RemoteChannelService;
import com.lvpaul.shiyi.subscription.rpc.RemotePostService;
import com.lvpaul.shiyi.subscription.service.ChannelPlanPostRelationService;
import com.lvpaul.shiyi.subscription.service.PlanService;
import com.lvpaul.shiyi.subscription.service.SubscriptionService;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    ChannelPlanPostRelationService channelPlanPostRelationService;
    @Autowired
    PlanService planService;
    @Autowired
    RemoteChannelService remoteChannelService;
    @Autowired
    RemotePostService remotePostService;
    @GetMapping("list")
    @ApiOperation("通过id获取用户还生效的订阅")
    public Result getSubscriptionList(@RequestParam Long userId){
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        //找出此时还在订阅期内的
        wrapper.eq("user_id",userId).ge("expire_time",LocalDateTime.now());
        List<Subscription> subscriptions = subscriptionService.list(wrapper);
        if(subscriptions == null || subscriptions.size() == 0){
            return Result.error().message("该用户没有有效的订阅");
        }
        List<Long> planIdList = subscriptions.stream().map(Subscription::getPlanId).collect(Collectors.toList());
        List<Map<String,Object>> resultList = remoteChannelService.getChannelInfoInner(planIdList);
        List<SubscriptionDetailVo> detailedSubs = new ArrayList<>();
        for(int i = 0;i < subscriptions.size();i++){
            SubscriptionDetailVo subscriptionDetailVo = new SubscriptionDetailVo();
            subscriptionDetailVo.setUserId(subscriptions.get(i).getUserId());
            subscriptionDetailVo.setPlanId(subscriptions.get(i).getPlanId());
            subscriptionDetailVo.setExpireTime(subscriptions.get(i).getExpireTime());
            subscriptionDetailVo.setChannelId(Long.valueOf((Integer) resultList.get(i).get("channelId")));
            subscriptionDetailVo.setAmount((Integer) resultList.get(i).get("amount"));
            subscriptionDetailVo.setPlanName((String) resultList.get(i).get("planName"));
            subscriptionDetailVo.setPlanIntroduction((String) resultList.get(i).get("planIntro"));
            subscriptionDetailVo.setChannelName((String) resultList.get(i).get("channelName"));
            subscriptionDetailVo.setChannelIntroduction((String) resultList.get(i).get("channelIntro"));
            subscriptionDetailVo.setCreator_id(Long.valueOf((Integer) resultList.get(i).get("creatorId")));
            subscriptionDetailVo.setImg((String) resultList.get(i).get("img"));
            detailedSubs.add(subscriptionDetailVo);
        }
        return Result.success(detailedSubs);
    }
    @GetMapping("post")
    @ApiOperation("判断用户对某个动态是否有权限浏览")
    public Result isPostValid (@RequestParam Long postId){
        Long userId =   Long.parseLong((String) StpUtil.getLoginId());
        //先判断是不是创作者
        if(userId==remotePostService.planHost(postId))
            return Result.success(true);
        //找出该动态支持的方案
        QueryWrapper<ChannelPlanPostRelation> planWrapper = new QueryWrapper<>();
        planWrapper.eq("post_id",postId);
        List<ChannelPlanPostRelation> postPlanList = channelPlanPostRelationService.list(planWrapper);
        List<Long> planIdList = postPlanList.stream().map(ChannelPlanPostRelation::getPlanId).collect(Collectors.toList());
        if(planIdList.size()==0){
            return Result.success(true); //动态不设置相应的方案档位默认全部用户可浏览
        }
        //查出用户是否有还在有效期内的那些方案的订阅
        QueryWrapper<Subscription> subWrapper = new QueryWrapper<>();
        subWrapper.eq("user_id",userId)
                .in("plan_id",planIdList)
                .ge("expire_time",LocalDateTime.now());
        if(subscriptionService.list(subWrapper).size()>0){
            return Result.success(true);
        }else {
            return Result.success(false);
        }
    }
    @PostMapping
    @ApiOperation("用户订阅")
    public Result subscribe(@RequestBody SubscriptionRequestVo subscriptionRequestVo){
        int month = subscriptionRequestVo.getMonth();

        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("plan_id", subscriptionRequestVo.getPlanId())
                .eq("user_id", subscriptionRequestVo.getUserId());
        Subscription subscription = subscriptionService.getOne(wrapper);
        if(subscription!=null){
            //订阅信息非空，更新订阅失效时间
            LocalDateTime newExprieTime;
            if(subscription.getExpireTime().isAfter(LocalDateTime.now()))//判断失效时间是在今天之前还是之后
                newExprieTime = subscription.getExpireTime().plusMonths(month);
            else
                newExprieTime = LocalDateTime.now().plusMonths(month);
            subscription.setExpireTime(newExprieTime);

            if(subscriptionService.update(subscription,wrapper))
                return Result.success();
            else
                return Result.error();
        }else{
            //订阅信息为空，新增订阅信息
            Subscription newSubscription = new Subscription();
            newSubscription.setPlanId(subscriptionRequestVo.getPlanId());
            newSubscription.setUserId(subscriptionRequestVo.getUserId());
            newSubscription.setExpireTime(LocalDateTime.now().plusMonths(month));//从今日起增加月份
            if(subscriptionService.save(newSubscription))
                return Result.success();
            else
                return Result.error();
        }
    }
}
