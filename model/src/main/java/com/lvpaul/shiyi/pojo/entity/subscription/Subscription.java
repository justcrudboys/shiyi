package com.lvpaul.shiyi.pojo.entity.subscription;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;

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
 * 订阅权限
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Subscription对象", description="订阅权限")
public class Subscription implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订阅者用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "订阅的赞助计划id")
    @TableField("plan_id")
    private Long planId;

    @ApiModelProperty(value = "订阅到期时间")
    @TableField("expire_time")
    private LocalDateTime expireTime;


}
