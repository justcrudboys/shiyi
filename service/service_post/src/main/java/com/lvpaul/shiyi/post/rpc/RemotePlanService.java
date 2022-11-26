package com.lvpaul.shiyi.post.rpc;

import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-channel")
public interface RemotePlanService {
    @RequestMapping("api/channel/getPlan")
    public Plan getPlan(@RequestParam Long planId);
}
