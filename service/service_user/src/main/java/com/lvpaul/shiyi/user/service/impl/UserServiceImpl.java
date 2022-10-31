package com.lvpaul.shiyi.user.service.impl;

import com.lvpaul.shiyi.pojo.entity.user.User;
import com.lvpaul.shiyi.user.mapper.UserMapper;
import com.lvpaul.shiyi.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-10-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
