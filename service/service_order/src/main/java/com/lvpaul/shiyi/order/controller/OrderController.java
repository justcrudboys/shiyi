package com.lvpaul.shiyi.order.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.order.service.OrderService;
import com.lvpaul.shiyi.order.service.impl.AlipayService;
import com.lvpaul.shiyi.pojo.entity.order.Order;
import com.lvpaul.shiyi.pojo.vo.order.AlipayOrder;
import com.lvpaul.shiyi.pojo.vo.order.OrderDetailVo;
import com.lvpaul.shiyi.pojo.vo.order.OrderRequestVo;
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

    @RabbitHandler
    public void changeSubscription(String message){
        System.out.println("=====接受到"+message+"==========");
    }
    @ApiOperation("返回用户所有订单")
    @GetMapping("list")
    public Result getOrderList(){
        Long userId =   Long.parseLong((String) StpUtil.getLoginId());
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Order> orderList= orderService.list(wrapper);
        List<OrderDetailVo> detailVoList = orderService.getDetailList(orderList);
        return Result.success(detailVoList);
    }
    @GetMapping("alipay")
    public Result alipay(@RequestParam Long orderId){
        Order order = orderService.getById(orderId);
        AlipayOrder alipayOrder = new AlipayOrder();
        alipayOrder.setOut_trade_no(orderId.toString());
        alipayOrder.setSubject("拾艺订阅");
        alipayOrder.setTotal_amount(order.getMoneyAmount().toString());

        return Result.success(alipayService.pay(alipayOrder));
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
    @ApiOperation("取消订单")
    @PutMapping("cancellation")
    public Result cancelOrder(@RequestBody Long orderId){
        Long userId =   Long.parseLong((String) StpUtil.getLoginId());
        Order order = orderService.getById(orderId);
        order.setStatus(2);
        if(orderService.updateById(order))
            return Result.success();
        else
            return Result.error();
    }
}
