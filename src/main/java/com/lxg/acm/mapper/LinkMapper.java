package com.lxg.acm.mapper;

import com.lxg.acm.entity.Link;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LinkMapper {

    @Delete("delete from link")
    void clearLink();

    @Select("select * from link")
    public List<Link> selectAll();

    @Select("select * from link where 1=1 limit #{offset},#{pageSize}")
    public List<Link> queryForList(@Param("offset") Long offset, @Param("pageSize") Long pageSize);

    @Select("select count(1) from link")
    public Long count();

    @Delete("delete from link where id =#{id}")
    public Long delete(@Param("id") Integer id);

    @Insert("insert into link (name,url,type) values (#{name},#{url},#{type})")
    public Long add(@Param("name") String name, @Param("url") String url, @Param("type") String type);

    @Update("update link set name=#{name},url=#{url},type=#{type} where id=#{id}")
    public Long update(@Param("name") String name, @Param("url") String url, @Param("type") String type, @Param("id") Integer id);
}
