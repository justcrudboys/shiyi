package com.lvpaul.shiyi.channel.rpc;

import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.utils.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-user")
public interface RemoteUserService {
    @RequestMapping("api/user/info/searchInfo")
    public User searchUser(@RequestParam(value = "user_id") Long userId);
}
