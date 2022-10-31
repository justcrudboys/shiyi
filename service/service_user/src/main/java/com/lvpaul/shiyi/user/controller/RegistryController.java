package com.lvpaul.shiyi.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.pojo.vo.user.LoginRequestVo;
import com.lvpaul.shiyi.pojo.vo.user.RegistryRequestVo;
import com.lvpaul.shiyi.result.Result;
import com.lvpaul.shiyi.user.service.RegistryService;
import com.lvpaul.shiyi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/registry")
public class RegistryController {

    @Autowired
    RegistryService registryService;
    @Autowired
    UserService userService;
    @PostMapping("doRegistry")
    public Result doRegistry(@RequestBody RegistryRequestVo registryRequest)
    {
        String phone = registryRequest.getPhone();
        String password = registryRequest.getPassword();
        String username = registryRequest.getUsername();
        if(!registryService.isPhoneValidate(phone)){
            return Result.error().message("手机号已注册");
        }
        if(!registryService.isUserNameValidate(username)){
            return Result.error().message("用户名已被使用");
        }
        User user = new User();
        user.setPassword(password);
        user.setPhone(phone);
        user.setUsername(username);
        if(userService.save(user)){
            return Result.success();
        }
        return Result.error();
    }
}
