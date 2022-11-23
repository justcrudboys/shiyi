package com.lvpaul.shiyi.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvpaul.shiyi.pojo.entity.order.Order;
import com.lvpaul.shiyi.pojo.vo.order.OrderDetailVo;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
public interface OrderService extends IService<Order> {
    public List<OrderDetailVo> getDetailList(List<Order> orderList);
}
