package com.lvpaul.shiyi.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.channel.Plan;
import com.lvpaul.shiyi.pojo.entity.post.ChannelPlanPostRelation;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import com.lvpaul.shiyi.post.mapper.ChannelPlanPostRelationMapper;
import com.lvpaul.shiyi.post.mapper.PostMapper;
import com.lvpaul.shiyi.post.rpc.RemotePlanService;
import com.lvpaul.shiyi.post.service.ChannelPlanPostRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态帖权限赞助方案关系 服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Service
public class ChannelPlanPostRelationServiceImpl extends ServiceImpl<ChannelPlanPostRelationMapper, ChannelPlanPostRelation> implements ChannelPlanPostRelationService {

    @Autowired
    PostMapper postMapper;
    @Autowired
    ChannelPlanPostRelationMapper channelPlanPostRelationMapper;

    @Autowired
    RemotePlanService remotePlanService;

    @Override
    public List<Map<String,Object>> getPostPlan(Long channel_id){
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("channel_id",channel_id);
        List<Post> postList = postMapper.selectList(wrapper);
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (Post post : postList) {
            Long id = post.getId();
            QueryWrapper<ChannelPlanPostRelation> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("post_id", id);
            ChannelPlanPostRelation channelPlanPostRelation = channelPlanPostRelationMapper.selectOne(wrapper1);
            Long planId = channelPlanPostRelation.getPlanId();
            QueryWrapper<Plan> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id", planId);
            String planName = remotePlanService.getPlan(planId).getName();
            Map<String, Object> result = new HashMap<>();
            String content = post.getContent();
            if(content.length()>100)
                content=content.substring(0,100)+"...";
            result.put("id", id);
            result.put("postName", post.getPostName());
            result.put("content", content);
            result.put("postTime", post.getPostTime());
            result.put("planName", planName);
            resultList.add(result);
        }
        return resultList;
    }
}
