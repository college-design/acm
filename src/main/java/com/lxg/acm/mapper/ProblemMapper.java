package com.lxg.acm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Problem;

@Repository
public interface ProblemMapper {

//	public void save(Long e);

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
}
