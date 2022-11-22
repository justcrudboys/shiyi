package com.lvpaul.shiyi.orderqueue;

import com.lvpaul.shiyi.orderqueue.service.OrderPaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {
    @Autowired
    private OrderPaymentService orderPaymentService;
    @Test
    public void contextLoads(){
        orderPaymentService.payOrder("12345");
    }
}
