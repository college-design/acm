package com.lxg.acm.mapper;

import com.lxg.acm.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Delete("delete from role")
    void clearRole();

    @Select("select * from role where 1=1 limit #{offset},#{pageSize}")
    public List<Role> queryForList(@Param("offset") Long offset, @Param("pageSize") Long pageSize);

    @Select("select count(1) from role")
    public Long count();

    @Insert("insert into role (type,uid,username) values (#{type},#{uid},#{username})")
    public Long save(Role role);

    @Update("update role set type=#{type},uid=#{uid},username=#{username} where rid=#{rid}")
    public Long update(Role role);

    @Delete("delete from role where rid =#{rid}")
    public Long delete(@Param("rid") Integer rid);

}
