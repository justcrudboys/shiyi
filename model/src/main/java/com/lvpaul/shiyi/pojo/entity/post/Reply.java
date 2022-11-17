package com.lvpaul.shiyi.pojo.entity.post;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 动态留言回复
 * </p>
 *
 * @author lvpaul
 * @since 2022-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Reply对象", description="动态留言回复")
public class Reply implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "回复id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "回复内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "回复所属动态帖id")
    @TableField("post_id")
    private Long postId;

    @ApiModelProperty(value = "回复时间")
    @TableField("reply_time")
    private Date replyTime;


}
