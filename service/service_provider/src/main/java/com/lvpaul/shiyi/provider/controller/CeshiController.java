package com.lvpaul.shiyi.provider.controller;

import com.lvpaul.shiyi.pojo.entity.demo.Student;
import com.lvpaul.shiyi.result.Result;
import com.lvpaul.shiyi.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvpaul
 * @since 2022-10-18
 */
@RestController
@RequestMapping("/provider/ceshi")
public class CeshiController {

    @GetMapping("get")
    public Result getCeshi(){
        Student s= new Student();
        s.setId(222222);
        s.setAge(123123);
        s.setName("小明");
        s.setSex(0);
        return Result.success(s);
    }
}

