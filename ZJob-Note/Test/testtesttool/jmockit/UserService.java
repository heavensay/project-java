package testtesttool.jmockit;

public class UserService {
	
	public UserDao userDao;
	
	public User getUser(String name){
		User user = userDao.getUser(name);
		return user;
	}

	public String getUserName(Integer id){
		String name = userDao.getUserName(id);
		return name;
	}

	public String getBedroom(String name){
		return "bedroom 001:"+name;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
