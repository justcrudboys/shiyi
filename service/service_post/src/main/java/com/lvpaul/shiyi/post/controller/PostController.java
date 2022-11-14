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
        String datetime = postRequestVo.getDatetime();
        Post post = new Post();
        post.setChannelId(channelId);
        post.setContent(content);
        post.setPostTime(datetime);

        if(postService.save(post))
            return Result.success();
        else
            return Result.error();
    }
}
