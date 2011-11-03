package ro.utcluj.larkc.server;

import java.sql.*;

public class DBConnector
{
	public Connection conn = null;
	public String dbName = "larkc";

	public DBConnector(){
		if(conn==null)
			this.startConnection(dbName);

	}

	public void startConnection(String dbName)
	{
		try
		{
			String userName = "root";
			String password = "1111";
			String url = "jdbc:mysql://localhost/" + dbName;
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection (url, userName, password);
			//System.out.println ("Database connection established");
		}
		catch (Exception e)
		{
			//System.err.println ("Cannot connect to database server");
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
                //System.out.println ("Database connection terminated");
            }
            catch (Exception e) 
            { 
            	/* ignore close errors */
            	e.printStackTrace();
            }
        }
    }
}
