package ro.utcluj.larkc.mysqlmetrics.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.portlet.PortletPreferences;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.utcluj.larkc.mysqlmetrics.server.DBConnector;

import com.google.gwt.dev.jjs.ast.js.JsonArray;
import com.google.gwt.user.client.Window;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.PortletPreferencesFinder;
import com.mysql.jdbc.ResultSetMetaData;



/**
 * Servlet implementation class MetricServlet
 */
public class MetricServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public enum AggregateMethods {
		AVERAGE,
		AVERAGE_WITOUT_ZERO_VALUES,
		MINIMUM_VALUE,
		MAXIMUM_VALUE
	}
	
	
	//public AggregateMethods aggregateMethod;
	
	//public int jsonSize = 400;
	  
	//public int liveModePreselectedPeriod = 300000;
	
	//public boolean isLive = false;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public MetricServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//URL request example: http://localhost:8080/vis-mysql-metrics-chart-portlet/MetricServlet?chartcmd=getworkflowmetrics&idPlugin=2&idMetric=42&callback=myfunction
		String idWorkflow = "";
		String workflowName = "";
		String idPlugin = "";
		String pluginName = "";
		String idMetric = "";
		String metricName = "";		
		String chartCmd = "";
		String idQuery = "";
		String queryName = "";
		String mysqlQuery = "";
		String platformLevel = "";
		String startTime = "";
		String endTime = "";
		String callback = "";
		String idSystem = "";
		String systemName = "";
		String idPlatform = "";
		String platformName = "";
		String appName = "";
		String groupOrderLimitClauses = ""; 
		
		String liveMode = "";
	    String preselectedPeriod = "";
	    String lineChartResolution = "";
	    String lineChartAggregateMethod = "";
	    
	    String lineInterpolateMode = "";
			  
	    //default values
	    int jsonSize = 400;
	    AggregateMethods aggregateMethod = AggregateMethods.AVERAGE;
		
		int liveModePreselectedPeriod = 300000;
		boolean isLive = false;
		boolean isInterpolate = false;
		//get url parameters
		Map<String, List<String>> parameters = request.getParameterMap();
		String mapKey = "";
				
		if (!(mapKey = containsKeyIgnoreCase(parameters,"chartcmd")).isEmpty()){
			
			chartCmd = request.getParameter(mapKey).trim();
		}		
		if (!(mapKey = containsKeyIgnoreCase(parameters,"mysqlQuery")).isEmpty()){

			mysqlQuery = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"idWorkflow")).isEmpty()){

			idWorkflow = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"workflowName")).isEmpty()){

			workflowName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"idPlugin")).isEmpty()){

			idPlugin = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"pluginName")).isEmpty()){

			pluginName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"idMetric")).isEmpty()){

			idMetric = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"metricName")).isEmpty()){

			metricName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"idQuery")).isEmpty()){

			idQuery = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"queryName")).isEmpty()){

			queryName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"platformLevel")).isEmpty()){

			platformLevel = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"groupOrderLimitClauses")).isEmpty()){

			groupOrderLimitClauses = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"idSystem")).isEmpty()){

			idSystem = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"systemName")).isEmpty()){

			systemName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"idPlatform")).isEmpty()){

			idPlatform = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"platformName")).isEmpty()){

			platformName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"appName")).isEmpty()){

			appName = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"startTime")).isEmpty()){

			startTime = request.getParameter(mapKey).trim();
		}
		if (!(mapKey = containsKeyIgnoreCase(parameters,"endTime")).isEmpty()){

			endTime = request.getParameter(mapKey).trim();
		}
		if (parameters.containsKey("callback")){
			callback = request.getParameter("callback").trim();
		}			
		if(!(mapKey = containsKeyIgnoreCase(parameters, "liveMode")).isEmpty()){
			liveMode = request.getParameter(mapKey).trim();			
		}					
		if(!(mapKey = containsKeyIgnoreCase(parameters, "preselectedPeriod")).isEmpty()){
			preselectedPeriod = request.getParameter(mapKey).trim();
		}		
		if(!(mapKey = containsKeyIgnoreCase(parameters, "lineChartResolution")).isEmpty()){
			lineChartResolution = request.getParameter(mapKey).trim();
		}	
		if(!(mapKey = containsKeyIgnoreCase(parameters, "lineChartAggregateMethod")).isEmpty()){
			lineChartAggregateMethod = request.getParameter(mapKey).trim();
		}
		if(!(mapKey = containsKeyIgnoreCase(parameters, "interpolateMode")).isEmpty()){
			lineInterpolateMode = request.getParameter(mapKey).trim();
		}
		
		//set the result resolution according to the request parameter. Default is 400
		try {
			jsonSize = !lineChartResolution.isEmpty() ? Integer.parseInt(lineChartResolution) : 400;
		}catch (NumberFormatException e) {
			System.out.println("Wrong number format for Chart Resolution (jsonSize)"); 
		}
		
	
		//set method for data aggregation for the resulted json. Default is AVERAGE
		aggregateMethod = getAggregateMethod(lineChartAggregateMethod);
		
		//check if the portlet is in Live Mode
		isLive = (!liveMode.isEmpty()) && (liveMode.equalsIgnoreCase("isLive"));
		
		isInterpolate = (!lineInterpolateMode.isEmpty()) && (lineInterpolateMode.equalsIgnoreCase("interpolateMode"));
		
		//set preselected period of time form the live mode
		try {
			liveModePreselectedPeriod = !preselectedPeriod.isEmpty() ? Integer.parseInt(preselectedPeriod) : 300000;
		}catch (NumberFormatException e) {
			System.out.println("Wrong number format for Chart Resolution (jsonSize)"); 
		}
		
		//print out the JSON object
		PrintWriter out = response.getWriter(); 
		
		
		//get Start Time and End Time for the live or report modes		
		if(isLive){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//get end time for Live Mode
				endTime = getCurrentDateTime();
				
				//get start time for Live Mode
				long endTimestamp = df.parse(endTime).getTime();
				long startTimestamp = endTimestamp - liveModePreselectedPeriod;
				startTime = df.format(new Date(startTimestamp));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			//get start time for report mode
			startTime =  getStartTime(); 
			
			//get end time for report mode
			endTime = getEndTime();
		}
		
		
		//based on the requested command an output is generated
		if((!mysqlQuery.isEmpty()) && (mysqlQuery != null) && (mysqlQuery != "null")){
			out.print(callback + "(" + getMysqlResult(mysqlQuery,groupOrderLimitClauses, isLive) + ")");
		}
		else if(
				(chartCmd.compareToIgnoreCase("getsystemmetrics") == 0) ||
				(platformLevel.compareToIgnoreCase("system") == 0)
				){
			
			out.print(callback + "(" + getSystemMetrics(idSystem,systemName,idMetric,metricName,startTime,endTime,jsonSize,aggregateMethod,isLive,isInterpolate) + ")");
		}
		else if(
				(chartCmd.compareToIgnoreCase("getplatformmetrics") == 0) ||
				(platformLevel.compareToIgnoreCase("platform") == 0)
				){
			
			out.print(callback + "(" + getPlatformMetrics(idPlatform,platformName,appName,
					  									  idWorkflow, workflowName, 
					  									  idPlugin, pluginName, 
					  									  idQuery, queryName,
					  									  idMetric,metricName,
					  									  startTime,endTime,
					  									  groupOrderLimitClauses,
					  									  jsonSize, aggregateMethod,isLive,isInterpolate) + ")");
		}
		else if(
				(chartCmd.compareToIgnoreCase("getpluginmetrics") == 0) ||
				(platformLevel.compareToIgnoreCase("plugins") == 0)
				){
			
			out.print(callback + "(" + getPluginMetrics(idPlatform,platformName,appName,
													  	idWorkflow, workflowName, 
														idPlugin, pluginName, 
														idQuery, queryName,
														idMetric,metricName,
														startTime,endTime,
														groupOrderLimitClauses,
					  									jsonSize, aggregateMethod,isLive,isInterpolate) + ")");
		}
		else if(
				(chartCmd.compareToIgnoreCase("getworkflowmetrics") == 0) ||
				(platformLevel.compareToIgnoreCase("workflows") == 0)
				){
			
			out.print(callback + "(" + getWorkflowMetrics(idPlatform,platformName,appName,
													  	  idWorkflow, workflowName, 
														  idPlugin, pluginName, 
														  idQuery, queryName,
														  idMetric,metricName,
														  startTime,endTime,
														  groupOrderLimitClauses,
					  									  jsonSize, aggregateMethod,isLive,isInterpolate) + ")");
		}
		else if(
				(chartCmd.compareToIgnoreCase("getquerymetrics") == 0) ||
				(platformLevel.compareToIgnoreCase("queries") == 0)
				){
			
			out.print(callback + "(" + getQueryMetrics(idPlatform,platformName,appName,
				  	  idWorkflow, workflowName, 
					  idPlugin, pluginName, 
					  idQuery, queryName,
					  idMetric,metricName,
					  startTime,endTime,
					  groupOrderLimitClauses,
					  jsonSize, aggregateMethod,isLive,isInterpolate) + ")");
		}
		else {
			out.print(callback + "([])");
		} 
		
		PortletPreferencesFinder prefs = new PortletPreferencesFinder() {
			
			@Override
			public List<com.liferay.portal.model.PortletPreferences> findByPortletId(
					String arg0) throws SystemException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
	
		//prefs.findByPortletId("");
		
	}
	
	
	/*
	 * Get System Metrics
	 */
	private String getSystemMetrics(String idSystem, String systemName, String idMetric, String metricName, String startTime, String endTime, int jsonSize, AggregateMethods aggregateMethod, boolean isLive, boolean isInterpolate) {
	
		
		/*
		 * Construct the MySQL query
		 */
		String query = "select *, metrics.MetricName as Name FROM systems, metrics, systems_metrics " + 
					   "where systems.idSystem=systems_metrics.idSystem " +
					   "and metrics.idMetric=systems_metrics.idMetric ";
		
		
		if(!idMetric.isEmpty()){
			idMetric = idMetric.trim();
			query += "and metrics.idMetric =\""+idMetric+"\" ";
		}
		else if(!metricName.isEmpty()){
			metricName = metricName.trim();
			query += "and metrics.MetricName like '" + metricName + "' ";
		}
		
		if(!idSystem.isEmpty()){
			idSystem = idSystem.trim();
			query += "and systems.idSystem =\""+idSystem+"\" ";
		}
		else if(!systemName.isEmpty()){
			systemName = systemName.trim();
			query += "and systems.systemName like '" + systemName + "' ";
		}
		
		
		
		//To DO: put startTime and endTime
		if( (!startTime.isEmpty()) && (!endTime.isEmpty()) ){
			query += " and Timestamp >= '" + startTime + "' and Timestamp <= '" + endTime + "' ";
		}
		
		query += " ORDER BY Timestamp ASC";
		
		return getJsonFromResultSet(query, startTime, endTime, jsonSize, aggregateMethod, isLive, isInterpolate);
	}//get System Metrics
	
	
	/*
	 * Get Platform Metrics
	 */
	private String getPlatformMetrics(String idPlatform, String platformName, String appName, 
									  String idWorkflow, String workflowName, 
									  String idPlugin, String pluginName, 
									  String idQuery, String queryName,
									  String idMetric, String metricName, 
									  String startTime, String endTime,
									  String groupOrderLimitClauses, 
									  int jsonSize, AggregateMethods aggregateMethod, boolean isLive, boolean isInterpolate) {
		
		
		String query_select = 	  "select *, metrics.MetricName as Name ";
		String query_from =       "FROM platforms, metrics, platforms_metrics ";
		String query_conditions = "where platforms.idPlatform=platforms_metrics.idPlatform " +
		   						  "and metrics.idMetric=platforms_metrics.idMetric ";
		
		String query_plugin = "";
		String query_queries = "";
		String query_workflows = "";
		
		boolean isWorflowSelectNeeded = false;
		/*
		 * Construct the MySQL query
		 */	
		if(!idMetric.isEmpty()){
			idMetric = idMetric.trim();
			query_conditions += "and metrics.idMetric =\""+idMetric+"\" ";
		}else if(!metricName.isEmpty()){
			metricName = metricName.trim();
			query_conditions += "and metrics.MetricName like '" + metricName + "' ";
		}
		
		if(!idPlatform.isEmpty()){
			idPlatform = idPlatform.trim();
			query_conditions += "and platforms.idPlatform =\""+idPlatform+"\" ";
		}else if(!platformName.isEmpty()){
			platformName = platformName.trim();
			query_conditions += "and platforms.platformName like '" + platformName + "' ";
		}
		
		if(!appName.isEmpty()){
			appName = appName.trim();
			query_conditions += "and platforms.ApplicationName like '"+appName+"' ";
		}
		
		
		
		if(!idPlugin.isEmpty()){
			idPlugin = idPlugin.trim();
			query_plugin = "and workflows.idWorkflow in " + 	
							"(select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
								"where plugins.idPlugin = workflows_plugins.idPlugin " +
								"and workflows.idWorkflow = workflows_plugins.idWorkflow " +
								"and plugins.idPlugin =\""+idPlugin+"\" )";
			
			isWorflowSelectNeeded = true;
		}else if(!pluginName.isEmpty()){
			pluginName = pluginName.trim();
			query_plugin = "and workflows.idWorkflow in " + 	
			"(select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
				"where plugins.idPlugin = workflows_plugins.idPlugin " +
				"and workflows.idWorkflow = workflows_plugins.idWorkflow " +
				"and plugins.PluginName= \""+pluginName+"\" ) ";								
			
			isWorflowSelectNeeded = true;
		}
		
		if(!idQuery.isEmpty()){
			idQuery = idQuery.trim();
			query_queries = "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow  from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					 "and queries.idQuery =\""+idQuery+"\" ) ";
			isWorflowSelectNeeded = true;
		}
		else if(!queryName.isEmpty()){
			queryName = queryName.trim();
			query_queries = "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow  from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					"and queries.QueryContent like '"+queryName+"' ";	
			
			isWorflowSelectNeeded = true;					
		}
		
		
		if(!idWorkflow.isEmpty()){
			idWorkflow = idWorkflow.trim();
			query_workflows = "and workflows.idWorkflow =\""+idWorkflow+"\" ";		
			isWorflowSelectNeeded = true;
		}
		else if(!workflowName.isEmpty()){
			workflowName = workflowName.trim();
			query_workflows = "and workflows.WorkflowDescription = \""+workflowName+"\" ";
			isWorflowSelectNeeded = true;	
		}
		
		if(isWorflowSelectNeeded == true){
			query_conditions += "and platforms.idPlatform in " +
								"(  select distinct platforms.idPlatform from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms.idPlatform = platforms_workflows.idPlatform " +
									query_workflows +
									query_plugin +
									query_queries +
									") ";

		}
		//put startTime and endTime
		if( (!startTime.isEmpty()) && (!endTime.isEmpty()) ){
			query_conditions += " and Timestamp >= '" + startTime + "' and Timestamp <= '" + endTime + "' ";
		}
		
		String grouporderlimitclauses = groupOrderLimitClauses.isEmpty() ? " ORDER BY Timestamp ASC" : groupOrderLimitClauses;
		
		String query = query_select + query_from + query_conditions + grouporderlimitclauses;
		
		/*
		 * execute the MySQL query and generate the JSON Object
		 */
		return getJsonFromResultSet(query, startTime, endTime, jsonSize, aggregateMethod, isLive, isInterpolate);

	}//get Platform Metrics
	
	
	private String getPluginMetrics(String idPlatform, String platformName, String appName, 
									String idWorkflow, String workflowName, 
									String idPlugin, String pluginName, 
									String idQuery, String queryName,
									String idMetric, String metricName, 
									String startTime, String endTime,
									String groupOrderLimitClauses,
									int jsonSize, AggregateMethods aggregateMethod, boolean isLive, boolean isInterpolate) {
		
		
		/*
		 * Construct the MySQL query
		 */
//		String query = "select *, metrics.MetricName as Name FROM plugins, plugins_metrics, workflows_plugins, metrics, workflows " +
//		"where workflows_plugins.idPlugin =plugins_metrics.idPlugin " +
//		"and workflows_plugins.idPlugin=plugins.idPlugin " +
//		"and plugins_metrics.idMetric = metrics.idMetric " +
//		"and workflows.idWorkflow=workflows_plugins.idWorkflow ";
		
		String query_select = 	  "select *, metrics.MetricName as Name ";
		String query_from =       "FROM metrics, plugins_metrics, plugins ";
		String query_conditions = "where plugins.idPlugin = plugins_metrics.idPlugin " +
								  "and metrics.idMetric=plugins_metrics.idMetric ";
		   						  
		
		String query_platforms = "";
		String query_queries = "";
		String query_workflows = "";
		
		boolean isPlatformSelectNeeded = false;
		
		if(!idPlugin.isEmpty()){
			query_conditions += "and plugins.idPlugin =\""+idPlugin+"\" ";
		}
		else if(!pluginName.isEmpty()){
			pluginName = pluginName.trim();
			query_conditions += "and plugins.PluginName= \""+pluginName+"\" ";
		}
		
		if(!idMetric.isEmpty()){
			query_conditions += "and metrics.idMetric =\""+idMetric+"\" ";
		}
		else if(!metricName.isEmpty()){
			metricName = metricName.trim();
			query_conditions += "and metrics.MetricName like '" + metricName + "' ";
		}
		
		if(!idWorkflow.isEmpty()){
			idWorkflow = idWorkflow.trim();
			query_workflows += "and workflows_plugins.idWorkflow =\""+idWorkflow+"\" ";
		}else if(!workflowName.isEmpty()){
			workflowName = workflowName.trim();
			query_workflows += "and workflows.WorkflowDescription = \""+workflowName+"\" ";
		}
		
		if(!idPlatform.isEmpty()){
			idPlatform = idPlatform.trim();
			query_platforms += "and platforms.idPlatform =\""+idPlatform+"\" ";
			isPlatformSelectNeeded = true;
		}else if(!platformName.isEmpty()){
			platformName = platformName.trim();
			query_platforms += "and platforms.platformName like '" + platformName + "' ";
			isPlatformSelectNeeded = true;
		}
		
		if(!appName.isEmpty()){
			appName = appName.trim();
			query_platforms += "and platforms.ApplicationName like '"+appName+"' ";
			isPlatformSelectNeeded = true;
		}
		
		if(isPlatformSelectNeeded == true){
			query_platforms = "	and workflows.idWorkflow in " +
			"(" +
			"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
			"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
			"and platforms.idPlatform = platforms_workflows.idPlatform " +
			query_platforms +
			")";
		}
		
		if(!idQuery.isEmpty()){
			idQuery = idQuery.trim();
			query_queries = "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow  from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					 "and queries.idQuery =\""+idQuery+"\" ) ";	
		}
		else if(!queryName.isEmpty()){
			queryName = queryName.trim();
			query_queries = "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow  from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					"and queries.QueryContent like '"+queryName+"' ) ";				
		}
		
	
		query_conditions += "and plugins.idPlugin in " +
						"(" +
							"select distinct plugins.idPlugin from workflows, plugins, workflows_plugins " +
							"where workflows.idWorkflow = workflows_plugins.idWorkflow " +
							"and plugins.idPlugin = workflows_plugins.idPlugin " +
							query_workflows +
							query_platforms +
							query_queries +
						")";
							
		//To DO: put startTime and endTime
		if( (!startTime.isEmpty()) && (!endTime.isEmpty()) ){
			query_conditions += " and Timestamp >= '" + startTime + "' and Timestamp <= '" + endTime + "' ";
		}
		
		String grouporderlimitclauses = groupOrderLimitClauses.isEmpty() ? " ORDER BY Timestamp ASC " : groupOrderLimitClauses;
		
		String query = query_select + query_from + query_conditions + grouporderlimitclauses;
		
		/*
		 * execute the MySQL query and generate the JSON Object
		 */
		return getJsonFromResultSet(query, startTime, endTime, jsonSize, aggregateMethod, isLive, isInterpolate);
		
		
	}//getPluginMetrics

	private String getWorkflowMetrics(String idPlatform, String platformName, String appName, 
									  String idWorkflow, String workflowName, 
									  String idPlugin, String pluginName, 
									  String idQuery, String queryName,
									  String idMetric, String metricName, 
									  String startTime, String endTime,
									  String groupOrderLimitClauses,
									  int jsonSize, AggregateMethods aggregateMethod, boolean isLive, boolean isInterpolate) {
		
		String query_select = 	  "select *, metrics.MetricName as Name ";
		String query_from =       "FROM workflows_metrics, metrics, workflows ";
		String query_conditions = "where workflows_metrics.idMetric = metrics.idMetric " +
								  "and workflows_metrics.idWorkflow=workflows.idWorkflow ";		   						  
		
		String query_platforms = "";
		String query_queries = "";
		String query_plugins = "";
		
		boolean isPlatformSelected = false;
		
		if(!idWorkflow.isEmpty()){
			idWorkflow = idWorkflow.trim();
			query_conditions += "and workflows_metrics.idWorkflow =\""+idWorkflow+"\" ";
		}
		else if(!workflowName.isEmpty()){
			workflowName = workflowName.trim();
			query_conditions += "and workflows.WorkflowDescription = \""+workflowName+"\" ";
		}
		
		if(!idMetric.isEmpty()){
			idMetric = idMetric.trim();
			query_conditions += "and metrics.idMetric =\""+idMetric+"\" ";
		}
		else if(!metricName.isEmpty()){
			metricName = metricName.trim();
			query_conditions += "and metrics.MetricName like '" + metricName + "' ";
		}
		
		if(!idPlatform.isEmpty()){
			idPlatform = idPlatform.trim();
			query_platforms += "and platforms.idPlatform =\""+idPlatform+"\" ";
			isPlatformSelected = true;
			
		}else if(!platformName.isEmpty()){
			platformName = platformName.trim();
			query_platforms += "and platforms.platformName like '" + platformName + "' ";
			isPlatformSelected = true;
			
		}
		
		if(!appName.isEmpty()){
			appName = appName.trim();
			query_platforms += "and platforms.ApplicationName like '"+appName+"' ";
			isPlatformSelected = true;
		}
		
		if(!idPlugin.isEmpty()){
			idPlugin = idPlugin.trim();
			query_plugins = "and workflows.idWorkflow in " + 	
							"(select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
								"where plugins.idPlugin = workflows_plugins.idPlugin " +
								"and workflows.idWorkflow = workflows_plugins.idWorkflow " +
								"and plugins.idPlugin =\""+idPlugin+"\" )";
			isPlatformSelected = true;			
		}else if(!pluginName.isEmpty()){
			pluginName = pluginName.trim();
			query_plugins = "and workflows.idWorkflow in " + 	
			"(select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
				"where plugins.idPlugin = workflows_plugins.idPlugin " +
				"and workflows.idWorkflow = workflows_plugins.idWorkflow " +
				"and plugins.PluginName= \""+pluginName+"\" ) ";
			isPlatformSelected = true;
		}
		
		if(!idQuery.isEmpty()){
			idQuery = idQuery.trim();
			query_queries = "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow  from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					 "and queries.idQuery =\""+idQuery+"\" ) ";	
			isPlatformSelected = true;
		}
		else if(!queryName.isEmpty()){
			queryName = queryName.trim();
			query_queries = "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow  from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					"and queries.QueryContent like '"+queryName+"' ) ";		
			isPlatformSelected = true;
		}		
		
		if(isPlatformSelected){		
			query_conditions += "and workflows.idWorkflow in " +
			"(select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
			"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
			"and platforms.idPlatform = platforms_workflows.idPlatform " +
			query_platforms +
			query_plugins +
			query_queries +
			")";
		}
		 
		
		//Put start time and end time
		if( (!startTime.isEmpty()) && (!endTime.isEmpty()) ){
			query_conditions += " and Timestamp >= '" + startTime + "' and Timestamp <= '" + endTime + "' ";
		}
		
		String grouporderlimitclauses = groupOrderLimitClauses.isEmpty() ? " ORDER BY Timestamp ASC " : groupOrderLimitClauses;
		
		String query = query_select + query_from + query_conditions + grouporderlimitclauses;
		
		/*
		 * execute the MySQL query and generate the JSON Object
		 */
		return getJsonFromResultSet(query, startTime, endTime, jsonSize, aggregateMethod, isLive, isInterpolate);	  

		
	}//getWorkflowMetrics

	private String getQueryMetrics(String idPlatform, String platformName, String appName, 
								   String idWorkflow, String workflowName, 
								   String idPlugin, String pluginName, 
								   String idQuery, String queryName,
								   String idMetric, String metricName, 
								   String startTime, String endTime,
								   String groupOrderLimitClauses,
								   int jsonSize, AggregateMethods aggregateMethod, boolean isLive, boolean isInterpolate) {
		
		
		String query_select = 	  "select *, metrics.MetricName as Name ";
		String query_from =       "FROM queries_metrics, metrics, queries ";
		String query_conditions = "where queries_metrics.idMetric = metrics.idMetric " +
								  "and queries_metrics.idQuery= queries.idQuery ";		   						  
		
		String query_platforms = "";
		String query_workflows = "";
		String query_plugins = "";
		 
		boolean isWorkflowSelected = false;
		
		if(!idQuery.isEmpty()){
			idQuery = idQuery.trim();
			query_conditions += "and queries.idQuery =\""+idQuery+"\" ";
		}
		else if(!queryName.isEmpty()){
			queryName = queryName.trim();
			query_conditions += "and queries.QueryContent like '"+queryName+"' ";
		}
		
		if(!idMetric.isEmpty()){
			query_conditions += "and metrics.idMetric =\""+idMetric+"\" ";
		}
		else if(!metricName.isEmpty()){
			metricName = metricName.trim();
			query_conditions += "and metrics.MetricName like '" + metricName + "' ";
		}
	
		
		if(!idWorkflow.isEmpty()){
			idWorkflow = idWorkflow.trim();
			query_workflows += "and workflows.idWorkflow =\""+idWorkflow+"\" ";
			isWorkflowSelected = true;
		}
		else if(!workflowName.isEmpty()){
			workflowName = workflowName.trim();
			query_workflows += "and workflows.WorkflowDescription = \""+workflowName+"\" ";
			isWorkflowSelected = true;
		}
		if(!idPlatform.isEmpty()){
			idPlatform = idPlatform.trim();
			
			query_platforms += "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
				"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
				"and platforms.idPlatform = platforms_workflows.idPlatform " +
				"and platforms.idPlatform =\""+idPlatform+"\" ) ";
			isWorkflowSelected = true;
			
		}else if(!platformName.isEmpty()){
			platformName = platformName.trim();
			query_platforms += "and workflows.idWorkflow in " + 
			"(select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
				"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
				"and platforms.idPlatform = platforms_workflows.idPlatform " +
				"and platforms.platformName like '" + platformName + "' ) ";
			isWorkflowSelected = true;
		}
		
		if(!appName.isEmpty()){
			appName = appName.trim();
			if(isWorkflowSelected == true){
				query_platforms += "and platforms.ApplicationName like '"+appName+"' ";
			}else{
				query_platforms += "and workflows.idWorkflow in " + 
				"(select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
					"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
					"and platforms.idPlatform = platforms_workflows.idPlatform " +
					"and platforms.ApplicationName like '"+appName+"' ) ";
			}
			isWorkflowSelected = true;
		}
		
		if(!idPlugin.isEmpty()){
			idPlugin = idPlugin.trim();
			query_plugins = "and workflows.idWorkflow in " + 	
							"(select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
								"where plugins.idPlugin = workflows_plugins.idPlugin " +
								"and workflows.idWorkflow = workflows_plugins.idWorkflow " +
								"and plugins.idPlugin =\""+idPlugin+"\" )";
			isWorkflowSelected = true;
		}else if(!pluginName.isEmpty()){
			pluginName = pluginName.trim();
			query_plugins = "and workflows.idWorkflow in " + 	
			"(select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
				"where plugins.idPlugin = workflows_plugins.idPlugin " +
				"and workflows.idWorkflow = workflows_plugins.idWorkflow " +
				"and plugins.PluginName= \""+pluginName+"\" ) ";	
			isWorkflowSelected = true;
		}
		
		if(isWorkflowSelected){
			query_conditions += "and queries.idQuery in " +
					"(" +
					"select distinct queries.idQuery from workflows, queries, queries_workflows " +
					"where workflows.idWorkflow = queries_workflows.idWorkflow " +
					"and queries.idQuery = queries_workflows.idQuery " +
					query_workflows +
					query_platforms +
					query_plugins +
					") ";
		}
		//Put start time and end time
		if( (!startTime.isEmpty()) && (!endTime.isEmpty()) ){
			query_conditions += " and Timestamp >= '" + startTime + "' and Timestamp <= '" + endTime + "' ";
		}
		
		String grouporderlimitclauses = groupOrderLimitClauses.isEmpty() ? " ORDER BY Timestamp ASC " : groupOrderLimitClauses;
		
		String query = query_select + query_from + query_conditions + grouporderlimitclauses;
		
		/*
		 * execute the MySQL query and generate the JSON Object
		 */
		return getJsonFromResultSet(query, startTime, endTime, jsonSize, aggregateMethod, isLive, isInterpolate);	  

		
	}//getQueryMetrics
	
	
	/* 
	 * get results for any MySQL query 
	 */
	private String getMysqlResult(String mysqlQuery, String groupOrderLimitClauses, boolean isLive) {
		ResultSet rs = null;
		Statement stmt = null;
		DBConnector dbConnector = null;
		
		JSONArray jsonArray = new JSONArray();
		
		if(mysqlQuery.isEmpty() || mysqlQuery.equalsIgnoreCase("null")){
			return null;
		}
		
		if(!mysqlQuery.isEmpty() && !mysqlQuery.equalsIgnoreCase("null")){
			mysqlQuery += " " + groupOrderLimitClauses;
		}
		
		/* retrieve result from cache */
		if(!isLive){
			String cacheResults = readCache(mysqlQuery);

			if(!cacheResults.isEmpty()){
				return cacheResults;
			}
		}
		
		try {
			
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();
			rs = stmt.executeQuery(mysqlQuery);
		
			ResultSetMetaData rsMetaData = (ResultSetMetaData) rs.getMetaData();
	
			while (rs.next()) 
			{
				JSONObject row = new JSONObject();
				int nrOfColumns = rsMetaData.getColumnCount();
				String columnName = "";
	
				for(int i = 1; i <= nrOfColumns; i++){
	
					try {
						columnName = rsMetaData.getColumnLabel(i);
						row.put(columnName, rs.getString(i));			
					} catch (JSONException e) {						
						e.printStackTrace();
					}
				}
				//System.out.println("Name " + Name);
				jsonArray.put(row);
			}
			
			/* write data in Cache */
			String jsonString = jsonArray.toString();
			if(!isLive){
				writeCache(mysqlQuery, jsonString);
			}
			return jsonString;
	
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
		
	}//getMetrics
	
	/*
	 * Get the the timestamp for MySQL query
	 */
	public String getStartTime(){
		
		ResultSet rs = null;
		Statement stmt = null;
		DBConnector dbConnector = null;
		
		String sqlStartDateQuery = "select SettingValue from larkc.vis_settings where SettingName = 'StartDate';";
		String startDate = "";
		
		
		try {
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();
			
			//obtain the start date
			rs = stmt.executeQuery(sqlStartDateQuery);
			while (rs.next()) {
				startDate = rs.getString(1);
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
		
		return startDate;
	}//getStartTime
	
	/*
	 * Get the end timestamp for MySQL query
	 */
	public String getEndTime(){
		
		ResultSet rs = null;
		Statement stmt = null;
		DBConnector dbConnector = null;
		
		String sqlEndDateQuery = "select SettingValue from larkc.vis_settings where SettingName = 'EndDate';";
		String endDate = "";
		
		try {
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();
			
			//obtain the start date
			rs = stmt.executeQuery(sqlEndDateQuery);
			while (rs.next()) {
				endDate = rs.getString(1);
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
			
		}
		
		return endDate;
	}//getEndTime
	
	
	public String getCurrentDateTime(){

		ResultSet rs = null;
		Statement stmt = null;
		DBConnector dbConnector = null;

		String sqlEndDateQuery = "select now() as currentDateTime";
		String currentDateTime = "";

		try {
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();

			//obtain the start date
			rs = stmt.executeQuery(sqlEndDateQuery);
			while (rs.next()) {
				currentDateTime = rs.getString(1);
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

		}

		return currentDateTime;
	}//getEndTime


	public String getJsonFromResultSet(String query, String startTime, String endTime, int jsonSize, AggregateMethods aggregateMethod, boolean isLive, boolean isInterpolate){
		
		ResultSet rs = null;
		Statement stmt = null;
		JSONArray jsonArray = new JSONArray();
		Date startDate = null;
		Date endDate = null;
		DBConnector dbConnector = null;
		
		
		boolean isNoValue = true;
		boolean isFirstValue = true;
		
		//number of Mysql ResultSet Columns
		int nrOfColumns = 0;
		String columnName = "";
		
		
		/* validate the startTime and endTime */
		if((startTime==null)||(startTime.isEmpty())||(endTime==null)||(endTime.isEmpty())){
			return null;
		}
		
		/* retrieve result from cache */
		if(!isLive){
			String cacheResults = readCache(query);

			if(!cacheResults.isEmpty()){
				return cacheResults;
			}
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		DecimalFormat decimalFormat =  new DecimalFormat(".####");		
		 
		try {
			startDate = df.parse(startTime); 
			endDate = df.parse(endTime);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		long rsDeltaTime = endDate.getTime() - startDate.getTime();
		long jsonBinSize = rsDeltaTime/jsonSize;
		long startDateTimestamp = startDate.getTime();
		long endDateTimestamp = endDate.getTime();
		
		try {

			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();
			rs = stmt.executeQuery(query);

			ResultSetMetaData rsMetaData = (ResultSetMetaData) rs.getMetaData();
			
			//test if the Timestamp Column is  included in the ResultSet
			boolean isTimestamp = false;
			int rsTimestampColumn = -1;
			int rsValueColumn = -1;
			for(int i = 1; i <= rsMetaData.getColumnCount(); i++){
				if(rsMetaData.getColumnLabel(i).equalsIgnoreCase("Timestamp")){
					isTimestamp = true;
					rsTimestampColumn = i;
				}
				else if (rsMetaData.getColumnLabel(i).equalsIgnoreCase("Value")){
					rsValueColumn = i;
				}
			}
			if(!isTimestamp){
				return null;
			}else if(isLive){
				
			}
			
			int jsonBinIndex = 1;
			long valueCount = 0;
			double value = 0.0;
			
			long rsTime = 0;
			long binTime = 0;
			
			isNoValue = true;
			
			
			while (rs.next()) 
			{
				
				nrOfColumns = rsMetaData.getColumnCount();
				
				rsTime = rs.getTimestamp(rsTimestampColumn).getTime();
				binTime = startDateTimestamp+(jsonBinIndex*jsonBinSize);
				
				if(rs.getTimestamp(rsTimestampColumn).getTime() < (startDateTimestamp+(jsonBinIndex*jsonBinSize))){
					if(aggregateMethod == AggregateMethods.AVERAGE_WITOUT_ZERO_VALUES){
						//aggregation without zero values
						value = (rs.getDouble(rsValueColumn) > 0) ? value + rs.getDouble(rsValueColumn) : value;
						valueCount++;
						isNoValue = false;
					}else if(aggregateMethod == AggregateMethods.MAXIMUM_VALUE){
						//aggregation using the maximum value
						if(isFirstValue){
							value = rs.getDouble(rsValueColumn);
							isFirstValue = false;
						}else{
							value = (rs.getDouble(rsValueColumn) > value) ? rs.getDouble(rsValueColumn) : value;
						}
						isNoValue = false;
					}else if(aggregateMethod == AggregateMethods.MINIMUM_VALUE){
						//aggregation using the minimum value
						if(isFirstValue){
							value = rs.getDouble(rsValueColumn);
							isFirstValue = false;
						}else{
							value = (rs.getDouble(rsValueColumn) < value) ? rs.getDouble(rsValueColumn) : value;
						}
						isNoValue = false;
					}else{
						//aggregation using the average value
						value += rs.getDouble(rsValueColumn);
						valueCount++;
						isNoValue = false;
					}
				}else{
					
					if((aggregateMethod == AggregateMethods.AVERAGE) || 
						(aggregateMethod == AggregateMethods.AVERAGE_WITOUT_ZERO_VALUES)){
							//averaging the value
							value = valueCount > 0 ? value/valueCount: 0; 
						}
					
					//for the Interpolate mode we update JSON only there is a value set
					if( (isInterpolate && !isNoValue) || (!isInterpolate)){
						columnName = "";

						JSONObject row = new JSONObject();
						for(int i = 1; i <= nrOfColumns; i++){

							try {
								columnName = rsMetaData.getColumnLabel(i);
								if(i == rsValueColumn){
									row.put(columnName, decimalFormat.format(value));
								}
								else if(i == rsTimestampColumn){
									row.put(columnName,df.format(new Date(startDateTimestamp+(jsonBinIndex*jsonBinSize))));
								}
								else{
									row.put(columnName, rs.getString(i));
								}
							} catch (JSONException e) {						
								e.printStackTrace();
							}

						}

						//store the current row
						jsonArray.put(row);
					}
					
					//get the next value
					value = rs.getDouble(rsValueColumn);
					valueCount = 1;
					jsonBinIndex++;
					
					
					
				}
				
				// jsonBinIndex is less than current time
				rsTime = rs.getTimestamp(rsTimestampColumn).getTime();
				binTime = startDateTimestamp+(jsonBinIndex*jsonBinSize);
				
				boolean shouldStoreInterpolateValue = false;
				//If no Interpolate is needed, then beans before the timestamp of the current resource will be filled with 0 values
				while((startDateTimestamp+(jsonBinIndex*jsonBinSize)) < rs.getTimestamp(rsTimestampColumn).getTime()){
					
					//for the Interpolate mode we update JSON only there is a value set
					shouldStoreInterpolateValue = true;
					if(!isInterpolate){
						columnName = "";
						JSONObject row = new JSONObject();
						for(int i = 1; i <= nrOfColumns; i++){

							try {
								columnName = rsMetaData.getColumnLabel(i);
								if(i == rsValueColumn){
									row.put(columnName, "0");
								}
								else if(i == rsTimestampColumn){
									row.put(columnName,df.format(new Date(startDateTimestamp+(jsonBinIndex*jsonBinSize))));
								}
								else{
									row.put(columnName, rs.getString(i));
								}
							} catch (JSONException e) {						
								e.printStackTrace();
							}

						}

						jsonArray.put(row);
					}

					jsonBinIndex++;
				}//while
				
				//for interpolate mode the current resourche should be stored into JSON, before to get to the next MySQL row
				if(isInterpolate && shouldStoreInterpolateValue){

					columnName = "";

					JSONObject row = new JSONObject();
					for(int i = 1; i <= nrOfColumns; i++){

						try {
							columnName = rsMetaData.getColumnLabel(i);
							if(i == rsValueColumn){
								row.put(columnName, decimalFormat.format(value));
							}
							else if(i == rsTimestampColumn){
								row.put(columnName,df.format(new Date(startDateTimestamp+(jsonBinIndex*jsonBinSize))));
							}
							else{
								row.put(columnName, rs.getString(i));
							}
						} catch (JSONException e) {						
							e.printStackTrace();
						}

					}

					//store the current row
					jsonArray.put(row);

					value = 0.0;
					valueCount = 0;
					isNoValue = true;
				}//if-else - for interpolate mode
					

				
				
			}//while
			
			
			/*
			 * Processing the last populated MySQL points
			 */
			if(valueCount >1){
				if((aggregateMethod == AggregateMethods.AVERAGE) || 
						(aggregateMethod == AggregateMethods.AVERAGE_WITOUT_ZERO_VALUES)){
					//averaging the value
					value = valueCount > 0 ? value/valueCount: 0; 
				} 
				JSONObject row = new JSONObject();
				for(int i = 1; i <= nrOfColumns; i++){

					try {
						columnName = rsMetaData.getColumnLabel(i);
						if(i == rsValueColumn){
							row.put(columnName, decimalFormat.format(value));
						}
						else if(i == rsTimestampColumn){
							row.put(columnName,df.format(new Date(startDateTimestamp+(jsonBinIndex*jsonBinSize))));
						}
						else{
							row.put(columnName, "none");
						}
					} catch (JSONException e) {						
						e.printStackTrace();
					}

				}
				jsonArray.put(row);

			}//if
			
			//fill with 0 last json bins
			while(jsonBinIndex < jsonSize){

				columnName = "";
				JSONObject row = new JSONObject();
				for(int i = 1; i <= nrOfColumns; i++){

					try {
						columnName = rsMetaData.getColumnLabel(i);
						
						if(i == rsValueColumn){
							row.put(columnName, "0");
						}
						else if(i == rsTimestampColumn){
							row.put(columnName,df.format(new Date(startDateTimestamp+(jsonBinIndex*jsonBinSize))));
						}
						else{
							row.put(columnName, "none");
						}
						
					} catch (JSONException e) {						
						e.printStackTrace();
					}

				}
				jsonArray.put(row);
				jsonBinIndex++;
			}			

		} catch (SQLException e) {
			System.out.println("Error: QUERY:" + query);
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
		
		String jsonString = jsonArray.toString();
		if(!isLive){
			writeCache(query, jsonString);
		}
		
		return jsonString;  

	}

	private void writeCache(String query, String jsonString) {
		Statement stmt = null;
		DBConnector dbConnector = null;
		query = query.replace("\"", "\\\"");
		jsonString = jsonString.replace("\\", "\\\\");
		jsonString = jsonString.replace("\"", "\\\"");
		String mySQLQuery = "INSERT INTO cache_queries (mysqlQueryContent, jsonResult) VALUES (\"" + query + "\", \"" + jsonString + "\");";
		
		
		try {
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();
			
			//obtain the start date
			stmt.executeUpdate(mySQLQuery);
			
		} catch (SQLException e) { 
			
			e.printStackTrace();
			
		} finally {
			
			try {
				stmt.close();
				dbConnector.closeConnection();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	private String readCache(String query) {
		
		
		ResultSet rs = null;
		Statement stmt = null;
		DBConnector dbConnector = null;
		query = query.replace("\"", "\\\"");
		String mySQLQuery = "SELECT jsonResult FROM cache_queries where mysqlQueryContent like \"" + query + "\"; ";
		String jsonResult = "";
		
		try {
			dbConnector = new DBConnector();
			stmt = dbConnector.conn.createStatement();
			
			//obtain the start date
			rs = stmt.executeQuery(mySQLQuery);
			while (rs.next()) {
				jsonResult = rs.getString(1);
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
			
		}
		
		return jsonResult;
		
	}

	/*
	 * Check if the Map contains key, ignoring the case, and return the corresponding original key
	 */
	@SuppressWarnings("unchecked")
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
	
	public AggregateMethods getAggregateMethod(String lineChartAggregateMethod){
		
		//return default aggregate methods
		if(lineChartAggregateMethod.isEmpty()){
			return AggregateMethods.AVERAGE;
		}
		
		if(lineChartAggregateMethod.equalsIgnoreCase("Average excluding zero values")){
			return AggregateMethods.AVERAGE_WITOUT_ZERO_VALUES;
		}else if(lineChartAggregateMethod.equalsIgnoreCase("Maximum value")){
			return AggregateMethods.MAXIMUM_VALUE;
		}else if(lineChartAggregateMethod.equalsIgnoreCase("Minimum Value")){
			return AggregateMethods.MINIMUM_VALUE;
		}else{
			return AggregateMethods.AVERAGE;
		}	
	}
		
}


