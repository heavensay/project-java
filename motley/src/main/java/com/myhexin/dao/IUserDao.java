package com.myhexin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myhexin.entity.PermissionDTO;
import com.myhexin.entity.User;

public interface IUserDao {
	public User getUserById(Integer id);
	
	public User authenticationUser(@Param("name")String name,@Param("password")String password);
	
	public List<PermissionDTO> queryUserPermission(@Param("name")String name);
	
}
