package com.lvpaul.shiyi.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.pojo.vo.user.UserBriefInfoVo;
import com.lvpaul.shiyi.user.service.FileService;
import com.lvpaul.shiyi.utils.file.cos.COSFileUtil;
import com.lvpaul.shiyi.utils.result.Result;
import com.lvpaul.shiyi.user.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user/info")
public class UserInfoController {
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;
    @GetMapping("brief")
    public Result briefInfo(){

        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("avatar",user.getAvatar());
            map.put("name",user.getUsername());
            map.put("roles","user");
            return Result.success(map);

        }else {
            return Result.error().message("id不存在于数据库中");
        }
    }

    @PutMapping("avatar")
    public Result uploadAvatar(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user==null)
            return Result.error().message("id不存在于数据库中");
        String uploadUrl = fileService.uploadAvatar(file);
        if(uploadUrl!=null){
            user.setAvatar(uploadUrl);
            userService.updateById(user);
            return Result.success(uploadUrl).message("文件上传成功");
        }else{
            return Result.error().message("文件上传失败");
        }
    }
}
