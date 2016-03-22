package mybatis.spring.service;

import mybatis.IUserDao;
import mybatis.User;

public class UserService {
	
	IUserDao iuserDao;
	
	public User getUserById(Integer id){
		User user = iuserDao.getUserById(id);
		return user;
	}
	
	public void insertUser(User user){
		iuserDao.insertUser(user);
	}
	
	

	public void setIuserDao(IUserDao iuserDao) {
		this.iuserDao = iuserDao;
	}
}
