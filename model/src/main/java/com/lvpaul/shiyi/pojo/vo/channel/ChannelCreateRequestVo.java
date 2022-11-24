package com.lvpaul.shiyi.pojo.vo.channel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ChannelCreateRequestVo {

    @ApiModelProperty(value = "频道名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "频道简介")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty(value = "频道图片")
    @TableField("img")
    private String img;

    @ApiModelProperty(value = "创作者id")
    @TableField("creator_id")
    private Long creator_id;

    @ApiModelProperty(value = "标签")
    @TableField("tags")
    private ArrayList<Integer> tags;
}

