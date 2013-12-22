package com.myhexin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhexin.dao.IProductDao;
import com.myhexin.entity.PctMetal;
import com.myhexin.entity.User;

@Service
@Transactional("slaveTransactionManager")
public class ProductService {
	
	@Autowired
	IProductDao iproductDao;
	
	@Autowired
	UserService userService;
	
	/**
	 * 理财专区->贵金属
	 * @return
	 */
	public List<PctMetal> queryPctMetal(String pctcode){
		return iproductDao.queryPctMetal(pctcode);
	}
	
	public void insertPctMetal(PctMetal pctMetal){
		iproductDao.insertPctMetal(pctMetal);
		User user = new User();
		user.setName("ttt");
		user.setPassword("333");
		userService.insertTuser(user);
	}
}
