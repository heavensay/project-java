package testdatabase.postgres;

import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
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
		String url = "jdbc:postgresql://127.0.0.1:5432/test";
		String name = "test";
		String pwd = "test";
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


}
