package com.lxg.acm.mapper;

import com.lxg.acm.entity.Link;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 刘雪岗 on 2017/2/9.
 */
@Repository
public interface LinkMapper {

    @Select("select * from link")
    public List<Link> selectAll();
}
