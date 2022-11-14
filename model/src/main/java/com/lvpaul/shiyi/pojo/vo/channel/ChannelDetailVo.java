package com.lvpaul.shiyi.pojo.vo.channel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelDetailVo {
    @ApiModelProperty(value = "频道id")
    private Long id;

    @ApiModelProperty(value = "频道名字")
    private String name;

    @ApiModelProperty(value = "频道简介")
    private String introduction;

    @ApiModelProperty(value = "创作者id")
    private Long creator_id;

    @ApiModelProperty(value = "频道图片")
    private String img;
}
