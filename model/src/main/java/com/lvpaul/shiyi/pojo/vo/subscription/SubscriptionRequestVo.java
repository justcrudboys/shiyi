package com.lvpaul.shiyi.pojo.vo.subscription;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SubscriptionRequestVo {
    @ApiModelProperty(value = "订阅者用户id")
    private Long userId;

    @ApiModelProperty(value = "订阅的赞助计划id")
    private Long planId;

    @ApiModelProperty(value = "订阅月数")
    private int month;
}
