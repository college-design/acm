package com.lxg.acm.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Contest;
import com.lxg.acm.entity.Problem;

// 比赛
@Repository
public interface ContestMapper {

	// 保存比赛信息 ------------------------------未完成
//	public void save(Contest contest);

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
}
