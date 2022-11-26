package com.lvpaul.shiyi.post.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.entity.post.ChannelPlanPostRelation;
import com.lvpaul.shiyi.pojo.entity.post.PostAttachment;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.post.mapper.ChannelMapper;
import com.lvpaul.shiyi.post.mapper.PostAttachmentMapper;
import com.lvpaul.shiyi.post.mapper.UserMapper;
import com.lvpaul.shiyi.post.service.ChannelPlanPostRelationService;
import com.lvpaul.shiyi.post.service.FileService;
import com.lvpaul.shiyi.post.service.PostAttachmentService;
import com.lvpaul.shiyi.post.service.PostService;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.pojo.vo.post.PostRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    ChannelMapper channelMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostService postService;
    @Autowired
    PostAttachmentMapper postAttachmentMapper;

    @Autowired
    PostAttachmentService postAttachmentService;
    @Autowired
    FileService fileService;
    @Autowired
    ChannelPlanPostRelationService channelPlanPostRelationService;

    @PostMapping("file")
    public Result uploadFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String uploadUrl = fileService.uploadFile(file);
        if(uploadUrl==null)
            return Result.error().message("文件上传失败");
        else
            return Result.success(uploadUrl);

    }
    @ApiOperation("新建动态")
    @PostMapping("createPost")
    public Result createPost(@RequestBody PostRequestVo postRequestVo){
        Long channelId = postRequestVo.getChannelId();
        String title = postRequestVo.getTitle();
        String content = postRequestVo.getContent();
        String datetime = postRequestVo.getDatetime();
        List<String> nameList = postRequestVo.getNameList();
        List<String> urlList = postRequestVo.getUrlList();
        long planId = postRequestVo.getPlanId();
        int n = nameList.size();

        Post post = new Post();
        post.setChannelId(channelId);
        post.setPostName(title);
        post.setContent(content);
        post.setPostTime(datetime);
        if(!postService.save(post))
            return Result.error();

        ChannelPlanPostRelation channelPlanPostRelation = new ChannelPlanPostRelation();
        channelPlanPostRelation.setPostId(post.getId());
        channelPlanPostRelation.setPlanId(planId);
        if(!channelPlanPostRelationService.save(channelPlanPostRelation))
            return Result.error();

        for(int i=0;i<n;i++){
            PostAttachment postAttachment = new PostAttachment();
            postAttachment.setPostId(post.getId());
            postAttachment.setName(nameList.get(i));
            postAttachment.setUrl(urlList.get(i));
            if(!postAttachmentService.save(postAttachment))
                return Result.error();
        }

        return Result.success();
    }
    @ApiOperation("获取动态")
    @GetMapping("getPost")
    public Result getChannelPost(@RequestParam(value = "channel_id")Long channel_id){
        List<Post> postList = postService.getChannelPost(channel_id);
        return Result.success(postList);
    }


    @ApiOperation("获取动态和赞助方案")
    @GetMapping("getPostAndPlan")
    public Result getPostAndPlan(@RequestParam(value = "channel_id")Long channel_id){
        List<Map<String,Object>> ObjectList = channelPlanPostRelationService.getPostPlan(channel_id);
        return Result.success(ObjectList);
    }


    @GetMapping("getPostById")
    public Result getPostById(@RequestParam(value = "post_id")String post_id){
        Long postid = Long.parseLong(post_id);
        Post post = postService.getById(postid);
        Long channelId =post.getChannelId();
        Long authorId = channelMapper.selectAuthorIdByChannelId(channelId);
        post.setAuthorAvatar(userMapper.selectAvatarById(authorId));
        post.setAuthorName(userMapper.selectNameById(authorId));
        return Result.success(post);
    }

    @GetMapping("getAttachment")
    public Result getAttachment(@RequestParam(value = "post_id")String post_id){
        Long postid = Long.parseLong(post_id);
        QueryWrapper<PostAttachment> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postid);
        List<PostAttachment> paList = postAttachmentMapper.selectList(wrapper);
        return Result.success(paList);
    }

    @ApiOperation("返回一个post的频道的主人")
    @GetMapping("planhost")
    public Long planHost(@RequestParam Long postId){
        return postService.getPostHost(postId);
    }
}
