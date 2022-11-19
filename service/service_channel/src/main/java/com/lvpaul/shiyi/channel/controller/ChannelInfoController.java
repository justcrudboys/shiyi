package com.lvpaul.shiyi.channel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.channel.mapper.PlanMapper;
import com.lvpaul.shiyi.channel.service.ChannelService;
import com.lvpaul.shiyi.channel.service.ImgService;
import com.lvpaul.shiyi.channel.service.PlanService;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.vo.channel.ChannelCreateRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/channel")
public class ChannelInfoController {

    @Autowired
    ChannelService channelService;

    @Autowired
    ImgService imgService;
    @Autowired
    PlanService planService;

    @GetMapping("mychannel")
    public Result MyChannel(@RequestParam(value = "creator_id")Long creator_id) {
        //Long id = Long.parseLong((String)StpUtil.getLoginId());
        List<Channel> channelList = channelService.getCreatorChannel(creator_id);
        return Result.success(channelList);
    }

    @PostMapping("uploadImg")
    public Result uploadImg(@ApiParam(name = "file", value = "文件", required = true)
                                @RequestParam("file") MultipartFile file){
        String uploadUrl = imgService.uploadChannelImg(file);
        if(uploadUrl==null)
            return Result.error().message("文件上传失败");
        else
            return Result.success(uploadUrl);
    }

    @PostMapping("createChannel")
    public Result createChannel(@RequestBody ChannelCreateRequestVo channelRequest){
        String name = channelRequest.getName();
        String introduction = channelRequest.getIntroduction();
        String img = channelRequest.getImg();
        Long creator_id = channelRequest.getCreator_id();
        if(!channelService.isNameValidate(name,creator_id))
            return Result.error().message("该频道已存在");
        Channel channel = new Channel();
        channel.setName(name);
        channel.setIntroduction(introduction);
        channel.setCreator_id(creator_id);
        channel.setImg(img);
        if(channelService.save(channel))
            return Result.success();
        else
            return Result.error();
    }

    @GetMapping("channelPlan")
    public Result channelPlan(@RequestParam(value = "channel_id")Long channelId) {
        List<Plan> planList = planService.getChannelPlan(channelId);
        return Result.success(planList);
    }

}
