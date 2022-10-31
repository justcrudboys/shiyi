package com.lvpaul.shiyi.pojo.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegistryRequestVo {
    @ApiModelProperty(value = "用户手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;
}
