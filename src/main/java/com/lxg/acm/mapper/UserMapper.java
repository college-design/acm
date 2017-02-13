package com.lxg.acm.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.User;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Repository
@CacheNamespace(size = 100)
public interface UserMapper {

	/**
	 * 用户添加
	 * @param user
	 */
	@Insert("insert into user(username,password,email,nick,school) values(#{username},#{password},#{email},#{nick},#{school})")
	public void save(User user);

	/**
	 * 按uid查询用户
	 * @param uid
	 * @return
	 */
	@Select("select * from user where uid=#{uid}")
	public User query(Long uid);

	@Select("select DISTINCT pid from status where uid=#{uid} and result=0")
	public List<Integer> queryByUidSolved(Long uid);

	@Select("select DISTINCT pid from status where uid=#{uid} and result!=0")
	public List<Integer> queryByUidNoSolved(Long uid);

	@Select("select * from user order by solved desc,accepted asc,submit desc limit #{offset},#{pageSize}")
	public List<User> queryForList(@Param(value = "offset") Long offset,
			@Param(value = "pageSize") Long pageSize);

	@Select("<script>select * from user order by solved desc,accepted asc,submit desc "+
				"limit #{offset},#{pageSize}</script>")
	public List<User> queryForListProblemstatus(@Param("s_uid") Long s_uid,
			@Param("offset") Long page, @Param("pageSize") Long pageSize);

	@Select("select user.username as username,type from user,role where user.username=role.username and type=#{type}")
	public List<User> queryAdminForList(String type);

	@Select("select count(1) from problemstatus")
	public Long countProblemstatus();

	@Select("select * from user order by rand() limit 1")
	public User queryByRand();

	@Delete("delete from user where uid=#{uid}")
	public void delete(Long uid);

	@Update("update user set username=#{username},password=#{password},email=#{email},nick=#{nick},school=#{school} where uid=#{uid}")
	public Long update(User user);

	@Select("select count(1) from user")
	public Long count();
	
	@Select("select * from user where username=#{username}")
	@Options(useCache = true, flushCache = false, timeout = 1000)
	public User queryByUsername(String username);

	@Select("select type from role where username=#{username}")
	public Set<String> findRoles(String username);
	
//	
//	public List<Problem> queryFindProblemList(Object params,
//			@Param("offset") Long page, @Param("pageSize") Long pageSize);

}