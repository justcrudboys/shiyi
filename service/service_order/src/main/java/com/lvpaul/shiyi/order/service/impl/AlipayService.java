package com.lvpaul.shiyi.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lvpaul.shiyi.order.config.AlipayProperties;
import com.lvpaul.shiyi.pojo.vo.order.AlipayOrder;
import org.springframework.stereotype.Component;

/**
 * 支付宝调用服务
 * @author cc
 * @date 2021-12-01 15:53
 */
@Component
public class AlipayService {
    private final AlipayProperties alipayProperties;

    public AlipayService(AlipayProperties alipayProperties) {
        this.alipayProperties = alipayProperties;
    }

    /**
     * 支付接口
     * @author cc
     * @date 2021-12-01 15:54
     */
    public String pay(AlipayOrder order){
        // 支付宝网关
        String serverUrl = alipayProperties.getGatewayUrl();
        // APPID
        String appId = alipayProperties.getAppId();
        // 私钥
        String privateKey = alipayProperties.getPrivateKey();
        // 格式化为 json 格式
        String format = "json";
        // 字符编码格式
        String charset = alipayProperties.getCharset();
        // 公钥，对应APPID的那个
        String alipayPublicKey = alipayProperties.getPublicKey();
        // 签名方式
        String signType = alipayProperties.getSignType();
        // 页面跳转同步通知页面路径
        String returnUrl = alipayProperties.getReturnUrl();
        // 服务器异步通知页面路径
        String notifyUrl = alipayProperties.getNotifyUrl();

        // 1. 初始化client
        AlipayClient client = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

        // 2. 设置请求参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(returnUrl);
        request.setNotifyUrl(notifyUrl);
        System.out.println("JSON.toJSONString(order):" + JSON.toJSONString(order));
        request.setBizContent(JSON.toJSONString(order));

        // 3. 调用支付并获取支付结果
        String result = null;
        try {
            result = client.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

