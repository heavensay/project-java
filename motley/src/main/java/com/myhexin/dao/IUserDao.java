package com.myhexin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myhexin.entity.PermissionDTO;
import com.myhexin.entity.TResource;
import com.myhexin.entity.TResourceTreeDTO;
import com.myhexin.entity.User;
import com.myhexin.persistent.Page;

public interface IUserDao {
	public User getUserById(Integer id);
	
	public User authenticationUser(@Param("name")String name,@Param("password")String password);
	
	public List<PermissionDTO> queryUserPermission(@Param("name")String name);
	
	public TResourceTreeDTO queryPermissionTreeById(@Param("id")Integer id);
	
	public List<TResource> queryPermissionByPage(Page page);
	
	public void insertTUser(User user);
}
