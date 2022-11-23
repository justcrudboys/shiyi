package com.lvpaul.shiyi.pojo.entity.post;

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
 * 动态帖权限赞助方案关系
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("channel_plan_post_relation")
@ApiModel(value="ChannelPlanPostRelation对象", description="动态帖权限赞助方案关系")
public class ChannelPlanPostRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "动态帖id")
    @TableId(value = "post_id")
    private Long postId;

    @ApiModelProperty(value = "赞助方案id")
    @TableId("plan_id")
    private Long planId;


}
