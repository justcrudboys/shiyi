package com.lvpaul.shiyi.pojo.vo.channel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChannelDetailVo {

    @ApiModelProperty(value = "频道id")
    private Long id;

    @ApiModelProperty(value = "频道名字")
    private String name;

    @ApiModelProperty(value = "频道简介")
    private String introduction;

    @ApiModelProperty(value = "创作者id")
    private Long creatorId;

    @ApiModelProperty(value = "频道图片")
    private String img;

    @ApiModelProperty(value = "频道分类")
    private List<String> tagName;

}
