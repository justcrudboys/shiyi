package com.lvpaul.shiyi.pojo.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NameLoginRequestVo {

    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "用户密码")
    private String password;
}
