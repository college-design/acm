package com.lxg.acm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Classifier;
import com.lxg.acm.entity.Problem;

@Repository
public interface ClassifierMapper {

	// 按分类id排序显示分类列表
	@Select("select * from classifier order by cid")
	public List<Classifier> queryall();

	// 查询所有分类列表
	@Select("select cid,title from classifier")
	public List<Classifier> queryAllClassifier();

	// 按分类id查询problem
	@Select("select * from problem left join classifier_problem cp on cp.pid=problem.pid where cid=#{cid}")
	public List<Problem> queryProblemList(Long cid);

	// 插入分类信息
	@Insert("insert into classifier(cid,title,createTime,modifyTime) values(#{cid},#{title},now(),now())")
	public Long save(Classifier classifier);

	// 更新分类信息
	@Update("update classifier set title=#{title},modifyTime=now() where cid=#{cid}")
	public Long update(@Param("title") String title,@Param("cid") Long cid);

	// 按分类id查询分类
	@Select("select * from classifier where cid=#{cid}")
	public Classifier query(Long cid);

	// 分页查询分类信息
	@Select("select * from classifier order by cid limit #{offset},#{pageSize}")
	public List<Classifier> queryForList(Object params,
			@Param("offset") Long page, @Param("pageSize") Long pageSize);

	// 查询分类列表的总个数
	@Select("select count(1) from classifier")
	public Long count();

	// 按分类id删除分类
	@Delete("delete from classifier where cid=#{cid}")
	public void delete(Long cid);
}
