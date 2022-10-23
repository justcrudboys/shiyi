package com.lvpaul.shiyi.demo.service.impl;

import com.lvpaul.shiyi.pojo.entity.demo.Student;
import com.lvpaul.shiyi.demo.mapper.StudentMapper;
import com.lvpaul.shiyi.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvpaul
 * @since 2022-10-18
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
