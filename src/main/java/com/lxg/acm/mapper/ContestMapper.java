package com.lxg.acm.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxg.acm.entity.ContestProblem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Contest;
import com.lxg.acm.entity.Problem;

// 比赛
@Repository
public interface ContestMapper {

	// 添加比赛
	@Insert("insert into contest (title,startTime,endTime,defunct,description) values " +
			"(#{title},#{startTime},#{endTime},#{defunct},#{description})")
	public Long save(Contest contest);

	// 更新比赛
	@Update("update contest set title=#{title},startTime=#{startTime},endTime=#{endTime}," +
			"defunct=#{defunct},description=#{description} where cid =#{cid}")
	public Long update(Contest contest);

	// 按cid查询比赛信息
	@Select("select * from contest where cid=#{cid}")
	public Contest query(Long cid);

	// 按pid查询result=0的个数
	@Select("select count(result) from status where result=0 and pid=#{pid}")
	public Long countResult1(Long pid);

	// 按pid查询result=1的个数
	@Select("select count(result) from status where result=1 and pid=#{pid}")
	public Long countResult2(Long pid);

	// 按pid查询result=2的个数
	@Select("select count(result) from status where result=2 and pid=#{pid}")
	public Long countResult3(Long pid);

	// 按pid查询result=3的个数
	@Select("select count(result) from status where result=3 and pid=#{pid}")
	public Long countResult4(Long pid);

	// 按pid查询result=4的个数
	@Select("select count(result) from status where result=4 and pid=#{pid}")
	public Long countResult5(Long pid);

	// 按pid查询result=5的个数
	@Select("select count(result) from status where result=5 and pid=#{pid}")
	public Long countResult6(Long pid);

	// 按pid查询result=6的个数
	@Select("select count(result) from status where result=6 and pid=#{pid}")
	public Long countResult7(Long pid);

	// 按pid查询language=0的language通过的个数
	@Select("select count(language) from status where language=0 and pid=#{pid}")
	public Long countResult8(Long pid);

	// 按pid查询language=1的language通过的个数
	@Select("select count(language) from status where language=1 and pid=#{pid}")
	public Long countResult9(Long pid);

	// 按pid查询language=2的language通过的个数
	@Select("select count(language) from status where language=2 and pid=#{pid}")
	public Long countResult10(Long pid);

	// 按pid查询language=3的language通过的个数
	@Select("select count(language) from status where language=3 and pid=#{pid}")
	public Long countResult11(Long pid);

	// -----------
	@Select("select * from problem left join contest_problem cp on cp.pid=problem.pid where cid=#{cid} order by num")
	public List<Problem> queryProblemList(Long cid);
	
	@Select("select max(num)+1 from contest_problem where cid=#{cid}")
	public Long queryProblemNum(Long cid);

	@Select("select cid,title from contest")
	public List<Contest> queryForAllContest();

	@Select("select * from attend where cid=#{cid} order by accepts desc,penalty asc")
	@ResultType(HashMap.class)
	public List<Map<String, Object>> queryRank(Long cid);

	@Select("select * from contest where 1=1 order by startTime desc limit #{offset},#{pageSize}")
	public List<Contest> queryForList(Object params,
			@Param("offset") Long page, @Param("pageSize") Long pageSize);

	@Select("select count(1) from contest")
	public Long count();

	@Delete("delete from contest where cid=#{cid}")
	public void delete(Long cid);

	//===================比赛问题关联
	@Select("select * from contest_problem where 1=1  limit #{offset},#{pageSize}")
	public List<ContestProblem> queryForContestProblemList(Object params,
											 @Param("offset") Long page, @Param("pageSize") Long pageSize);
	@Select("select count(1) from contest_problem")
	public Long contestProblemcount();

	@Update("update contest_problem set cid=#{cid},pid=#{pid},title=#{title}," +
			"num=#{num} where cpid =#{cpid}")
	public Long updateContestProblem(ContestProblem contestProblem);

	@Insert("insert into contest_problem (cid,pid,title,num) values " +
			"(#{cid},#{pid},#{title},#{num})")
	public Long saveContestProblem(ContestProblem contestProblem);

	@Delete("delete from contest_problem where cpid=#{cpid}")
	public void deleteContestProblem(Long cpid);
}
