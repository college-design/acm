package com.lxg.acm.mapper;

import com.lxg.acm.entity.Link;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LinkMapper
 * Created by 刘雪岗 on 2017/2/9.
 */
@Mapper
public interface LinkMapper {

    @Delete("delete from link")
    void clearLink();

    /**
     * 查询所有链接
     * @return
     */
    @Select("select * from link")
    public List<Link> selectAll();

    /**
     * 分页查询链接
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("select * from link where 1=1 limit #{offset},#{pageSize}")
    public List<Link> queryForList(@Param("offset") Long offset,@Param("pageSize") Long pageSize);

    /**
     * 查询链接总数
     * @return
     */
    @Select("select count(1) from link")
    public Long count();

    /**
     * 删除单个链接
     * @param id
     * @return
     */
    @Delete("delete from link where id =#{id}")
    public Long delete(@Param("id")Integer id);

    /**
     * 添加链接
     * @param name
     * @param url
     * @param type
     * @return
     */
    @Insert("insert into link (name,url,type) values (#{name},#{url},#{type})")
    public Long add(@Param("name") String name,@Param("url") String url,@Param("type") String type);

    /**
     * 更新链接
     * @param name
     * @param url
     * @param type
     * @param id
     * @return
     */
    @Update("update link set name=#{name},url=#{url},type=#{type} where id=#{id}")
    public Long update(@Param("name") String name,@Param("url") String url,@Param("type") String type,@Param("id") Integer id);
}
