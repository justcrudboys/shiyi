package com.lvpaul.shiyi.pojo.entity.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="PostAttachment对象", description="动态附件对象")
public class PostAttachment {
    @ApiModelProperty(value = "动态附件id")
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    @ApiModelProperty(value = "动态附件名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "动态id")
    @TableField(value = "post_id")
    private long postId;

    @ApiModelProperty(value = "附件url")
    @TableField(value = "url")
    private String url;
}
