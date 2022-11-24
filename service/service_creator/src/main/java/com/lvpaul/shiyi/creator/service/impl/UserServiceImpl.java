package com.lvpaul.shiyi.creator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.creator.mapper.UserMapper;
import com.lvpaul.shiyi.creator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getUserByName(String name){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username",name).eq("is_creator",1);
        List<User> userList = userMapper.selectList(userQueryWrapper);
        return userList;
    }
}
