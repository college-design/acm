package com.lxg.acm.shiro;

import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.util.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 用户权限
 * @author Administrator
 *
 */
public class UserRealm extends AuthorizingRealm {

	private static final Log logger = LogFactory.getLog(UserRealm.class);// 日志

	UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) atoken;
		String username = token.getUsername();
		User user = userMapper.queryByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				username,user.getPassword(), getName());
		return info;
	}
	
	// 为当前登录的用户授予角色和权限
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userMapper.findRoles(username));
		return info;
	}

}
