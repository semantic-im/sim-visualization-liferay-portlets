package com.liferay.portlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ro.utcluj.larkc.metrics.server.DBConnector;

public class MysqlAccessPortlet {

	private DBConnector dbConnector = null;

	public ArrayList<String>  getMetricsNames(String level){

		ArrayList<String> metricNames = new ArrayList<String>();
		ResultSet rs = null;
		Statement stmt = null;

		if( level.isEmpty() || (level == null) ){
			return metricNames;
		}
		String mysqlQuery = new String();
		/*
		 * selecting all metrics form the database 
		 */
		mysqlQuery = "select MetricName from metrics ";
		
		if(level.equalsIgnoreCase("platforms")){
			mysqlQuery += "where MetricName like 'Platform%'";
		}else if(level.equalsIgnoreCase("workflows")){
			mysqlQuery += "where MetricName like 'Workflow%'";
		}else if(level.equalsIgnoreCase("plugins")){
			mysqlQuery += "where MetricName like 'Plugin%'";
		}else if(level.equalsIgnoreCase("queries")){
			mysqlQuery += "where MetricName like 'Query%'";
		}
		
		mysqlQuery += " ORDER BY MetricName";
		
		try{
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement(); 
			rs = stmt.executeQuery(mysqlQuery);

			metricNames.add("All");
			while (rs.next()) 
			{
				String columnName = "MetricName";
				try {
					metricNames.add(rs.getString(columnName));			
				} catch (Exception e) {						
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			try{
				rs.close();
				stmt.close();
				dbConnector.closeConnection();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}//finally
		
		return metricNames;
	}
	
	public ArrayList<String>  getPlatformLevels(){
		ArrayList<String> platformLevels = new ArrayList<String>();
		platformLevels.add("Platforms");
		platformLevels.add("Workflows");
		platformLevels.add("Plugins");
		platformLevels.add("Queries");
		platformLevels.add("Systems");
		
		return platformLevels;
		
	}
	
	public ArrayList<String> getLimit() {
		ArrayList<String> limit = new ArrayList<String>();
		limit.add("100");
		limit.add("200");
		limit.add("300");
		limit.add("400");
		limit.add("500");
		limit.add("MAX");
		
		return limit;
	}
	
	public ArrayList<String> getPageSize() {
		ArrayList<String> pageSize = new ArrayList<String>();
		pageSize.add("5");
		pageSize.add("10");
		pageSize.add("15");
		pageSize.add("20");
		pageSize.add("25");
		pageSize.add("30");
		pageSize.add("35");
		pageSize.add("40");
		
		return pageSize;
	}

	public ArrayList<String> getMetricLevels() {
		ArrayList<String> metricLevels = new ArrayList<String>();
		metricLevels.add("Context");
		metricLevels.add("Metrics");
		
		return metricLevels;
	}
	
	public ArrayList<String> getUseQueryLevels() {
		ArrayList<String> useQ = new ArrayList<String>();
		useQ.add("no");
		useQ.add("yes");
		return useQ;
	}
}
