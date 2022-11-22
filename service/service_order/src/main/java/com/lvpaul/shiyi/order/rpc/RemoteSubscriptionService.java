package com.lvpaul.shiyi.order.rpc;

import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-subscription")
public interface RemoteSubscriptionService {
    @PostMapping("/api/subscription")
    public Result subscribe(SubscriptionRequestVo subscriptionRequestVo);
}
