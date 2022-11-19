package com.lvpaul.shiyi.order.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvpaul.shiyi.order.service.OrderService;
import com.lvpaul.shiyi.pojo.entity.order.Order;
import com.lvpaul.shiyi.pojo.vo.order.OrderRequestVo;
import com.lvpaul.shiyi.utils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @ApiOperation("返回用户所有订单")
    @GetMapping("list")
    public Result getOrderList(@RequestParam Long id){
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        orderService.list(wrapper);
        return Result.success(wrapper);
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
