package testdatabase.postgres;

import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

/**
 * 测试sqlserver相关的一些功能
 * @author banana
 *
 */
public class TestPostgres {
	Connection conn = null;
	
	@Before
	public void before() throws Exception{
		String url = "jdbc:postgresql://116.62.238.6:5432/jrocketdb";
		String name = "jrocket";
		String pwd = "jrocket";
		Class.forName( "org.postgresql.Driver");
		conn   =   DriverManager.getConnection(url,name,pwd);
	}

	/**
	 * 测试postgres连接是否成功
	 * @throws Exception
	 */
	@Test
	public void connectTest() throws  Exception{
//		Asserts.check(conn.isValid(5000),"postgres连接失败");//数据库不支持valid方法
		Assert.assertNotNull("postgres连接失败",conn);
	}


	/**
	 *简单的查询示例
	 */
	@Test
	public void selectSimpleTest() throws Exception {
		String sql = " select id,name from t_user ";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet result = stmt.executeQuery();
		while(result.next()){
			System.out.println(result.getInt("id"));
			System.out.println(result.getString("name"));
		}
		result.close();
		stmt.close();
		conn.close();
	}

	/**
	 *简单的查询示例
	 */
	@Test
	public void selectSimpleTest2() throws Exception {
		String sql = " select id,created_at from loan_applications where id=4894";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet result = stmt.executeQuery();
		Timestamp date = null;
		while(result.next()){
			System.out.println(result.getInt("id"));
			date = result.getTimestamp("created_at");
			System.out.println(result.getDate("created_at"));
		}
		result.close();
		stmt.close();
		conn.close();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		System.out.println(format.format(date));
	}


	/**
	 *简单的查询示例
	 */
	@Test
	public void insert() throws Exception {
		String sql = " INSERT  into loan_applications(created_at,updated_at) values(?,?) ";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
		stmt.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
		Timestamp date = null;

		stmt.executeUpdate();

		stmt.close();
		conn.close();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		System.out.println(format.format(date));
	}



}
