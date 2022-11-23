package com.lvpaul.shiyi.pojo.vo.order;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequestVo {
    @ApiModelProperty(value = "赞助方案id")
    private Long planId;

    @ApiModelProperty(value = "订阅的月数")
    private Integer subscribeMonth;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal moneyAmount;

}
