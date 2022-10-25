package com.lvpaul.shiyi.demo.controller;


import com.lvpaul.shiyi.demo.rpc.RemoteCeshiService;
import com.lvpaul.shiyi.pojo.entity.demo.Student;
import com.lvpaul.shiyi.demo.mapper.StudentMapper;
import com.lvpaul.shiyi.demo.service.StudentService;
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
@RequestMapping("/demo/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RemoteCeshiService remoteCeshiService;
    @GetMapping("findAll")
    public Result findAllStudent(){
        return  Result.success(studentService.list());
    }
    @GetMapping("findById/{id}")
    public Result findById(@PathVariable int id){
        Student student =studentMapper.findStudentById(id);
        if(student==null)
            return Result.error();
        else
            return Result.success(student).message(ResultCodeEnum.DATA_ERROR.getMessage());
    }
    @GetMapping("{id}")
    public Student selectById(@PathVariable int id){ return  studentMapper.selectById(id);}
    @PostMapping("p")
    public boolean postStudent(@RequestBody Student student){
        boolean isSucceed = studentService.save(student);
        return  isSucceed;
    }
    @GetMapping("ceshi")
    public Result ceshi(){
        return remoteCeshiService.getCeshi();
    }
}

