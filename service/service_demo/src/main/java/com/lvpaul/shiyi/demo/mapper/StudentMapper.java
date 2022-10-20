package com.lvpaul.shiyi.demo.mapper;

import com.lvpaul.entity.model.demo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvpaul
 * @since 2022-10-18
 */

@Repository
public interface StudentMapper extends BaseMapper<Student> {
    Student findStudentById(int id);
}
