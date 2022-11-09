package com.lvpaul.shiyi.creator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.creator.mapper.UserMapper;
import com.lvpaul.shiyi.creator.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
