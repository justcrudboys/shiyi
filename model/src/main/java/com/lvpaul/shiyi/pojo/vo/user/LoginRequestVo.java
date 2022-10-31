package com.lvpaul.shiyi.pojo.vo.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class LoginRequestVo {

        @ApiModelProperty(value = "用户手机号")
        private String phone;


        @ApiModelProperty(value = "用户密码")
        private String password;
}
