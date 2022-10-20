package com.lvpaul.shiyi.demo.controller;


import com.lvpaul.entity.model.demo.Student;
import com.lvpaul.shiyi.demo.mapper.StudentMapper;
import com.lvpaul.shiyi.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("findAll")
    public List<Student> findAllStudent(){
        return  studentService.list();
    }
    @GetMapping("findById")
    public Student findById(){ return studentMapper.findStudentById(1); }
}

