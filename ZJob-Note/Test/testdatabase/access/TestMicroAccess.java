package testdatabase.access;

import java.sql.*;

import org.junit.Test;

public class TestMicroAccess {

	@Test
	public void test1() throws Exception{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String dburl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=//192.168.51.95/share/att2000.mdb";// 此为NO-DSN方式
		//String dburl ="jdbc:odbc:kaoqin";//此为ODBC连接方式;kaoqin:在本机建立odbc名称
		Connection conn = DriverManager.getConnection(dburl);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select top 5  * from checkinout");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + rs.getDate(2).toString() + rs.getString(3));
		}
		rs.close();
		stmt.close();
		conn.close();
	}
	
	/**
	 * 通过rmi协议，远程连接access数据库；需要在access数据库所在服务器上运行一个rmi服务
	 * server端运行命令：java -jar E:\store\all\RmiJdbc\dist\lib\RmiJdbc.jar -port 8899 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2remoteConnAccess() throws Exception{
		try {
			// Register RmiJdbc Driver in jdbc DriverManager
			// On some platforms with some java VMs, newInstance() is
			// necessary...
			Class.forName("org.objectweb.rmijdbc.Driver").newInstance();

			// Test with InstantDB java database engine
			// See http://www.lutris.com/products/instantDB/index.html
			// for info & download
			String url = "jdbc:odbc:kaoqin";
			// RMI host will point to local host
//			String rmiHost = "//192.168.51.94:8899";
			String rmiHost = "//172.20.4.32:8899";
			
//			String rmiHost = "//192.168.4.31:8899";
			
			// RmiJdbc URL is of the form:
			// jdbc:rmi://<rmiHostName[:port]>/<jdbc-url>
			java.sql.Connection c = DriverManager.getConnection("jdbc:rmi:"
					+ rmiHost + "/" + url);
			
			String querySql = "select top 3  * from checkinout where datediff('s',?,checktime)>=0"; 
			java.sql.PreparedStatement st = c.prepareStatement(querySql);
			st.setString(1, "2016/04/20 00:00:00");
			java.sql.ResultSet rs = st.executeQuery();

			java.sql.ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				System.out.print("\nTUPLE: | ");
				for (int i = 1; i <= md.getColumnCount(); i++) {
//					System.out.print(rs.getString(i) + " | ");
					System.out.print(rs.getObject(i) + " | ");
					System.out.print(md.getColumnName(i) + " | ");
				}
			}
			rs.close();
			st.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3(){
		String s = "ab4c1";
		String result = s.replaceAll("[0-9]", "");
		System.out.println(result);
	}
	
	@Test
	public void test4() throws Exception{
		try {
			// Register RmiJdbc Driver in jdbc DriverManager
			// On some platforms with some java VMs, newInstance() is
			// necessary...
			Class.forName("org.objectweb.rmijdbc.Driver").newInstance();

			// Test with InstantDB java database engine
			// See http://www.lutris.com/products/instantDB/index.html
			// for info & download
			String url = "jdbc:odbc:kaoqin";
			// RMI host will point to local host
			String rmiHost = "//127.0.0.1:8899";
			
			// RmiJdbc URL is of the form:
			// jdbc:rmi://<rmiHostName[:port]>/<jdbc-url>
			java.sql.Connection c = DriverManager.getConnection("jdbc:rmi:"
					+ rmiHost + "/" + url);
			
			String querySql = "select top 3  * from userinfo "; 
			java.sql.PreparedStatement st = c.prepareStatement(querySql);
			java.sql.ResultSet rs = st.executeQuery();

			java.sql.ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				System.out.print("\nTUPLE: | ");
				for (int i = 1; i <= md.getColumnCount(); i++) {
//					System.out.print(rs.getString(i) + " | ");
//					rs.getObject("ssn");
					System.out.print(rs.getObject(i) + " | ");
					System.out.print(md.getColumnName(i) + " | ");
					
					if(md.getColumnName(i).equalsIgnoreCase("ssn")){
						System.out.print(" i am snn ");
					}
				}
				
				
			}
			rs.close();
			st.close();
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  static void main(String[] args) throws Exception{
		new TestMicroAccess().test2remoteConnAccess();
	}
}
