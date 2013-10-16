package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleTest {
    private final String oracleDriverName = "oracle.jdbc.driver.OracleDriver";
    
    //以下使用的Test就是Oracle里的表空间
    private final String oracleUrlToConnect ="jdbc:oracle:thin:@192.2.2.157:1521:tzsw"; 
    private Connection myConnection = null;
    /**
     * To load the jdbc driver
     * @throws ClassNotFoundException 
     *
     */
    public OracleTest() throws ClassNotFoundException
    {

            Class.forName(oracleDriverName);

    }
    
    public StringBuffer getErrorMessage(Exception ex,String alarmMessage)
    {
        StringBuffer errorStringBuffer = new StringBuffer();
        errorStringBuffer.append(alarmMessage);
        errorStringBuffer.append(ex.getMessage());
        return errorStringBuffer;
    }
    
    /**
     * getConnection method
     * @return Connection
     */
    public Connection getConnection()
    {
        try
        {
            this.myConnection = DriverManager.getConnection(oracleUrlToConnect,"zjwgr","oracle");
            
        }catch(Exception ex)
        {
            System.out.println(getErrorMessage(ex,"Can not get connection,please contact to your Software Designer!").toString());
        }
        
        return this.myConnection;
        
    }
    
    /**
     * @param args
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException {
        OracleTest myOracleTest = new OracleTest();
        try
        {
            Connection myConnection = myOracleTest.getConnection();

            System.out.println("Now begin to excute.............");
        
            PreparedStatement myPreparedStatement = myConnection.prepareStatement("select * from sc08 ");
            //myPreparedStatement.setInt(1,2);
            ResultSet myResultSet = myPreparedStatement.executeQuery();
            StringBuffer myStringBuffer = new StringBuffer();
            
            while(myResultSet.next())
            {
                
                myStringBuffer.append(myResultSet.getInt("area_id")+"  ");
                myStringBuffer.append(myResultSet.getString("area_name")+"  ");
                myStringBuffer.append(myResultSet.getString("ip_address")+"  ");
                myStringBuffer.append(myResultSet.getString("tel")+"  ");
                myStringBuffer.append(myResultSet.getInt("area_type")+"  ");
                myStringBuffer.append(myResultSet.getInt("pc_id")+"\n");
            }
            System.out.println(myStringBuffer.toString());
            //System.out.println(new String(myStringBuffer.toString().getBytes("ISO-8859-1"),"GBK"));
        }catch(Exception ex)
        {
            System.out.println(myOracleTest.getErrorMessage(ex,"Application error,please contact to your Software Designer!").toString());
        }
    }
    } 