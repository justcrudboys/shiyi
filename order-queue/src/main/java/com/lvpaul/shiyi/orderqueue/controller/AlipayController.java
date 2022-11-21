package com.lvpaul.shiyi.orderqueue.controller;

import com.alipay.api.AlipayApiException;
import com.lvpaul.shiyi.orderqueue.service.OrderPaymentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class AlipayController {
    @Autowired
    OrderPaymentService orderPaymentService;
    @PostMapping("/notify")
    public String finishNotify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        // 支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        // 交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        orderPaymentService.payOrder(out_trade_no);
        System.out.println("==========");
        System.out.println("out_trade_no: " + out_trade_no);
        System.out.println("trade_no: " + trade_no);
        System.out.println("trade_status: " + trade_status);

        return "success";
    }
}
