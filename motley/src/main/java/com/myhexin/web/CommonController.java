package com.myhexin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhexin.entity.PermissionDTO;
import com.myhexin.service.UserService;

/**
 * 类UserController.java的实现描述：内部用户WEB层
 * 
 * @author admin 2013-3-5 上午09:50:56
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	UserService userService;

	/**
	 * 
	 */
	@RequestMapping(value = "/view1",method=RequestMethod.GET)
	@ModelAttribute(value="list")
	public List<PermissionDTO>  view1(String name) {
			List<PermissionDTO> list = userService.queryUserPermission(name);
			return list;
    }
}
