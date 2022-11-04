package com.lvpaul.shiyi.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="User对象", description="用户对象")
public class User{


    @ApiModelProperty(value = "用户id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户手机号")
      @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "用户名")
      @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码")
      @TableField("password")
    private String password;

    @ApiModelProperty(value = "是否是创作者")
    @TableField("is_creator")
    private boolean iscreator;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

}
