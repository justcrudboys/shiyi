package com.lvpaul.shiyi.pojo.vo.channel;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelPutRequestVo {

    @ApiModelProperty(value = "频道id")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "频道名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "频道简介")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty(value = "频道图片")
    @TableField("img")
    private String img;
}

