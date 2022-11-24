package com.lvpaul.shiyi.pojo.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "下订单用户id")
    private Long userId;
    @ApiModelProperty(value = "频道名字")
    private String channelName;
    @ApiModelProperty(value = "赞助方案id")
    private Long planId;
    @ApiModelProperty(value = "赞助方案名字")
    private String planName;
    @ApiModelProperty(value = "赞助计划金额")
    private Integer planAmount;

    @ApiModelProperty(value = "订阅的月数")
    private Integer subscribeMonth;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
