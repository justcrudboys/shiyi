package com.lvpaul.shiyi.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.vo.user.LoginRequestVo;
import com.lvpaul.shiyi.pojo.vo.user.NameLoginRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import com.lvpaul.shiyi.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("phone")
    public  Result loginByPhone(@RequestBody LoginRequestVo loginRequest)
    {
        String phone = loginRequest.getPhone();
        String password = loginRequest.getPassword();
        if(loginService.checkUserLoginByPhone(phone,password)){
            Long id=loginService.getIdByPhone(phone);
            StpUtil.login(id);
            Map<String,Object> map = new HashMap<>();
            map.put("token","admin-token");
            return Result.success(map);
        }
        return Result.error();
    }
    @PostMapping("name")
    public  Result loginByUsername(@RequestBody NameLoginRequestVo loginRequest)
    {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if(loginService.checkUserLoginByName(username,password)){
            Long id=loginService.getIdByName(username);
            StpUtil.login(id);
            Map<String,Object> map = new HashMap<>();
            map.put("token","admin-token");
            return Result.success(map);
        }
        return Result.error();
    }
}
