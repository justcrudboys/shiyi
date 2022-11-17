package com.lvpaul.shiyi.pojo.entity.order;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Order对象", description="订单")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "下订单用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "赞助方案id")
    @TableField("plan_id")
    private Long planId;

    @ApiModelProperty(value = "订阅的月数")
    @TableField("subscribe_month")
    private Integer subscribeMonth;

    @ApiModelProperty(value = "订单金额")
    @TableField("money_amount")
    private BigDecimal moneyAmount;


}
