package com.lvpaul.shiyi.pojo.entity.creator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Creator对象", description="创作者对象")
public class Creator {
    @ApiModelProperty(value = "用户id")
    @TableId(value = "id")
    private Long id;

    @ApiModelProperty(value = "创作者简介")
    @TableField("introduction")
    private String introduction;
}
