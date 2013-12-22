package tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import tx.entity.User;


public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public User getUserById(Integer id){
		return null;
	}
	
	public void saveUser(User user){
		
		if(user.getId()!=null){
			jdbcTemplate.update("INSERT INTO TUSER(id,name,password) " +
					" values(?,?,?)",user.getId(),user.getName(),user.getPassword());
		}else{
			jdbcTemplate.update("INSERT INTO TUSER(name,password) " +
					" values(?,?)",user.getName(),user.getPassword());
		}
	}
	
	
	public void saveBeThrowException(){
		throw new RuntimeException("插入异常");
	}
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
