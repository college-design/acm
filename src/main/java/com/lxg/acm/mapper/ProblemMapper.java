package com.lxg.acm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.lxg.acm.entity.Problem;

@Mapper
public interface ProblemMapper {

    @Delete("delete from problem")
    void clearProblem();

    @Delete("delete from problemstatus")
    void clearProblemStatus();

    @Select("select * from problem")
    List<Problem> selectAll();

    @Insert("insert into problem (title,description,input,output,sampleInput,sampleOutput,hint,source,sampleCode) values (#{title},#{description},#{input},#{output},#{sampleInput},#{sampleOutput},#{hint},#{source},#{sampleCode})")
    public Long save(Problem problem);

    @Select("select * from problem where pid=#{pid}")
    public Problem query(Long pid);

    @Update("update problem set accepted=#{accepted},submit=#{submit},ratio=#{ratio} where pid=#{pid}")
    public void update(Problem problem);

    @Select("select * from problem where 1=1 order by pid limit #{offset},#{pageSize}")
    public List<Problem> queryForList(Object params,
                                      @Param("offset") Long offset, @Param("pageSize") Long pageSize);

    @Select("select count(1) from problem")
    public Long count();

    @Delete("delete from problem where pid=#{pid}")
    public void delete(Long pid);

    @Update("update problem set title=#{title},description=#{description},input=#{input},output=#{output},sampleInput=#{sampleInput},sampleOutput=#{sampleOutput}," +
            "hint=#{hint},source=#{source},sampleCode=#{sampleCode} where pid=#{pid}")
    public Long updateAdminProblem(Problem problem);
}
