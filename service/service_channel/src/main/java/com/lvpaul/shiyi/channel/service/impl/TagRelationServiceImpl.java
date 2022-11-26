package com.lvpaul.shiyi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.channel.mapper.PlanMapper;
import com.lvpaul.shiyi.channel.mapper.TagRelationMapper;
import com.lvpaul.shiyi.channel.service.TagRelationService;
import com.lvpaul.shiyi.pojo.entity.channel.Channel;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.entity.channel.Tag;
import com.lvpaul.shiyi.pojo.entity.channel.TagRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 频道标签关系 服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Service
public class TagRelationServiceImpl extends ServiceImpl<TagRelationMapper, TagRelation> implements TagRelationService {
    @Autowired
    TagRelationMapper tagRelationMapper;
    @Override
    public List<TagRelation> getChannelTagRelation(Long channel_id){
        QueryWrapper<TagRelation> qw = new QueryWrapper<>();
        qw.eq("channel_id", channel_id);
        List<TagRelation> tagRelationList = tagRelationMapper.selectList(qw);
        return tagRelationList;
    }

    @Override
    public List<TagRelation> channelByTagId(Long tag_id){
        QueryWrapper<TagRelation> qw = new QueryWrapper<>();
        qw.eq("tag_id", tag_id);
        List<TagRelation> tagRelationList = tagRelationMapper.selectList(qw);
        return tagRelationList;

    public Boolean updateOneChannelTagRelation(List<TagRelation> tagRelationList) {
        for (int i=0;i<tagRelationList.size();i++) {
            TagRelation tagRelation = tagRelationList.get(i);
            if (i==0) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("channel_id", tagRelation.getChannelId());
                int sum = tagRelationMapper.deleteByMap(map);
            }
            if(tagRelationMapper.insert(tagRelation)!=1)
                return false;
        }
        return true;

    }
}
