package com.lvpaul.shiyi.pojo.vo.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostRequestVo {

    @ApiModelProperty(value = "频道id")
    private long channelId;

    @ApiModelProperty(value = "动态内容")
    private String content;

}
