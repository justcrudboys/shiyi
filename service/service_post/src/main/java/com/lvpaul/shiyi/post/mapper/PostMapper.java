package com.lvpaul.shiyi.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvpaul.shiyi.pojo.entity.post.Post;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper extends BaseMapper<Post> {

}
