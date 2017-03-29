package com.lxg.acm.mapper;

import com.lxg.acm.entity.Status;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusMapper {

	@Delete("delete from status")
	void clearStatus();

	@Delete("delete from attend")
	void clearAttend();

	//保存提交代码状态
	@SelectKey(keyProperty = "sid", before = false, resultType = Long.class, statement = { "SELECT @@IDENTITY" })
	@Insert("insert into status (uid,username,pid,result,time,memory,language,submittime,code) values (#{uid},#{username},#{pid},#{result},#{time},#{memory},#{language},now(),#{code})")
	public Long save(Status status);

	//添加代码表sid状态id=====code代码
	@Insert("insert into code (sid,code) values (#{sid},#{code})")
	public void insertCode(@Param("sid") Long sid,@Param("code") String code);

	@Select("<script>select * from status where 1=1 <if test=\"pid!=null\"> and pid=#{pid}</if><if test=\"username!=null and username!=''\"> and username=#{username}</if>" +
			" order by sid desc limit #{offset},#{pageSize}</script>")
	public List<Status> queryForList(@Param("pid") Long pid,
			@Param("username") String username, @Param("offset") Long offset,
			@Param("pageSize") Long pageSize);
	
	@Select("select * from status where sid=#{sid}")
	public Status queryBySid(Long sid);
	
	@Select("select code from code where sid=#{sid}")
	public String queryCodeBySid(Long sid);

	@Select("select count(1) from status")
	public Long count();

	@Select("select min(result) as result from status where uid=#{uid} and pid=#{pid}")
	public Integer queryResult(@Param("uid") Long uid, @Param("pid") Long pid);

	@Update("update status set memory=#{memory},time=#{time},result=#{result} where sid=#{sid}")
	public void update(Status status);


	/**
	 * 判断用户是否已通过该题目
	 * @param pid 题目id
	 * @param uid 用户id
	 * @return
	 */
	@Select("select count(1) from status where pid=#{pid} and uid = #{uid} and result=0")
	public int isUserSolvedByPid(@Param("pid")  Long pid,@Param("uid")  Long uid);

}
