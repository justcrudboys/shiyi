package com.lvpaul.shiyi.post.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.pojo.entity.post.PostAttachment;
import com.lvpaul.shiyi.pojo.entity.post.Reply;
import com.lvpaul.shiyi.pojo.vo.post.PostRequestVo;
import com.lvpaul.shiyi.pojo.vo.post.ReplyRequestVo;
import com.lvpaul.shiyi.post.mapper.ReplyMapper;
import com.lvpaul.shiyi.post.mapper.UserMapper;
import com.lvpaul.shiyi.post.service.ReplyService;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/post/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    UserMapper userMapper;

    @ApiOperation("新建评论")
    @PostMapping("createReply")
    public Result createReply(@RequestBody ReplyRequestVo replyRequestVo) {
        Long userId =   Long.parseLong((String) StpUtil.getLoginId());
        Long postId = replyRequestVo.getPostId();
        String content = replyRequestVo.getContent();
        Date datetime = replyRequestVo.getReplyTime();

        Reply reply = new Reply();
        reply.setUserId(userId);
        reply.setPostId(postId);
        reply.setContent(content);
        reply.setReplyTime(datetime);

        if(!replyService.save(reply))
            return Result.error();
        else
            return Result.success();
    }

    @GetMapping("getReply")
    public Result getReply(@RequestParam(value = "post_id")String post_id){
        Long postid = Long.parseLong(post_id);

        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postid);
        List<Reply> replies = replyMapper.selectList(wrapper);
        for(int i=0;i<replies.size();i++){
            Reply reply=replies.get(i);
            reply.setUserName(userMapper.selectNameById(reply.getUserId()));
            reply.setUserAvatar(userMapper.selectAvatarById(reply.getUserId()));
            System.out.println(reply.getReplyTime());
        }

        return Result.success(replies);
    }
}
