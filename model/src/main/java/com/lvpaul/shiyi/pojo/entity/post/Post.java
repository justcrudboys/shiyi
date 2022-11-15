package com.lvpaul.shiyi.pojo.entity.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Post对象", description="动态对象")
public class Post {
    @ApiModelProperty(value = "动态id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "频道id")
    @TableField(value = "channel_id")
    private Long channelId;

    @ApiModelProperty(value = "动态内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "动态发布时间")
    @TableField("post_time")
    private String postTime;
}

