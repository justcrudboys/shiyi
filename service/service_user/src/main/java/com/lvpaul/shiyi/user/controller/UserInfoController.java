package com.lvpaul.shiyi.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.pojo.vo.user.UserBriefInfoVo;
import com.lvpaul.shiyi.result.Result;
import com.lvpaul.shiyi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/info")
public class UserInfoController {
    @Autowired
    UserService userService;
    @GetMapping("brief")
    public Result briefInfo(){
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user!=null){
            UserBriefInfoVo briefInfoVo = new UserBriefInfoVo();
            briefInfoVo.setId(user.getId());
            briefInfoVo.setUsername(user.getUsername());
            briefInfoVo.setPhone(user.getPhone());
            return Result.success(briefInfoVo);
        }else{
            return Result.error().message("id不存在于数据库中");
        }
    }
}
