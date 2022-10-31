package com.lvpaul.shiyi.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lvpaul.shiyi.pojo.vo.user.LoginRequestVo;
import com.lvpaul.shiyi.result.Result;
import com.lvpaul.shiyi.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("doLogin")
    public  Result doLogin(@RequestBody LoginRequestVo loginRequest)
    {
        String phone = loginRequest.getPhone();
        String password = loginRequest.getPassword();
        if(loginService.checkUserLogin(phone,password)){
            Long id=loginService.getIdByPhone(phone);
            StpUtil.login(id);
            return Result.success(StpUtil.getPermissionList());
        }
        return Result.error();
    }

}
