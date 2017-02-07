package com.lxg.acm.shiro;

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

	UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken atoken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) atoken;
		String username = token.getUsername();
		User user = userMapper.queryByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user.getUsername(),user.getPassword(), getName());
		return info;
	}
	
	// 获取用户权限
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userMapper.findRoles(username));
		return info;
	}

}
