package com.lxg.acm.shiro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.util.SpringUtil;


/**
 * 用户权限
 * @author Administrator
 *
 */
public class UserRealm extends AuthorizingRealm {

	private static final Log logger = LogFactory.getLog(UserRealm.class);// 日志

	UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken atoken) throws AuthenticationException {
		logger.info("==========shiro用户获取->AuthenticationInfo->start==========");
		UsernamePasswordToken token = (UsernamePasswordToken) atoken;
		String username = token.getUsername();
		User user = userMapper.queryByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		logger.info(">>>>shiro用户权限获取->user=["+user+"]");
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user.getUsername(),user.getPassword(), getName());
		logger.info(">>>>shiro用户权限获取->info=["+info+"]");
		logger.info("==========shiro用户获取->AuthenticationInfo->end==========");
		return info;
	}
	
	// 获取用户权限
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		logger.info("==========shiro用户权限获取->AuthorizationInfo->start==========");
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userMapper.findRoles(username));
		logger.info(">>>>shiro用户权限获取->info=["+info+"]");
		logger.info("==========shiro用户权限获取->AuthorizationInfo->end==========");
		return info;
	}

}
