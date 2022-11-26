package com.lvpaul.shiyi.pojo.vo.creator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreatorDetailVo {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ApiModelProperty(value = "创作者简介")
    private String introduction;
}
