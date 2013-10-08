package com.myhexin.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhexin.entity.User;
import com.myhexin.service.UserService;

/**
 * 类UserController.java的实现描述：内部用户WEB层
 * 
 * @author admin 2013-3-5 上午09:50:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 转向创建内部用户页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
//	@ResponseBody
	public String login(User user,String username,String name) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,user.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            return "redirect:/success.jsp";
        }catch (AuthenticationException e) {
//            log.error("登录失败错误信息:"+e);
//            token.clear();
        	System.out.println(e);
            return "redirect:/login.jsp";
        }
	}

}
