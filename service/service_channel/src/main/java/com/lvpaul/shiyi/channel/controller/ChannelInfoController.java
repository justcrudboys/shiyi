package com.lvpaul.shiyi.channel.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.channel.service.ChannelService;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.pojo.vo.user.UserBriefInfoVo;
import com.lvpaul.shiyi.pojo.vo.user.UserDetailVo;
import com.lvpaul.shiyi.user.service.FileService;
import com.lvpaul.shiyi.utils.file.cos.COSFileUtil;
import com.lvpaul.shiyi.utils.result.Result;
import com.lvpaul.shiyi.user.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/channel")
public class ChannelInfoController {

    @Autowired
    ChannelService channelService;
    @GetMapping("mychannel")
    public Result MyChannel(@RequestParam(value = "creator_id")Long creator_id) {
        //Long id = Long.parseLong((String)StpUtil.getLoginId());
        List<Channel> channelList = channelService.getCreatorChannel(creator_id);
        return Result.success(channelList);
    }
}
