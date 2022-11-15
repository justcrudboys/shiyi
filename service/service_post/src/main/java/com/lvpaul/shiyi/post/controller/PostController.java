package com.lvpaul.shiyi.post.controller;

import com.lvpaul.shiyi.post.service.PostService;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.pojo.vo.post.PostRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    PostService postService;

    @ApiOperation("新建动态")
    @PostMapping("createPost")
    public Result createPost(@RequestBody PostRequestVo postRequestVo){
        Long channelId = postRequestVo.getChannelId();
        String content = postRequestVo.getContent();
        Post post = new Post();
        post.setChannelId(channelId);
        post.setContent(content);

        if(postService.save(post))
            return Result.success();
        else
            return Result.error();
    }
    @ApiOperation("获取动态")
    @GetMapping("getPost")
    public Result getChannelPost(@RequestParam(value = "channel_id")Long channel_id){
        List<Post> postList = postService.getChannelPost(channel_id);
        return Result.success(postList);
    }
}
