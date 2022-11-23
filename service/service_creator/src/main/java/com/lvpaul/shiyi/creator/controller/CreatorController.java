package com.lvpaul.shiyi.creator.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.creator.service.CreatorService;
import com.lvpaul.shiyi.creator.service.UserService;
import com.lvpaul.shiyi.pojo.entity.creator.Creator;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/creator")
public class CreatorController {
    @Autowired
    UserService userService;
    @Autowired
    CreatorService creatorService;
    @ApiOperation("返回当前登录用户是否为创作者")
    @GetMapping("iscreator")
    public Result isCreator(){
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user==null)
            return Result.error().message("id不存在于数据库中");
        return Result.success(user.isIscreator());
    }

    @ApiOperation("当前登录用户成为创作者")
    @PostMapping("becomecreator")
    public Result becomeCreator(){
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user==null)
            return Result.error().message("id不存在于数据库中");
        if(user.isIscreator())
            return Result.error().message("已经是创作者了");

        Creator creator = new Creator();
        creator.setId(id);
        user.setIscreator(true);
        if(creatorService.save(creator)&&userService.updateById(user))
            return Result.success(user.isIscreator());
        else
            return Result.error();
    }
    @ApiOperation("返回当前登录用户的创作者信息")
    @GetMapping("personal")
    public Result personalInfo(){
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        Creator creator = creatorService.getById(id);
        if(creator==null)
            return Result.error().message("不是创作者");
        else
            return Result.success(creator);
    }
    @ApiOperation("修改当前登录用户的创作者简介")
    @PutMapping("introduction")
    public Result changeIntroduction(@RequestBody String introduction){
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        Creator creator = creatorService.getById(id);
        if(creator==null)
            return Result.error().message("不是创作者");
        creator.setIntroduction(introduction);
        if(creatorService.updateById(creator))
            return Result.success();
        else
            return Result.error();
    }

    @ApiOperation("修改支付宝账户")
    @PutMapping("account")
    public Result changeAccount(@RequestBody String account){
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        Creator creator = creatorService.getById(id);
        if(creator==null)
            return Result.error().message("不是创作者");
        creator.setAccount(account);
        if(creatorService.updateById(creator))
            return Result.success();
        else
            return Result.error();
    }
}
