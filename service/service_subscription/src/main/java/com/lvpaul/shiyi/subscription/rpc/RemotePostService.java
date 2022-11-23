package com.lvpaul.shiyi.subscription.rpc;

import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-post")
public interface RemotePostService {
    @GetMapping("api/post/planhost")
    public Long planHost(@RequestParam Long postId);
}
