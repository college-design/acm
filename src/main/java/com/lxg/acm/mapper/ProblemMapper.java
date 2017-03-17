package com.lxg.acm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Problem;

@Repository
public interface ProblemMapper {

	@Delete("delete from problem")
	void clearProblem();

	@Delete("delete from problemstatus")
	void clearProblemStatus();

	/**
	 * 添加问题
	 * @param e
	 */
	@Insert("insert into problem (title,description,input,output,sampleInput,sampleOutput,hint,source,sampleCode) values (#{title},#{description},#{input},#{output},#{sampleInput},#{sampleOutput},#{hint},#{source},#{sampleCode})")
	public Long save(Problem problem);

	/**
	 * pid查询题目
	 * @param pid
	 * @return
	 */
	@Select("select * from problem where pid=#{pid}")
	public Problem query(Long pid);

	/**
	 * 修改问题的提交率
	 * @param problem
	 */
	@Update("update problem set accepted=#{accepted},submit=#{submit},ratio=#{ratio} where pid=#{pid}")
	public void update(Problem problem);

	/**
	 * 分页问题列表
	 * @param params
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Select("select * from problem where 1=1 order by pid limit #{offset},#{pageSize}")
	public List<Problem> queryForList(Object params,
			@Param("offset") Long offset, @Param("pageSize") Long pageSize);

	/**
	 * 列表总数
	 * @return
	 */
	@Select("select count(1) from problem")
	public Long count();

	/**
	 * 根据pid删除问题
	 * @param pid
	 */
	@Delete("delete from problem where pid=#{pid}")
	public void delete(Long pid);

	/**
	 * 后台更新问题-----------------------------------
	 * @param problem
	 * @return
	 */
	@Update("update problem set title=#{title},description=#{description},input=#{input},output=#{output},sampleInput=#{sampleInput},sampleOutput=#{sampleOutput}," +
			"hint=#{hint},source=#{source},sampleCode=#{sampleCode} where pid=#{pid}")
	public Long updateAdminProblem(Problem problem);
}
