package com.lvpaul.shiyi.orderqueue.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void payOrder(String orderId){
        //交换机
        String exchangeName = "fanout_payment_exchange";
        //路由
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
}
