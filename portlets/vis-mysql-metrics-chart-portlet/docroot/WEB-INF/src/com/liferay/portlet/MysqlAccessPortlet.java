package com.liferay.portlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ro.utcluj.larkc.mysqlmetrics.server.DBConnector;



public class MysqlAccessPortlet {

	

	public ArrayList<String>  getMetricsNames(String level){
		
		DBConnector dbConnector = null;
		ArrayList<String> metricNames = new ArrayList<String>();
		ResultSet rs = null;
		Statement stmt = null;

		if( level.isEmpty() || (level == null) ){
			return metricNames;
		}
		
		/*
		 * selecting all metrics form the database 
		 */
		String mysqlQuery = "select * from metrics ";
		
		if(level.equalsIgnoreCase("system")){
			mysqlQuery += "where MetricName like 'system%'";
		}else if(level.equalsIgnoreCase("platform")){
			mysqlQuery += "where MetricName like 'platform%'";
		}else if(level.equalsIgnoreCase("workflows")){
			mysqlQuery += "where MetricName like 'workflow%'";
		}else if(level.equalsIgnoreCase("plugins")){
			mysqlQuery += "where MetricName like 'plugin%'";
		}else if(level.equalsIgnoreCase("queries")){
			mysqlQuery += "where MetricName like 'query%'";
		}
		
		mysqlQuery += " and MetricType = 2 ORDER BY MetricName";
		
		try{
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement(); 
			rs = stmt.executeQuery(mysqlQuery);

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
				if(rs != null) rs.close();
				if(stmt != null) rs.close();
				//stmt.close();
				if(dbConnector != null) dbConnector.closeConnection();
			}catch(SQLException e) {
				e.printStackTrace();
			}

		}//finally
		
		return metricNames;
	}//get Metric Names based on a platform level
	
	/*
	 * Get platform levels
	 */
	public ArrayList<String>  getPlatformLevels(){
		ArrayList<String> platformLevels = new ArrayList<String>();

		platformLevels.add("System");
		platformLevels.add("Platform");
		platformLevels.add("Workflows");
		platformLevels.add("Plugins");
		platformLevels.add("Queries");

		return platformLevels;

	}
	
	/*
	 * Get Chart Types
	 */
	public ArrayList<String>  getChartTypes(){
		ArrayList<String> chartTypes = new ArrayList<String>();

		chartTypes.add("LineChart");
		chartTypes.add("PieChart");
		chartTypes.add("BarChart");
	
		return chartTypes;

	}
	
	public ArrayList<String>  getRefreshTimeTexts(){
		
		ArrayList<String> refreshTimeTexts = new ArrayList<String>();

		refreshTimeTexts.add("3 Seconds");
		refreshTimeTexts.add("5 Seconds");
		refreshTimeTexts.add("10 Seconds");
		refreshTimeTexts.add("20 Seconds");
		refreshTimeTexts.add("30 Seconds");
		refreshTimeTexts.add("60 Seconds");
		refreshTimeTexts.add("2 Minutes");
		refreshTimeTexts.add("5 Minutes");
	
		return refreshTimeTexts;
		
	}
	
	public ArrayList<String>  getRefreshTimeValues(){

		ArrayList<String> refreshTimeValues = new ArrayList<String>();

		refreshTimeValues.add("3000");
		refreshTimeValues.add("5000");
		refreshTimeValues.add("10000");
		refreshTimeValues.add("20000");
		refreshTimeValues.add("30000");
		refreshTimeValues.add("60000");
		refreshTimeValues.add("120000");
		refreshTimeValues.add("300000");

		return refreshTimeValues;

	}
	
public ArrayList<String>  getPreselectedPeriodTexts(){
		
		ArrayList<String> preselectedPeriodTexts = new ArrayList<String>();

		preselectedPeriodTexts.add("1 Minute");
		preselectedPeriodTexts.add("5 Minutes");
		preselectedPeriodTexts.add("10 Minutes");
		preselectedPeriodTexts.add("20 Minutes");
		preselectedPeriodTexts.add("30 Minutes");
		preselectedPeriodTexts.add("1 Hour");
		preselectedPeriodTexts.add("2 Hours");
		preselectedPeriodTexts.add("4 Hours");
		
	
		return preselectedPeriodTexts;
		
	}
	
	public ArrayList<String>  getPreselectedPeriodValues(){

		ArrayList<String> preselectedPeriodValues = new ArrayList<String>();

		preselectedPeriodValues.add("60000"); 
		preselectedPeriodValues.add("300000"); //5 Minutes
		preselectedPeriodValues.add("600000");
		preselectedPeriodValues.add("1200000");
		preselectedPeriodValues.add("1800000");
		preselectedPeriodValues.add("3600000");
		preselectedPeriodValues.add("7200000");
		preselectedPeriodValues.add("14400000");

		return preselectedPeriodValues;

	}
	
	public ArrayList<String>  getLineChartResolutions(){

		ArrayList<String> lineChartResolutions = new ArrayList<String>();

		lineChartResolutions.add("10");
		lineChartResolutions.add("30");
		lineChartResolutions.add("50");
		lineChartResolutions.add("100");
		lineChartResolutions.add("200");
		lineChartResolutions.add("300");
		lineChartResolutions.add("400");
		lineChartResolutions.add("500");
		lineChartResolutions.add("600");
		lineChartResolutions.add("700");
		lineChartResolutions.add("800");
		lineChartResolutions.add("900");
		lineChartResolutions.add("1000");

		return lineChartResolutions;

	}
	
	public ArrayList<String>  lineChartAggregateMethods(){

		ArrayList<String> lineChartAggregateMethods = new ArrayList<String>();

		lineChartAggregateMethods.add("Average");
		lineChartAggregateMethods.add("Average excluding zero values");
		lineChartAggregateMethods.add("Maximum value");
		lineChartAggregateMethods.add("Minimum Value");
		
		return lineChartAggregateMethods;

	}

	public String containsKeyIgnoreCase(Map<String, List<String>> map, String key){

		Iterator<Entry<String, List<String>>> iterator = map.entrySet().iterator();
		Map.Entry<String, List<String>> mapEntry = null;

		while(iterator.hasNext()){
			mapEntry = (Entry<String, List<String>>) iterator.next();
			if(key.equalsIgnoreCase(mapEntry.getKey())){
				return mapEntry.getKey();
			}
		}
		return "";
	}


}
