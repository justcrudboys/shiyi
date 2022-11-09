package com.lvpaul.shiyi.creator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.creator.mapper.CreatorMapper;
import com.lvpaul.shiyi.creator.service.CreatorService;
import com.lvpaul.shiyi.pojo.entity.creator.Creator;
import org.springframework.stereotype.Service;

@Service
public class CreatorServiceImpl extends ServiceImpl<CreatorMapper, Creator> implements CreatorService {
}
