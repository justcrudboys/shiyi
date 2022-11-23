package com.lvpaul.shiyi.pojo.vo.post;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReplyRequestVo {


    @ApiModelProperty(value = "回复者内容")
    private String content;

    @ApiModelProperty(value = "回复所属动态帖id")
    private Long postId;

    @ApiModelProperty(value = "回复时间")
    private Date replyTime;
}
