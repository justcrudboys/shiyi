package com.lvpaul.shiyi.pojo.vo.subscription;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionDetailVo {
    @ApiModelProperty(value = "订阅者用户id")
    private Long userId;

    @ApiModelProperty(value = "订阅的赞助计划id")
    private Long planId;

    @ApiModelProperty(value = "订阅到期时间")
    private LocalDateTime expireTime;

    @ApiModelProperty(value = "频道id")
    private Long channelId;

    @ApiModelProperty(value = "赞助计划金额(元/月)")
    private Integer amount;

    @ApiModelProperty(value = "赞助计划名字")
    private String planName;

    @ApiModelProperty(value = "赞助计划介绍")
    private String planIntroduction;

    @ApiModelProperty(value = "频道名字")
    private String channelName;

    @ApiModelProperty(value = "频道简介")
    private String channelIntroduction;

    @ApiModelProperty(value = "创作者id")
    private Long creator_id;

    @ApiModelProperty(value = "频道图片")
    private String img;
}
