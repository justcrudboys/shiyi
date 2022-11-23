package com.lvpaul.shiyi.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置信息类
 * @author cc
 * @date 2021-12-01 15:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {
    private String appId;

    private String privateKey;

    private String publicKey;

    private String returnUrl;

    private String notifyUrl;

    private String signType;

    private String charset;

    private String gatewayUrl;

}