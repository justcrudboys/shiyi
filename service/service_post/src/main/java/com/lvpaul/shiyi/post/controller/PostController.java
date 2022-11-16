package com.lvpaul.shiyi.post.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.entity.post.PostAttachment;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.post.service.FileService;
import com.lvpaul.shiyi.post.service.PostAttachmentService;
import com.lvpaul.shiyi.post.service.PostService;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.pojo.vo.post.PostRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    PostAttachmentService postAttachmentService;
    @Autowired
    FileService fileService;

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
        String content = postRequestVo.getContent();
        String datetime = postRequestVo.getDatetime();
        List<String> nameList = postRequestVo.getNameList();
        List<String> urlList = postRequestVo.getUrlList();
        int n = nameList.size();

        Post post = new Post();
        post.setChannelId(channelId);
        post.setContent(content);
        post.setPostTime(datetime);

        if(!postService.save(post))
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
}
