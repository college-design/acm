package com.lxg.acm.mapper;

import com.lxg.acm.entity.Link;
import com.lxg.acm.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色
 * Created by 刘雪岗 on 2017/2/13.
 */
@Repository
public interface RoleMapper {

    @Delete("delete from role")
    void clearRole();

    /**
     * 查询角色列表
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("select * from role where 1=1 limit #{offset},#{pageSize}")
    public List<Role> queryForList(@Param("offset") Long offset, @Param("pageSize") Long pageSize);

    /**
     * 查询用户角色总数
     * @return
     */
    @Select("select count(1) from role")
    public Long count();

    /**
     * 添加用户角色信息
     * @param role
     * @return
     */
    @Insert("insert into role (type,uid,username) values (#{type},#{uid},#{username})")
    public Long save(Role role);

    /**
     * 更新用户角色信息
     * @param role
     * @return
     */
    @Update("update role set type=#{type},uid=#{uid},username=#{username} where rid=#{rid}")
    public Long update(Role role);

    @Delete("delete from role where rid =#{rid}")
    public Long delete(@Param("rid")Integer rid);

}
