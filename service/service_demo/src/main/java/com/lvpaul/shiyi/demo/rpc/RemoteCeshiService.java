package com.lvpaul.shiyi.demo.rpc;

import com.lvpaul.shiyi.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-provider")
public interface RemoteCeshiService {
    @RequestMapping("/provider/ceshi/get")
    public Result getCeshi();
}
