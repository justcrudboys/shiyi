package com.lvpaul.shiyi.demo.controller;


import com.lvpaul.pojo.entity.demo.Student;
import com.lvpaul.shiyi.demo.mapper.StudentMapper;
import com.lvpaul.shiyi.demo.service.StudentService;
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
    @GetMapping("findAll")
    public List<Student> findAllStudent(){
        return  studentService.list();
    }
    @GetMapping("findById/{id}")
    public Student findById(@PathVariable int id){ return studentMapper.findStudentById(id); }
    @GetMapping("{id}")
    public Student selectById(@PathVariable int id){ return  studentMapper.selectById(id);}
    @PostMapping("p")
    public boolean postStudent(@RequestBody Student student){
        boolean isSucceed = studentService.save(student);
        return  isSucceed;
    }
}

