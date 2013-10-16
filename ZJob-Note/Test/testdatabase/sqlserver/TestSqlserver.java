package testdatabase.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

/**
 * 测试sqlserver相关的一些功能
 * @author banana
 *
 */
public class TestSqlserver {
	Connection conn = null;
	
	@Before
	public void before() throws Exception{
		Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		conn   =   DriverManager.getConnection( 
		"jdbc:sqlserver://127.0.0.1;DatabaseName=dir","dir","dir"); 
	}
	
	/**
	 * 测试sqlserver中查询 clob/text字段 
	 * @throws Exception
	 */
	@Test
	public void queryClob() throws Exception{
		
		String sql = " select id,name,content from student ";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet result = stmt.executeQuery();
		while(result.next()){
			System.out.println(result.getInt("id"));
			System.out.println(result.getString("name"));
			System.out.println(result.getString("content"));
			java.sql.Clob content = result.getClob("content");
			System.out.println(content.getSubString(1l, 1000));
		}
		conn.close();
	}
	
	/**
	 * 测试sqlserver中插入clob/text字段
	 * @throws Exception
	 */
	@Test 
	public void insertClob() throws Exception{
		String sql = " insert into student(id,name,content) values(?,?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 112);
		stmt.setString(2, "Jerry");
		stmt.setString(3, "日期：20120717");
		stmt.executeUpdate();
		stmt.setInt(1, 113);
		stmt.setString(2, "Jerry2");
		
		java.sql.Clob clob = conn.createClob();
		clob.setString(1l, " hi,I be created by clob !");
		
		stmt.setClob(3, clob);
		stmt.executeUpdate();
		
		conn.commit();
		stmt.close();
		conn.close();
		
	}
	
}
