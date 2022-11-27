package com.lvpaul.shiyi.order.service.impl;

import com.lvpaul.shiyi.order.mapper.OrderMapper;
import com.lvpaul.shiyi.order.rpc.RemoteChannelService;
import com.lvpaul.shiyi.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.order.Order;
import com.lvpaul.shiyi.pojo.vo.order.OrderDetailVo;
import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    RemoteChannelService remoteChannelService;
    public List<OrderDetailVo> getDetailList(List<Order> orderList){
        List<OrderDetailVo> orderDetailVoList = new ArrayList<>();
        if(orderList.size()==0)
            return orderDetailVoList;
        List<Long> planIdList = orderList.stream().map(Order::getPlanId).collect(Collectors.toList());
        List<Map<String,Object>> planDetailList = remoteChannelService.getChannelInfoInner(planIdList);
        Iterator<Order> orderIterator = orderList.iterator();
        while (orderIterator.hasNext()){
            Order order = orderIterator.next();
            Iterator<Map<String,Object>> planDetailIteretor = planDetailList.iterator();
            while(planDetailIteretor.hasNext()){
                Map<String,Object> planDetail = planDetailIteretor.next();
                Long orderPlanId = order.getPlanId();
                Long detailPlanId = Long.valueOf(planDetail.get("planId").toString());
                if(orderPlanId.equals(detailPlanId)){
                    OrderDetailVo orderDetailVo = new OrderDetailVo(
                            order.getId(),order.getUserId(),(String)planDetail.get("channelName"),
                            order.getPlanId(),(String)planDetail.get("planName"),
                            (Integer) planDetail.get("amount"),
                            order.getSubscribeMonth(),order.getMoneyAmount(),order.getStatus()
                    );
                    orderDetailVoList.add(orderDetailVo);
                }
            }
        }
        return orderDetailVoList;
    }
}
