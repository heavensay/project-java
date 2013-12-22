package testdatabase.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import common.CommonUtil;

public class TestDataSource {

	/**
	 * jdbc 普通用法查询与插入
	 */
	@Test
	public void test1JdbcQueryAndInsert() throws Exception{
		Connection conn = DatasourceManager.getConnection();
		
		
		String sqlForSeq = " select max(id) id from tuser ";
		PreparedStatement stmt = conn.prepareStatement(sqlForSeq);
		ResultSet resultSet = stmt.executeQuery();
		int maxid = 0;
		while(resultSet.next()){
			maxid = resultSet.getInt("id");
		}
		
		String sqlForInsert = " insert into tuser(id,name,remark) values(?,?,?) ";
		conn.setAutoCommit(false);
		PreparedStatement stmt2 = conn.prepareStatement(sqlForInsert);
		stmt2.setInt(1, ++maxid);
		stmt2.setString(2, RandomStringUtils.random(6, "abcd"));
		stmt2.setString(3, RandomStringUtils.random(6, "abcd"));
		int i1 = stmt2.executeUpdate();
		//记录利用同一个PreparedStatement插入数据，一起提供
		stmt2.setInt(1, ++maxid);
		stmt2.setString(2, RandomStringUtils.random(6, "zxst"));
		stmt2.setString(3, RandomStringUtils.random(6, "zxst"));
		int i2 = stmt2.executeUpdate();
		
		CommonUtil.println(i1);
		CommonUtil.println(i2);
		
		conn.commit();
		stmt.close();
		conn.close();
	}
	
	/**
	 * 测试JDBC3.0版本的savepoint(事务保存回滚点)功能
	 */
	@Test
	public void test2SavePoint() throws Exception{
		Connection conn = DatasourceManager.getConnection();
		
		String sqlForInsert = " insert into tuser(name,remark) values(?,?) ";
		conn.setAutoCommit(false);
		PreparedStatement stmt2 = conn.prepareStatement(sqlForInsert);
		stmt2.setString(1, RandomStringUtils.random(6, "abcd"));
		stmt2.setString(2, RandomStringUtils.random(6, "abcd"));
		int i1 = stmt2.executeUpdate();
		
		Savepoint svpt = conn.setSavepoint("avd");
		
		stmt2.setString(1, RandomStringUtils.random(6, "zxst"));
		stmt2.setString(2, RandomStringUtils.random(6, "zxst"));
		int i2 = stmt2.executeUpdate();
		
		//如果已经提交过了，那么savepoint会无效，报错：
		//java.sql.SQLException: ORA-01086: 从未在此会话中创建保存点 'AVD' 或者该保存点无效
//		conn.commit();
		conn.rollback(svpt);
		conn.commit();
		
		CommonUtil.println(i1);
		CommonUtil.println(i2);
		
		conn.close();
	}
	
}
