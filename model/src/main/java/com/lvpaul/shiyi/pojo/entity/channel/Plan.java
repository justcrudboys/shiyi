package com.lvpaul.shiyi.pojo.entity.channel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 频道赞助计划
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("channel_plan")
@ApiModel(value="Plan对象", description="频道赞助计划")
public class Plan implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "赞助计划id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "频道id")
    @TableField(value = "channel_id")
    private Long channelId;

    @ApiModelProperty(value = "赞助计划金额(元/月)")
    @TableField(value = "amount")
    private Integer amount;

    @ApiModelProperty(value = "赞助计划名字")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "赞助计划介绍")
    private String introduction;


}
