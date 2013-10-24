package com.myhexin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhexin.dao.IUserDao;
import com.myhexin.entity.PermissionDTO;
import com.myhexin.entity.TResourceTreeDTO;
import com.myhexin.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	IUserDao iuserDao;
	
	public User getUserById(Integer id){
		User user = iuserDao.getUserById(id);
		return user;
	}
	
	/**
	 * get user's permissions
	 * @param name
	 * @return
	 */
	public List<PermissionDTO> queryUserPermission(String name){
		return iuserDao.queryUserPermission(name);
	}
	
	/**
	 * get user's permissions
	 * @param name
	 * @return
	 */
	public TResourceTreeDTO queryResourceTreeById(Integer id){
		return iuserDao.queryPermissionTreeById(id);
	}
	
}
