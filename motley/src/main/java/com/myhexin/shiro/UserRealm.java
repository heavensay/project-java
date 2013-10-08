package com.myhexin.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.myhexin.dao.IUserDao;
import com.myhexin.entity.PermissionDTO;
import com.myhexin.entity.User;
import com.myhexin.service.UserService;


/**
 * 类CrmRealm的实现描述：shiro领域，用于授权和认证
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao iuserDao;
    @Autowired
    private UserService userService;
    
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = iuserDao.authenticationUser(token.getUsername(),new String(token.getPassword()));
        if (user != null) {
            return new SimpleAuthenticationInfo(user, token.getPassword(),
                                                    user.getName());
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	 User user = (User)getAvailablePrincipal(principals);
    	
    	List<PermissionDTO> permissions = userService.queryUserPermission(user.getName());
    	
    	SimpleAuthorizationInfo infos = new SimpleAuthorizationInfo ();
    	for (PermissionDTO permission : permissions) {
    		infos.addStringPermission(permission.getResourcecode());
		}
    	return infos;
//        return (AuthorizationInfo) SecurityUtils.getSubject().getSession().getAttribute(Constants.USER_RESOURCE);
    }
}
