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

	@Select("select count(result) from status where result>=0 and pid=#{pid}")
	Long countResultCount(Long pid);
	@Select("select count(result) from status where result=0 and pid=#{pid}")
	Long countResultAC(Long pid);
	@Select("select count(result) from status where result=1 and pid=#{pid}")
	public Long countResultis1(Long pid);
	@Select("select count(result) from status where result=2 and pid=#{pid}")
	public Long countResultTLE(Long pid);
	@Select("select count(result) from status where result=3 and pid=#{pid}")
	public Long countResultMLE(Long pid);
	@Select("select count(result) from status where result=4 and pid=#{pid}")
	public Long countResultWA(Long pid);
	@Select("select count(result) from status where result=5 and pid=#{pid}")
	public Long countResultRE(Long pid);
	@Select("select count(result) from status where result=6 and pid=#{pid}")
	public Long countResultOLE(Long pid);
	@Select("select count(language) from status where language=0 and pid=#{pid}")
	public Long countResultC(Long pid);
	@Select("select count(language) from status where language=1 and pid=#{pid}")
	public Long countResultGCC(Long pid);
	@Select("select count(language) from status where language=2 and pid=#{pid}")
	public Long countResultJAVA(Long pid);

}
