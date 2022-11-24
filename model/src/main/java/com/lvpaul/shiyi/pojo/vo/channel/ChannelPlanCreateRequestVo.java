package com.lvpaul.shiyi.pojo.vo.channel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelPlanCreateRequestVo {
    @ApiModelProperty(value = "频道Id")
    @TableField("channelId")
    private Long channelId;

    @ApiModelProperty(value = "赞助计划金额")
    @TableField("amount")
    private int amount;

    @ApiModelProperty(value = "赞助计划名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "赞助计划简介")
    @TableField("introduction")
    private String introduction;
}

