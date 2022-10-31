package com.lvpaul.shiyi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.user.mapper.UserMapper;
import com.lvpaul.shiyi.user.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryServiceImpl implements RegistryService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean isPhoneValidate(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        User user = userMapper.selectOne(wrapper);
        if(user==null){ //查不到说明手机号还没被占用
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean isUserNameValidate(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if(user==null){ //查不到说明用户名还没被占用
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean userRegister(String phone, String username, String password) {
        User user =new User();

        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);
        System.out.println(userMapper.insert(user));
        return false;
    }
}
