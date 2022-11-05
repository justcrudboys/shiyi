package com.lvpaul.shiyi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.user.mapper.UserMapper;
import com.lvpaul.shiyi.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public  boolean checkUserLoginByPhone(String phone, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        User user = userMapper.selectOne(wrapper);
        if(user!=null&&user.getPassword().equals(password)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean checkUserLoginByName(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if(user!=null&&user.getPassword().equals(password)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Long getIdByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        User user = userMapper.selectOne(wrapper);
        if(user!=null){
            return user.getId();
        }else {
            return null;
        }
    }

    @Override
    public Long getIdByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if(user!=null){
            return user.getId();
        }else {
            return null;
        }
    }
}
