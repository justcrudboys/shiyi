package com.lvpaul.shiyi.subscription.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.subscription.Subscription;
import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionRequestVo;
import com.lvpaul.shiyi.subscription.service.SubscriptionService;
import com.lvpaul.shiyi.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    @GetMapping
    public Result get(){
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("plan_id",1);
        Subscription subscription = subscriptionService.getOne(wrapper);
        return Result.success(subscription);
    }
    @PostMapping
    public Result subscribe(@RequestBody SubscriptionRequestVo subscriptionRequestVo){
        System.out.println(subscriptionRequestVo.toString());
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("plan_id", subscriptionRequestVo.getPlanId())
                .eq("user_id", subscriptionRequestVo.getUserId());
        Subscription subscription = subscriptionService.getOne(wrapper);
        if(subscription!=null){
            //订阅信息非空，更新订阅信息
            System.out.println(subscription.toString());
            subscription.setExpireTime(new Date());
            if(subscriptionService.save(subscription))
                return Result.success();
            else
                return Result.error();
        }else{
            //订阅信息为空，新增订阅信息
            Subscription newSubscription = new Subscription();
            newSubscription.setPlanId(subscriptionRequestVo.getPlanId());
            newSubscription.setUserId(subscriptionRequestVo.getUserId());
            newSubscription.setExpireTime(new Date());
            if(subscriptionService.save(newSubscription))
                return Result.success();
            else
                return Result.error();
        }
    }
}
