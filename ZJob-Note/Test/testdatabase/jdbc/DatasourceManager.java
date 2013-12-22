package testdatabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatasourceManager {
	
	public static Connection getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl",
										"diy", "diy");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
		
	}
	
}
