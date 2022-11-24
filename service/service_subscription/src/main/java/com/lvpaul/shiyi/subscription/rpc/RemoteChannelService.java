package com.lvpaul.shiyi.subscription.rpc;

import com.lvpaul.shiyi.utils.result.Result;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-channel")
public interface RemoteChannelService {
    @RequestMapping("api/channel/getChannelInfoInner")
    public Channel getChannelInfoInner(@RequestParam Long channelId);
}
