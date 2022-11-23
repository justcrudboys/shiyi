package com.lvpaul.shiyi.order.service.impl;

import com.lvpaul.shiyi.order.mapper.OrderMapper;
import com.lvpaul.shiyi.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvpaul.shiyi.pojo.entity.order.Order;
import com.lvpaul.shiyi.pojo.vo.order.OrderDetailVo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
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
    public List<OrderDetailVo> getDetailList(List<Order> orderList){
        List<OrderDetailVo> orderDetailVoList = orderList.stream().map(res-> new OrderDetailVo(
                res.getId(), res.getUserId(), "频道名字", res.getPlanId(),
                "方案名字",66, res.getSubscribeMonth(),
                res.getMoneyAmount(), res.getStatus()
        )).collect(Collectors.toList());
        return orderDetailVoList;
    }
}
