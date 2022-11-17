package com.lvpaul.shiyi.subscription.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.subscription.Subscription;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.subscription.service.SubscriptionService;
import com.lvpaul.shiyi.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;

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
}
