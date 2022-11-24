package com.lvpaul.shiyi.creator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.user.User;

import java.util.List;


public interface UserService extends IService<User> {
    public List<User> getUserByName(String name);
}
