package com.lxg.acm.mapper;

import java.util.List;

import com.lxg.acm.entity.ClassifierProblem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Classifier;
import com.lxg.acm.entity.Problem;

@Repository
public interface ClassifierMapper {

	// 分类id排序
	@Select("select * from classifier order by cid")
	public List<Classifier> queryall();

	// 分类列表
	@Select("select cid,title from classifier")
	public List<Classifier> queryAllClassifier();

	// 分类下问题列表
	@Select("select * from problem left join classifier_problem cp on cp.pid=problem.pid where cid=#{cid}")
	public List<Problem> queryProblemList(Long cid);

	// 添加分类
	@Insert("insert into classifier(cid,title,createTime,modifyTime) values(#{cid},#{title},now(),#{modifyTime})")
	public Long save(Classifier classifier);

	// 修改分类
	@Update("update classifier set title=#{title},modifyTime=now() where cid=#{cid}")
	public Long update(Classifier classifier);

	// cid查询分类
	@Select("select * from classifier where cid=#{cid}")
	public Classifier query(Long cid);

	// 按cid排序分页===分类
	@Select("select * from classifier order by cid limit #{offset},#{pageSize}")
	public List<Classifier> queryForList(Object params,
			@Param("offset") Long page, @Param("pageSize") Long pageSize);

	// 分类总数
	@Select("select count(1) from classifier")
	public Long count();

	// 删除分类
	@Delete("delete from classifier where cid=#{cid}")
	public void delete(Long cid);

	@Select("select count(1) from classifier_problem")
	public Long ClassifierProblemCount();

	//分类问题关联列表
	@Select("select * from classifier_problem where 1=1 limit #{offset},#{pageSize}")
	public List<ClassifierProblem> ClassifierProblemList(@Param("offset") Long page, @Param("pageSize") Long pageSize);

	// 分类与问题关联修改
	@Update("update classifier_problem set pid=#{pid},cid=#{cid} where cpid=#{cpid}")
	public Long updateClassifierProblem(ClassifierProblem classifierProblem);

	// 分类问题关联添加
	@Insert("insert into classifier_problem (pid,cid) values (#{pid},#{cid})")
	public Long saveClassifierProblem(ClassifierProblem classifierProblem);

	// 分类问题关联删除
	@Delete("delete from classifier_problem where cpid=#{cpid}")
	public Long deleteClassifierProblem(Long cpid);

}
