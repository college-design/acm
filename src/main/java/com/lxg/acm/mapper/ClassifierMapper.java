package com.lxg.acm.mapper;

import java.util.List;

import com.lxg.acm.entity.ClassifierProblem;
import org.apache.ibatis.annotations.*;

import com.lxg.acm.entity.Classifier;
import com.lxg.acm.entity.Problem;

@Mapper
public interface ClassifierMapper {

    @Delete("delete from classifier")
    void clearClassifier();

    @Delete("delete from classifier_problem")
    void clearClassifierProblem();

    @Select("select * from classifier order by cid")
    public List<Classifier> queryall();

    @Select("select cid,title from classifier")
    public List<Classifier> queryAllClassifier();

    @Select("select * from problem left join classifier_problem cp on cp.pid=problem.pid where cid=#{cid}")
    public List<Problem> queryProblemList(Long cid);

    @Insert("insert into classifier(cid,title,createTime,modifyTime) values(#{cid},#{title},now(),now())")
    public Long save(Classifier classifier);

    @Update("update classifier set title=#{title},modifyTime=now() where cid=#{cid}")
    public Long update(Classifier classifier);

    @Select("select * from classifier where cid=#{cid}")
    public Classifier query(Long cid);

    @Select("select * from classifier order by cid limit #{offset},#{pageSize}")
    public List<Classifier> queryForList(Object params,
                                         @Param("offset") Long page, @Param("pageSize") Long pageSize);

    @Select("select count(1) from classifier")
    public Long count();

    @Delete("delete from classifier where cid=#{cid}")
    public void delete(Long cid);

    @Select("select count(1) from classifier_problem")
    public Long ClassifierProblemCount();

    @Select("select * from classifier_problem where 1=1 limit #{offset},#{pageSize}")
    public List<ClassifierProblem> ClassifierProblemList(@Param("offset") Long page, @Param("pageSize") Long pageSize);

    @Update("update classifier_problem set pid=#{pid},cid=#{cid} where cpid=#{cpid}")
    public Long updateClassifierProblem(ClassifierProblem classifierProblem);

    @Insert("insert into classifier_problem (pid,cid) values (#{pid},#{cid})")
    public Long saveClassifierProblem(ClassifierProblem classifierProblem);

    @Delete("delete from classifier_problem where cpid=#{cpid}")
    public Long deleteClassifierProblem(Long cpid);

}
