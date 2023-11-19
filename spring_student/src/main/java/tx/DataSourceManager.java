package tx;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DataSourceManager {

    private static String DRIVER_NAEE = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
    private static String USER_NAEE = "diy";
    private static String PASSWORD = "diy";

    private static Driver driver = null;

    static {
        try {
            driver = (Driver) Class.forName(DRIVER_NAEE).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER_NAEE, PASSWORD);
            return conn;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public static DataSource getDataSource() {
        DataSource dataSource = new SimpleDriverDataSource(driver, URL, USER_NAEE, PASSWORD);
        return dataSource;
    }

    public static DataSource getDataSource2() {
        DataSource dataSource = new SimpleDriverDataSource(driver, URL, USER_NAEE, PASSWORD);
        return dataSource;
    }

}


