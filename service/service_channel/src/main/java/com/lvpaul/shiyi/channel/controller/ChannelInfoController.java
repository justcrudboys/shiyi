package com.lvpaul.shiyi.channel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.channel.mapper.PlanMapper;
import com.lvpaul.shiyi.channel.service.ChannelService;
import com.lvpaul.shiyi.channel.service.ImgService;
import com.lvpaul.shiyi.channel.service.PlanService;
import com.lvpaul.shiyi.channel.service.TagRelationService;
import com.lvpaul.shiyi.channel.service.TagService;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.entity.channel.TagRelation;
import com.lvpaul.shiyi.pojo.entity.channel.Tag;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.pojo.vo.channel.ChannelCreateRequestVo;
import com.lvpaul.shiyi.pojo.vo.channel.ChannelPutRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
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
    @Autowired
    TagRelationService tagRelationService;
    @Autowired
    TagService tagService;
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
        channel.setCreatorId(creator_id);
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
    @ApiOperation("返回当前频道的信息")
    @GetMapping("getChannelInfo")
    public Result getChannelInfo(@RequestParam(value = "channel_id")Long channelId) {
        Channel channel = channelService.getById(channelId);
        if (channel!=null)
            return Result.success(channel);
        else
            return Result.error().message("该频道不存在");
    }
    @ApiOperation("返回当前频道的标签关系")
    @GetMapping("getChannelTagRelation")
    public Result getChannelTagRelation(@RequestParam(value = "channel_id")Long channelId) {
        List<TagRelation> tagRelationList = tagRelationService.getChannelTagRelation(channelId);
        if (tagRelationList.size()!=0)
            return Result.success(tagRelationList);
        else
            return Result.error().message("该频道标签关系不存在");
    }
    @ApiOperation("返回当前标签的名称")
    @GetMapping("getTagName")
    public Result getTagName(@RequestParam(value = "tag_id")Long tagId) {
        Tag tag = tagService.getById(tagId);
        if (tag!=null)
            return Result.success(tag);
        else
            return Result.error().message("该便签Id不存在");
    }
    @ApiOperation("返回当前频道的标签名")
    @GetMapping("getChannelTag")
    public Result getChannelTag(@RequestParam(value = "channel_id")Long channelId) {
        List<TagRelation> tagRelationList = tagRelationService.getChannelTagRelation(channelId);
        List<Tag> tagNameList = new ArrayList<>();
        for(int i=0;i<tagRelationList.size();i++){
            Tag tag = tagService.getById(tagRelationList.get(i).getTagId());
            tagNameList.add(tag);
        }
        if (tagNameList.size()!=0)
            return Result.success(tagNameList);
        else
            return Result.error().message("该频道不存在标签");
    }
    @ApiOperation("修改当前频道的信息")
    @PutMapping("putChannelInfo")
    public Result putChannel(@RequestBody ChannelPutRequestVo channelRequest){
        Long channel_id = channelRequest.getId();
        String name = channelRequest.getName();
        String introduction = channelRequest.getIntroduction();
        String img = channelRequest.getImg();

        Channel channel = channelService.getById(channel_id);
        channel.setName(name);
        channel.setIntroduction(introduction);
        channel.setCreatorId(channel.getCreatorId());
        channel.setImg(img);
        if(channelService.updateById(channel))
            return Result.success();
        else
            return Result.error();
    }

    @GetMapping("getChannelInfoInner")
    public List<Map<String,Object>> getChannelInfoInner(@RequestParam List<Long> planIdList) {
        List<Plan> planList = (List<Plan>) planService.listByIds(planIdList);
        //System.out.println(planList.size());
        List<Long> channelIdList = planList.stream().map(Plan::getChannelId).collect(Collectors.toList());
        //System.out.println(channelIdList.size());
        List<Channel> channelList = (List<Channel>) channelService.listByIds(channelIdList);
        //System.out.println(channelList.size());
        List<Map<String,Object>> resultList = new ArrayList<>();
        for(int i = 0;i < planList.size();i++){
            for(int j = 0;j < channelList.size();j++){
                if(Objects.equals(planList.get(i).getChannelId(), channelList.get(j).getId())){
                    Map<String,Object> result = new HashMap<>();
                    result.put("planId", planList.get(i).getId());
                    result.put("channelId",planList.get(i).getChannelId());
                    result.put("amount",planList.get(i).getAmount());
                    result.put("planName",planList.get(i).getName());
                    result.put("planIntro",planList.get(i).getIntroduction());
                    result.put("channelName",channelList.get(j).getName());
                    result.put("channelIntro",channelList.get(j).getIntroduction());
                    result.put("creatorId",channelList.get(j).getCreatorId());
                    result.put("img",channelList.get(j).getImg());
                    resultList.add(result);
                }
            }
        }
        return resultList;
    }

}
