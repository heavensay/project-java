package com.myhexin.web;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhexin.entity.User;
import com.myhexin.service.UserService;

/**
 * 类UserController.java的实现描述：内部用户WEB层
 *
 * @author admin 2013-3-5 上午09:50:56
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    @Autowired
    UserService userService;

    /**
     * 转向创建内部用户页面
     *
     * @return
     */
    // @RequestMapping(params = { "method=hello" })
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello2(/*
     * @RequestParam(value = "rel") String rel,
     */Map<String, Object> map, HttpServletRequest request) {
        try {
            // map.put("rel", URLDecoder.decode(rel, "UTF-8"));
            System.out.println(" base - hello ");
        } catch (Exception e) {
            // logger.error(e.getMessage(), e);
            // throw new WebException(e.getMessage());
        }
        return " aaaaaabbbbbb ";
    }

    /**
     * 转向创建内部用户页面
     *
     * @return
     */
    @RequestMapping(value = "/getUser")
    @ResponseBody
    public String getUserById() {
        System.out.println(" get user by id ");
        User user = userService.getUserById(1);
        return user.getName() + user.getPassword();
    }

}
