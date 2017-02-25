package com.lxg.acm.shiro;

import com.lxg.acm.util.StringUtil;
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

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
		logger.info("==========shiro用户获取==========");
		UsernamePasswordToken token = (UsernamePasswordToken) atoken;
		String username = token.getUsername();
		User user = userMapper.queryByUsername(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		logger.info("==========shiro用户获取={"+user.toString()+"}===========");
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				username,user.getPassword(), getName());
		logger.info("==========shiro用户获取info={"+info+"}==========");
		return info;
	}
	
	// 为当前登录的用户授予角色和权限
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("==========shiro获取用户权限==========");
		String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userMapper.findRoles(username));
		logger.info("==========shiro获取用户={"+username+"}权限={"+userMapper.findRoles(username)+"}==========");
		return info;
	}

}
