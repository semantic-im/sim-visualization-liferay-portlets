package com.liferay.scheduler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.utcluj.larkc.mysqlmetrics.server.DBConnector;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.mysql.jdbc.ResultSetMetaData;

public class SchedulerJob implements MessageListener {

    @Override
    public void receive(Message arg0) {

    	System.out.println("Scheduler Job: Clear cache_queries table"); 
    	
    	Statement stmt = null;
    	DBConnector dbConnector = null;

    	JSONArray jsonArray = new JSONArray();
    	String mysqlQuery = "truncate cache_queries";
    	if(mysqlQuery.isEmpty() || mysqlQuery.equalsIgnoreCase("null")){
    		return;
    	}

    	try {

    		dbConnector = new DBConnector();
    		stmt = dbConnector.conn.createStatement();
    		stmt.executeQuery(mysqlQuery);

    		
    	} catch (SQLException e) {

    		e.printStackTrace();
    		return;

    	} finally{

    		try{		
    			stmt.close();
    			dbConnector.closeConnection();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}

    	}//finally

    }

} 