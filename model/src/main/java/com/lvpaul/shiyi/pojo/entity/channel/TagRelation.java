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
 * 频道标签关系
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("channel_tag_relation")
@ApiModel(value="TagRelation对象", description="频道标签关系")
public class TagRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "频道id")
      @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    @ApiModelProperty(value = "标签id")
    @TableField(value = "tag_id")
    private Integer tagId;


}
