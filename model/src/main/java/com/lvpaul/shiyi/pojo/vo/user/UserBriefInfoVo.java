package com.lvpaul.shiyi.pojo.vo.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserBriefInfoVo {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String username;
}
