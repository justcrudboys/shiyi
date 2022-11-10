package com.lvpaul.shiyi.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.pojo.vo.user.UserBriefInfoVo;
import com.lvpaul.shiyi.pojo.vo.user.UserDetailVo;
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
    @GetMapping("detail")
    public Result Detail(){

        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user!=null){
            UserDetailVo userDetail = new UserDetailVo();
            userDetail.setId(user.getId());
            userDetail.setIscreator(user.isIscreator());
            userDetail.setPhone(user.getPhone());
            userDetail.setUsername(user.getUsername());
            userDetail.setAvatar(user.getAvatar());
            userDetail.setSignature(user.getSignature());
            return Result.success(userDetail);
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
        if(uploadUrl==null)
            return Result.error().message("文件上传失败");

        user.setAvatar(uploadUrl);
        if(userService.updateById(user))
            return Result.success(uploadUrl);
        else
            return Result.error().message("头像更新失败");

    }
    @PutMapping("signature")
    public Result changeSignature(
            @ApiParam(name = "signature", value = "个性签名", required = true)
            @RequestBody String signature) {
        Long id =   Long.parseLong((String)StpUtil.getLoginId());
        User user = userService.getById(id);
        if(user==null)
            return Result.error().message("id不存在于数据库中");

        user.setSignature(signature);
        if(userService.updateById(user))
            return Result.success();
        else
            return Result.error().message("个性签名更新失败");
    }
}
