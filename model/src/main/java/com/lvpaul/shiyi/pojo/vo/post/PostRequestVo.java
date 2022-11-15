package com.lvpaul.shiyi.pojo.vo.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostRequestVo {

    @ApiModelProperty(value = "频道id")
    private long channelId;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "动态时间")
    private String datetime;

    @ApiModelProperty(value = "附件名列表")
    private List<String> nameList;

    @ApiModelProperty(value = "附件url列表")
    private List<String> urlList;


}
