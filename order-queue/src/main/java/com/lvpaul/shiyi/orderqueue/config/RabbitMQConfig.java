package com.lvpaul.shiyi.orderqueue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //1: 声明注册fanout模式交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_payment_exchange",true,false);
    }
    //2: 声明队列
    @Bean
    public Queue paymentQueue(){
        return new Queue("payment.fanout.queue",true);
    }
    //3: 绑定
    @Bean
    public Binding paymentBinding(){
        return BindingBuilder.bind(paymentQueue()).to(fanoutExchange());
    }
}
