package com.lvpaul.shiyi.pojo.vo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
}
