package com.lvpaul.shiyi.subscription.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.post.ChannelPlanPostRelation;
import com.lvpaul.shiyi.pojo.entity.subscription.Subscription;
import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionRequestVo;
import com.lvpaul.shiyi.subscription.service.ChannelPlanPostRelationService;
import com.lvpaul.shiyi.subscription.service.SubscriptionService;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    ChannelPlanPostRelationService channelPlanPostRelationService;
    @GetMapping("list")
    @ApiOperation("通过id获取用户还生效的订阅")
    public Result getSubscriptionList(@RequestParam Long userId){
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        //找出此时还在订阅期内的
        wrapper.eq("user_id",userId).ge("expire_time",LocalDateTime.now());
        List<Subscription> subscriptions = subscriptionService.list(wrapper);
        return Result.success(subscriptions);
    }
    @GetMapping("post")
    @ApiOperation("判断用户对某个动态是否有权限浏览")
    public boolean isPostValid (@RequestParam Long userId,@RequestParam Long postId){
        //找出该动态支持的方案
        QueryWrapper<ChannelPlanPostRelation> planWrapper = new QueryWrapper<>();
        planWrapper.eq("post_id",postId);
        List<ChannelPlanPostRelation> postPlanList = channelPlanPostRelationService.list(planWrapper);
        List<Long> planIdList = postPlanList.stream().map(ChannelPlanPostRelation::getPlanId).collect(Collectors.toList());
        if(planIdList.size()==0){
            return true; //动态不设置相应的方案档位默认全部用户可浏览
        }
        //查出用户是否有还在有效期内的那些方案的订阅
        QueryWrapper<Subscription> subWrapper = new QueryWrapper<>();
        subWrapper.eq("user_id",userId)
                .in("plan_id",planIdList)
                .ge("expire_time",LocalDateTime.now());
        if(subscriptionService.list(subWrapper).size()>0){
            return true;
        }else {
            return false;
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
