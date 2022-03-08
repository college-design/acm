package com.lxg.acm.mapper;

import com.lxg.acm.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Repository
@CacheNamespace(size = 100)
public interface UserMapper {

	@Delete("delete from user")
	void clearUser();

	// 登录
	@Select("select * from user where username=#{username} and password=#{password}")
	public User login(@Param(value = "username") String username, @Param(value = "password") String password);

	/**
	 * 用户添加
	 * @param user
	 */
	@Insert("insert into user(username,password,email,nick,school,createTime) values(#{username},#{password},#{email},#{nick},#{school},now())")
	public Long save(User user);

	/**
	 * 按uid查询用户
	 * @param uid
	 * @return
	 */
	@Select("select * from user where uid=#{uid}")
	public User query(Long uid);

	/**
	 * 查询用户解决的题号
	 * @param uid
	 * @return
	 */
	@Select("select DISTINCT pid from status where uid=#{uid} and result=0")
	public List<Integer> queryByUidSolved(Long uid);

	/**
	 * 查询用户未通过的题号
	 * @param uid
	 * @return
	 */
	@Select("select DISTINCT pid from status where uid=#{uid} and result!=0")
	public List<Integer> queryByUidNoSolved(Long uid);

	/**
	 * 用户排名排序
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Select("select * from user order by solved desc,submit asc,accepted desc limit #{offset},#{pageSize}")
	public List<User> queryForList(@Param(value = "offset") Long offset,
			@Param(value = "pageSize") Long pageSize);

	@Select("<script>select * from user where 1=1 <if test='username!=null'>and username like '%#{username}%'</if> order by solved desc,accepted asc,submit desc limit #{offset},#{pageSize}</script>")
	public List<User> queryForListByUser(@Param(value = "offset") Long offset,
								   @Param(value = "pageSize") Long pageSize,User user);

	/**
	 * 用户运行状态查询
	 * @param s_uid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@Select("<script>select * from user order by solved desc,accepted asc,submit desc "+
				"limit #{offset},#{pageSize}</script>")
	public List<User> queryForListProblemstatus(@Param("s_uid") Long s_uid,
			@Param("offset") Long page, @Param("pageSize") Long pageSize);

	/**
	 * 按权限类型查询用户
	 * @param type
	 * @return
	 */
	@Select("select user.username as username,type from user,role where user.username=role.username and type=#{type}")
	public List<User> queryAdminForList(String type);

	/**
	 * 查询所有的问题状态
	 * @return
	 */
	@Select("select count(1) from problemstatus")
	public Long countProblemstatus();

	//----------------------------
	@Select("select * from user order by rand() limit 1")
	public User queryByRand();

	/**
	 * 根据用户uid删除用户
	 * @param uid
	 */
	@Delete("delete from user where uid=#{uid}")
	public void delete(Long uid);

	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@Update("<script>update user set " +
			"<if test='username!=null'>username=#{username},</if>" +
			"<if test='password!=null'>password=#{password},</if>" +
			"<if test='email!=null'>email=#{email},</if>" +
			"<if test='nick!=null'>nick=#{nick},</if>" +
			"<if test='school!=null'>school=#{school},</if>" +
			"<if test='solved!=null'>solved=#{solved},</if>" +
			"<if test='accepted!=null'>accepted=#{accepted},</if>" +
			"<if test='submit!=null'>submit=#{submit},</if> " +
//			"<if test='accesstime!=null'>accesstime=#{accesstime},</if> " +
			"<if test='defunct!=null'>defunct=#{defunct}</if> " +
			"where uid=#{uid}</script>")
	Long update(User user);

	/**
	 * 查询用户总数
	 * @return
	 */
	@Select("select count(1) from user")
	public Long count();

	/**
	 * 按用户名查询用户
	 * @param username
	 * @return
	 */
	@Select("select * from user where username=#{username}")
	public User queryByUsername(String username);

	/**
	 * 根据用户名查询用户权限
	 * @param username
	 * @return
	 */
	@Select("select type from role where username=#{username}")
	public Set<String> findRoles(String username);
	
//	
//	public List<Problem> queryFindProblemList(Object params,
//			@Param("offset") Long page, @Param("pageSize") Long pageSize);

	//==========用户解决题目总数(不相同)
	@Select("SELECT COUNT(DISTINCT pid) AS solved FROM STATUS WHERE uid=#{uid} AND result=0")
	public Long getUserSolved(Long uid);
	//===========用户通过次数
	@Select("SELECT COUNT(1) FROM STATUS WHERE uid=#{uid} AND result=0")
	public Long getUserAccepted(Long uid);

	@Select("select count(1) from user where username=#{username}")
	Integer isUserNameAdd(String username);

	@Update("update user set password=#password{} where username=#{username}")
	Integer updateUserPassword(String password,String username);

	@Select("select * from user where username=#{username} and password=#{password}")
	Integer isUserPasswordPass(String username,String password);
}