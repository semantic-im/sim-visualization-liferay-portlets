package ro.utcluj.larkc.mysqlmetrics.server;

import java.sql.*;

import com.liferay.portlet.MysqlMetricsPortlet;





public class DBConnector 
{
	public Connection conn = null;
	//public String dbName = "larkc";
	public String dbName = "";
	public String userName = "";
	public String password = "";
	public DBConnector(){
		if(conn==null)
			this.startConnection(dbName);

	}


	public void startConnection(String dbName)
	{


		try
		{	
			dbName = MysqlMetricsPortlet.dbName;
			userName = MysqlMetricsPortlet.userName;
			password = MysqlMetricsPortlet.password; 
			
			String url = "jdbc:mysql://localhost/" + dbName + "?zeroDateTimeBehavior=convertToNull";
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection (url, userName, password);
			System.out.println ("Database connection established");
		}
		catch (Exception e)
		{
			System.err.println ("Cannot connect to database server");
			e.printStackTrace();
		}
		finally
		{

		}
	}
	
	public void closeConnection(){
    	if (conn != null)
        {
            try
            {
                conn.close ();
                System.out.println ("Database connection terminated");
            }
            catch (Exception e) { /* ignore close errors */ }
        }
    }
	
	

}
