package com.lvpaul.shiyi.utils.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.lvpaul.shiyi.utils.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }
    //特定异常处理
    @ExceptionHandler({NotLoginException.class})
    @ResponseBody
    public Result error(NotLoginException e){
        e.printStackTrace();
        return Result.error().message("未能读取到有效token");
    }

    //自定义异常处理
//    @ExceptionHandler(CustomerException.class)
//    @ResponseBody
//    public Result error(CustomerException e){
//        e.printStackTrace();
//        return Result.error().code(e.getCode()).message(e.getMsg());
//    }
}