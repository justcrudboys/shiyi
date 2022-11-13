package com.lvpaul.shiyi.pojo.entity.channel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Channel对象", description="频道对象")
public class Channel {

    @ApiModelProperty(value = "频道id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "频道名字")
    @TableId(value = "name")
    private String name;

    @ApiModelProperty(value = "频道简介")
    @TableId(value = "introduction")
    private String introduction;

    @ApiModelProperty(value = "创作者id")
    @TableField(value = "creator_id")
    private Long creator_id;
}
