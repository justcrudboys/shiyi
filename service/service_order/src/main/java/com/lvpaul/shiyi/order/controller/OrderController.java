package com.lvpaul.shiyi.order.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alipay.api.domain.OrderDetail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.order.rpc.RemoteSubscriptionService;
import com.lvpaul.shiyi.order.service.OrderService;
import com.lvpaul.shiyi.order.service.impl.AlipayService;
import com.lvpaul.shiyi.pojo.entity.order.Order;
import com.lvpaul.shiyi.pojo.vo.order.AlipayOrder;
import com.lvpaul.shiyi.pojo.vo.order.OrderRequestVo;
import com.lvpaul.shiyi.pojo.vo.subscription.SubscriptionRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RabbitListener(queues = {"payment.fanout.queue"})
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    AlipayService alipayService;
    @Autowired
    RemoteSubscriptionService remoteSubscriptionService;
    @RabbitHandler
    @GetMapping("aaa")
    public void changeSubscription(String message){
        System.out.println("=====接受到"+message+"==========");
        Long orderId = Long.parseLong(message);
        Order order = orderService.getById(orderId);
        if(order == null)
            throw new RuntimeException();
        order.setStatus(1);
        orderService.updateById(order);
        SubscriptionRequestVo subscriptionRequest=new SubscriptionRequestVo();
        subscriptionRequest.setMonth(order.getSubscribeMonth());
        subscriptionRequest.setUserId(order.getUserId());
        subscriptionRequest.setPlanId(order.getPlanId());
        remoteSubscriptionService.subscribe(subscriptionRequest);
    }
    @ApiOperation("返回用户所有订单")
    @GetMapping("list")
    public Result getOrderList(@RequestParam Long id){
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<Order> orderList= orderService.list(wrapper);
        return Result.success(orderList);
    }
    @GetMapping("alipay")
    public String alipay(@RequestParam Long orderId){
        Order order = orderService.getById(orderId);
        if(order == null)
            throw new RuntimeException();

        AlipayOrder alipayOrder = new AlipayOrder();
        alipayOrder.setOut_trade_no(orderId.toString());
        alipayOrder.setSubject(order.getPlanId().toString());
        alipayOrder.setTotal_amount(order.getMoneyAmount().toString());
        return alipayService.pay(alipayOrder);
    }
    @ApiOperation("生成订单")
    @PostMapping
    public Result createOrder(@RequestBody OrderRequestVo orderRequestVo){
        Long userId =   Long.parseLong((String) StpUtil.getLoginId());
        Order order = new Order();
        order.setUserId(userId);
        order.setPlanId(orderRequestVo.getPlanId());
        order.setMoneyAmount(orderRequestVo.getMoneyAmount());
        order.setSubscribeMonth(orderRequestVo.getSubscribeMonth());
        if(orderService.save(order))
            return Result.success();
        else
            return Result.error();
    }

}
