package ro.utcluj.larkc.metrics.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * SERVLET implementation class MetricDBServlet
 */
public class MetricDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetricDBServlet() {
        super();
    } 
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = "";
		String mysqlQuery = "";
		String tableName =""; 
		String metricName ="";
		String useMetrics ="";
		String limit ="";
		String useQuery="";
		// get parameters
		String idMetric ="";
		String idPlatform ="";
		String idWorkflow ="";
		String idPlugin = "";
		String idQuery = "";
		String idSystem = "";
		
		String metricNameGet = "";
		String platformName ="";
		String workflowName ="";
		String pluginName = "";
		String queryName = "";
		String systemName ="";
		String applicationName ="";

		Map<?, ?> parameters = request.getParameterMap();
				
		if (parameters.containsKey("callback")){
			callback = request.getParameter("callback");
		}
		
		//Get Mysql Query or set a default one
		if (parameters.containsKey("mysqlQuery")){
			mysqlQuery = request.getParameter("mysqlQuery");
			if(mysqlQuery == null) {
				mysqlQuery = "";
			}
		}else {
			mysqlQuery = "";
		}
		
		//Get table name or set a default one
		if (parameters.containsKey("tableName")) {
			tableName = request.getParameter("tableName");
			if(tableName == null) {
				tableName = "";
			}
		}
		
		//Get metric name or set a default one
		if (parameters.containsKey("metricName")) {
			metricName = request.getParameter("metricName");
			if(metricName == null) {
				metricName = "";
			}
		}
		
		//Get display value: metrics or context or set a default one
		if (parameters.containsKey("useMetrics")) {
			useMetrics = request.getParameter("useMetrics");
			if(useMetrics == null) {
				useMetrics = "";
			}
		}
		
		//Get result limit or set a default one
		if(parameters.containsKey("limit")) {
			limit = request.getParameter("limit");
			if((limit == null) || limit.equalsIgnoreCase("null")) {
				limit = "Max";
			}
		}
		
		//get the useQuery parameter
		if(parameters.containsKey("useQuery")) {
			useQuery = request.getParameter("useQuery");
			if(useQuery == null) {
				useQuery = "";
			}
		}
		/* GET parameters */
		if(parameters.containsKey("idMetric")) {
			idMetric = request.getParameter("idMetric");
			if(idMetric == null) {
				idMetric = "";
			}
		}
		
		if(parameters.containsKey("idPlatform")) {
			idPlatform = request.getParameter("idPlatform");
			if(idPlatform == null) {
				idPlatform = "";
			}
		}
		
		if(parameters.containsKey("idWorkflow")) {
			idWorkflow = request.getParameter("idWorkflow");
			if(idWorkflow == null) {
				idWorkflow = "";
			}
		}
		
		if(parameters.containsKey("idPlugin")) {
			idPlugin = request.getParameter("idPlugin");
			if(idPlugin == null) {
				idPlugin = "";
			}
		}
		
		if(parameters.containsKey("idQuery")) {
			idQuery = request.getParameter("idQuery");
			if(idQuery == null) {
				idQuery = "";
			}
		}
		
		if(parameters.containsKey("idSystem")) {
			idSystem = request.getParameter("idSystem");
			if(idSystem == null) {
				idSystem = "";
			}
		}
		
		if(parameters.containsKey("MetricName")) {
			metricNameGet = request.getParameter("MetricName");
			if(metricNameGet == null) {
				metricNameGet = "";
			}
		}
		
		if(parameters.containsKey("PlatformName")) {
			platformName = request.getParameter("PlatformName");
			if(platformName == null) {
				platformName = "";
			}
		}
		
		if(parameters.containsKey("WorkflowName")) {
			workflowName = request.getParameter("WorkflowName");
			if(workflowName == null) {
				workflowName = "";
			}
		}
		
		if(parameters.containsKey("PluginName")) {
			pluginName = request.getParameter("PluginName");
			if(pluginName == null) {
				pluginName = "";
			}
		}
		
		if(parameters.containsKey("QueryName")) {
			queryName = request.getParameter("QueryName");
			if(queryName == null) {
				queryName = "";
			}
		}
		
		if(parameters.containsKey("SystemName")) {
			systemName = request.getParameter("SystemName");
			if(systemName == null) {
				systemName = "";
			}
		}
		
		if(parameters.containsKey("ApplicationName")) {
			applicationName = request.getParameter("ApplicationName");
			if(applicationName == null) {
				applicationName = "";
			}
		}
			
		//print out the JSON object
		PrintWriter out = response.getWriter();
		out.print(callback + "(" + getMetrics(useQuery, mysqlQuery, tableName, metricName, useMetrics, limit, 
				idMetric, metricNameGet, idPlatform, platformName, applicationName, 
				idWorkflow, workflowName, idPlugin, pluginName, 
				idQuery, queryName, idSystem, systemName) + ");");	 	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * @param mysqlQuery
	 * @param tableName
	 * @param metricName
	 * @param useMetrics
	 * @param limit
	 * @return
	 */
	private String getMetrics(String useQuery, String mysqlQuery, String tableName, String metricName, String useMetrics, String limit,
			String idMetric, String metricNameGet, String idPlatform, String platformName, String applicationName, 
			String idWorkflow, String workflowName, String idPlugin, String pluginName, 
			String idQuery, String queryName, String idSystem, String systemName) {
		
		ResultSet rs = null;
		Statement s = null;
		ResultSetMetaData rsMetaData;	
						
		String sqlStartDate = "select SettingValue from larkc.vis_settings where SettingName = 'StartDate';";
		String sqlEndDate = "select SettingValue from larkc.vis_settings where SettingName = 'EndDate';";
		
 		JSONArray jsonArray = new JSONArray();
		String startDate = "";
		String endDate = "";
		DBConnector dbConnector = null;
		String myQuery ="";
		String myQuery_select = "";
		
		try {
			
			dbConnector = new DBConnector();
			s = dbConnector.conn.createStatement();
			
			//obtain the start date
			rs = s.executeQuery(sqlStartDate);
			while (rs.next()) {
				startDate = rs.getString(1);
			}
			
			//obtain the end date
			rs = s.executeQuery(sqlEndDate);
			while (rs.next()) {
				endDate = rs.getString(1);
			}
			
			//create query
			if(useQuery.isEmpty()) {
				JSONObject row = new JSONObject();
				try {
					row.put("Init", "Please intialize the portlet's parameters");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				jsonArray.put(row);
				return jsonArray.toString();
			}
			
			else {
				//construct query
				if(useQuery.equalsIgnoreCase("yes")) {
					myQuery = mysqlQuery;
					if(myQuery.isEmpty() || myQuery.equalsIgnoreCase(" ") || (myQuery == null) || myQuery.equalsIgnoreCase("null")) {
						JSONObject row = new JSONObject();
						try {
							row.put("Error", "MySQL Query is empty!");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jsonArray.put(row);
						return jsonArray.toString();
					}
				}	
				//no mysql query - construct query from parameters
				else {
					//get Information Context / Metrics
					boolean isSelectNeeded = false;
					String platform_cond = "";
					String plugin_cond ="";
					String query_cond ="";
					String workflow_cond ="";
					
					//platform information
					if(tableName.equalsIgnoreCase("platforms")) {
						if(useMetrics.equalsIgnoreCase("Metrics")) {
							myQuery_select ="select platforms_metrics.idPlatform, metrics.MetricName, " +
									"platforms_metrics.Value, platforms_metrics.Timestamp " +
									"from platforms_metrics, platforms, metrics " +
									"where metrics.idMetric = platforms_metrics.idMetric " +
									"and platforms.idPlatform = platforms_metrics.idPlatform and " +
									"platforms_metrics.Timestamp >= '" + startDate + "' and " +
									"platforms_metrics.Timestamp <= '" + endDate + "' ";
							
							//metric
							if(!idMetric.isEmpty()) {
								myQuery_select += "and metrics.idMetric = '" + idMetric + "' ";
								
							}
							//metric name from get parameters has priority over metric name from table preferences
							else if(!metricNameGet.isEmpty()) { 
									myQuery_select += "and metrics.MetricName = '" + metricNameGet + "' ";
							}
							else if(!metricName.isEmpty()&& !metricName.equalsIgnoreCase("All")) {
								myQuery_select += "and metrics.MetricName = '" + metricName + "' ";
							}
						}
						else {//context
							myQuery_select = "select platforms.idPlatform, platforms.PlatformName, platforms.ApplicationName " +
							"from platforms where " +
							"platforms.idPlatform in ( " +
								"select distinct platforms.idPlatform from platforms, platforms_metrics where " +
									"platforms.idPlatform = platforms_metrics.idPlatform and " +
									"platforms_metrics.Timestamp >= '" + startDate + "' and " +
									"platforms_metrics.Timestamp <= '" + endDate + "' ) ";
						}
						
						// add get parameters				
						//platform 
						if(!idPlatform.isEmpty()) {
							platform_cond += " and platforms.idPlatform= '" + idPlatform + "' ";
						} 
						else if (!platformName.isEmpty()){
							platform_cond += " and platforms.PlatformName= '" + platformName + "' ";
						}
						if(!applicationName.isEmpty()) {
							platform_cond += " and platforms.ApplicationName = '" + applicationName + "' ";								
						}
						//workflow 
						if(!idWorkflow.isEmpty()) {
							isSelectNeeded = true;		
							workflow_cond += "and workflows.idWorkflow = '" + idWorkflow + "' ";
						}
						else if(!workflowName.isEmpty()){
							isSelectNeeded = true;
							workflow_cond += "and workflows.WorkflowDescription = '" + workflowName + "' ";
						}
						//plugin 
						if(!idPlugin.isEmpty()) {
							isSelectNeeded = true;
							plugin_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
									"where plugins.idPlugin = workflows_plugins.idPlugin " +
									"and workflows.idWorkflow = workflows_plugins.idWorkflow and plugins.idPlugin = '" +
									idPlugin + "' ) ";
						}
						else if(!pluginName.isEmpty()) {
							isSelectNeeded = true;
							plugin_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, plugins, workflows_plugins " +
									"where plugins.idPlugin = workflows_plugins.idPlugin " +
									"and workflows.idWorkflow = workflows_plugins.idWorkflow and plugins.idPlugin = '" +
									pluginName + "' ) ";
						}
						
						//query 
						if(!idQuery.isEmpty()) {
							isSelectNeeded = true;
							query_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, queries, queries_workflows " +
									"where workflows.idWorkflow = queries_workflows.idWorkflow " +
									"and queries.idQuery = queries_workflows.idQuery and queries.idQuery = '" +
									idQuery + "' )";
						}
						else if(!queryName.isEmpty()) {
							isSelectNeeded = true;
							query_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, queries, queries_workflows " +
									"where workflows.idWorkflow = queries_workflows.idWorkflow " +
									"and queries.idQuery = queries_workflows.idQuery and queries.QueryContent = '" +
									queryName + "' )";
						}
						
						if(isSelectNeeded == true) {
							platform_cond += " and platforms.idPlatform in ( " + //paranteza se deschide aici
									"select distinct platforms.idPlatform from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms.idPlatform = platforms_workflows.idPlatform ";
							platform_cond += workflow_cond + query_cond + plugin_cond + ")";//paranteza se inchide aici
							
						}
						myQuery = myQuery_select + platform_cond;
						
					}
					// workflow information
					else if(tableName.equalsIgnoreCase("workflows")) {
						if(useMetrics.equalsIgnoreCase("Metrics")) {
							myQuery_select ="select workflows_metrics.idWorkflow, metrics.MetricName, " +
									"workflows_metrics.Value, workflows_metrics.Timestamp " +
									"from workflows_metrics, workflows, metrics " +
									"where metrics.idMetric = workflows_metrics.idMetric " +
									"and workflows.idWorkflow = workflows_metrics.idWorkflow and " +
									"workflows_metrics.Timestamp >= '" + startDate + "' and " +
									"workflows_metrics.Timestamp <= '" + endDate + "' ";
							
							//metric
							if(!idMetric.isEmpty()) {
								myQuery_select += "and metrics.idMetric = '" + idMetric + "' ";
								
							}
							//metric name from get parameters has priority over metric name from table preferences
							else if(!metricNameGet.isEmpty()) { 
									myQuery_select += "and metrics.MetricName = '" + metricNameGet + "' ";
							}
							else if(!metricName.isEmpty() && !metricName.equalsIgnoreCase("All")) {
								myQuery_select += "and metrics.MetricName = '" + metricName + "' ";
							}
						}
						else {//context
							myQuery_select = "select workflows.idWorkflow, workflows.WorkflowDescription from workflows where " +
								"workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, workflows_metrics where " +
										"workflows.idWorkflow = workflows_metrics.idWorkflow and " +
										"workflows_metrics.Timestamp >= '" + startDate + "' and " +
										"workflows_metrics.Timestamp <= '" + endDate + "' )	";
						}
												
						// add get parameters
						//workflow 
						if(!idWorkflow.isEmpty()) {
							workflow_cond = "and workflows.idWorkflow = '" + idWorkflow + "' ";
						}
						else if(!workflowName.isEmpty()) {
							workflow_cond = "and workflows.WorkflowDescription = '" + workflowName + "' ";
						}
						
						//platform 
						if(!idPlatform.isEmpty()) {
							platform_cond += " and workflows.idWorkflow in ( " +
										"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
										"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
										"and platforms_workflows.idPlatform = platforms.idPlatform " +
										"and platforms.idPlatform = '" + idPlatform + "' ) ";
						}
						else if(!platformName.isEmpty()) {
							platform_cond += " and workflows.idWorkflow in ( " +
										"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
										"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
										"and platforms_workflows.idPlatform = platforms.idPlatform " +
										"and platforms.PlatformName = '" + platformName + "' ) ";
						}
						
						if(!applicationName.isEmpty()) {
							platform_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.ApplicationName = '" + applicationName + "' ) ";
						}
						
						//plugin 
						if(!idPlugin.isEmpty()) {
							plugin_cond +="and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, workflows_plugins, plugins " +
									"where workflows.idWorkflow = workflows_plugins.idWorkflow " +
									"and workflows_plugins.idPlugin = plugins.idPlugin " +
									"and plugins.idPlugin = '" + idPlugin + "' ) ";
						}
						else if(!pluginName.isEmpty()) {
							plugin_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, workflows_plugins, plugins " +
									"where workflows.idWorkflow = workflows_plugins.idWorkflow " +
									"and workflows_plugins.idPlugin = plugins.idPlugin " +
									"and plugins.PluginName = '" + pluginName + "' ) ";
						}
						
						//query 
						if(!idQuery.isEmpty()) {
							query_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, queries_workflows, queries " +
									"where workflows.idWorkflow = queries_workflows.idWorkflow " +
									"and queries_workflows.idQuery = queries.idQuery " +
									"and queries.idQuery = '" + idQuery + "' ) ";
						}
						else if(!queryName.isEmpty()) {
							query_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, queries_workflows, queries " +
									"where workflows.idWorkflow = queries_workflows.idWorkflow " +
									"and queries_workflows.idQuery = queries.idQuery " +
									"and queries.QueryContent = '" + queryName + "'	) ";
						}
																				
						myQuery = myQuery_select + workflow_cond + platform_cond + plugin_cond + query_cond;
					}
					// plugins information
					else if(tableName.equalsIgnoreCase("plugins")) {
						if(useMetrics.equalsIgnoreCase("Metrics")) {
							myQuery_select ="select plugins_metrics.idPlugin, metrics.MetricName, " +
									"plugins_metrics.Value, plugins_metrics.Timestamp " +
									"from plugins_metrics, plugins, metrics " +
									"where metrics.idMetric = plugins_metrics.idMetric " +
									"and plugins.idPlugin = plugins_metrics.idPlugin and " +
									"plugins_metrics.Timestamp >= '" + startDate + "' and " +
									"plugins_metrics.Timestamp <= '" + endDate + "'  ";
							
							//metric
							if(!idMetric.isEmpty()) {
								myQuery_select += "and metrics.idMetric = '" + idMetric + "' ";
								
							}
							//metric name from get parameters has priority over metric name from table preferences
							else if(!metricNameGet.isEmpty()) { 
									myQuery_select += "and metrics.MetricName = '" + metricNameGet + "' ";
							}
							else if(!metricName.isEmpty() && !metricName.equalsIgnoreCase("All")) {
								myQuery_select += "and metrics.MetricName = '" + metricName + "' ";
							}
						}
						else {//context
							myQuery_select = "select plugins.PluginName, plugins.idPlugin from plugins where " +
								"plugins.idPlugin in ( " +
									"select distinct plugins.idPlugin from plugins, plugins_metrics where " +
										"plugins.idPlugin = plugins_metrics.idPlugin and " +
										"plugins_metrics.Timestamp >= '" + startDate + "' and " +
										"plugins_metrics.Timestamp <= '" + endDate + "' ) ";
						}
						
						// add get parameters
						//plugin
						if(!idPlugin.isEmpty()) {
							plugin_cond += "and plugins.idPlugin = '" + idPlugin + "' ";
						}
						else if(!pluginName.isEmpty()) {
							plugin_cond += "and plugins.PluginName = '" + pluginName + "' ";
						}
						
						//workflow
						if(!idWorkflow.isEmpty()) {
							isSelectNeeded = true;
							workflow_cond += "and workflows.idWorkflow = '" + idWorkflow + "' ";								
						}
						else if(!workflowName.isEmpty()) {
							isSelectNeeded = true;
							workflow_cond += "and workflows.WorkflowDescription = '"+ workflowName + "' "; 
						}
						
						//platform
						if(!idPlatform.isEmpty()) {
							isSelectNeeded = true;
							platform_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.idPlatform = '" + idPlatform + "' ) ";
						}
						else if(!platformName.isEmpty()) {
							isSelectNeeded = true;
							platform_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.PlatformName = '" + platformName + "' )";
						}
						if(!applicationName.isEmpty()) {
							isSelectNeeded = true;
							platform_cond += "and workflows.idWorkflow in (" +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow" +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.ApplicationName = '" + applicationName + "' )";
						}
						
						//query
						if(!idQuery.isEmpty()) {
							isSelectNeeded = true;
							query_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, queries_workflows, queries " +
									"where workflows.idWorkflow = queries_workflows.idWorkflow " +
									"and queries.idQuery = queries_workflows.idQuery " +
									"and queries.idQuery = '" + idQuery + "' ) ";
						}
						else if(!queryName.isEmpty()) {
							isSelectNeeded = true;
							query_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, queries_workflows, queries " +
									"where workflows.idWorkflow = queries_workflows.idWorkflow " +
									"and queries.idQuery = queries_workflows.idQuery " +
									"and queries.QueryContent = '" + queryName + "' )";
						}
							
						if(isSelectNeeded == true) {
							plugin_cond += "and plugins.idPlugin in (" + //paranteza se deschide aici
										"select distinct plugins.idPlugin from plugins, workflows_plugins, workflows " +
										"where plugins.idPlugin = workflows_plugins.idPlugin " +
										"and workflows_plugins.idWorkflow = workflows.idWorkflow ";
							
							plugin_cond += workflow_cond + query_cond+ platform_cond + " )";//paranteza se inchide aici
						}
						
						myQuery = myQuery_select + plugin_cond;
					}
					// queries information
					else if(tableName.equalsIgnoreCase("queries")) {
						if(useMetrics.equalsIgnoreCase("Metrics")) {
							myQuery_select ="select queries_metrics.idQuery, metrics.MetricName, " +
									"queries_metrics.Value, queries_metrics.Timestamp " +
									"from queries_metrics, queries, metrics " +
									"where metrics.idMetric = queries_metrics.idMetric " +
									"and queries.idQuery = queries_metrics.idQuery and " +
									"queries_metrics.Timestamp >= '" + startDate + "' and " +
									"queries_metrics.Timestamp <= '" + endDate + "' ";
							
							//metric
							if(!idMetric.isEmpty()) {
								myQuery_select += "and metrics.idMetric = '" + idMetric + "' ";
								
							}
							//metric name from get parameters has priority over metric name from table preferences
							else if(!metricNameGet.isEmpty()) { 
									myQuery_select += "and metrics.MetricName = '" + metricNameGet + "' ";
							}
							else if(!metricName.isEmpty() && !metricName.equalsIgnoreCase("All")) {
								myQuery_select += "and metrics.MetricName = '" + metricName + "' ";
							}
						}
						else {//context
							myQuery_select = "select queries.idQuery, queries.QueryContent from queries where " +
								"queries.idQuery in ( " +
									"select distinct queries.idQuery from queries, queries_metrics where " +
										"queries.idQuery = queries_metrics.idQuery	and " +
										"queries_metrics.Timestamp >= '" + startDate + "' and " +
										"queries_metrics.Timestamp <= '" + endDate + "' ) ";
						}
						// add get parameters						
						//query
						if(!idQuery.isEmpty()) {
							query_cond += "and queries.idQuery = '" + idQuery + "' ";
						}
						else if(!queryName.isEmpty()) {
							query_cond += "and queries.QueryContent = '" + queryName + "' ";
						}
						
						//workflow
						if(!idWorkflow.isEmpty()) {
							isSelectNeeded = true;
							workflow_cond += "and workflows.idWorkflow = '" + idWorkflow + "' ";								
						}
						else if(!workflowName.isEmpty()){
							isSelectNeeded = true;
							workflow_cond += "and workflows.WorkflowDescription = '" + workflowName + "' ";
						}
						
						//plugin
						if(!idPlugin.isEmpty()) {
							isSelectNeeded = true;
							plugin_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, workflows_plugins, plugins " +
									"where workflows.idWorkflow = workflows_plugins.idWorkflow " +
									"and workflows_plugins.idPlugin = plugins.idPlugin " +
									"and plugins.idPlugin = '" + idPlugin + "' ) ";
						}
						else if(!pluginName.isEmpty()) {
							isSelectNeeded = true;
							plugin_cond += "and workflows.idWorkflow in (" +
									"select distinct workflows.idWorkflow from workflows, workflows_plugins, plugins " +
									"where workflows.idWorkflow = workflows_plugins.idWorkflow " +
									"and workflows_plugins.idPlugin = plugins.idPlugin " +
									"and plugins.PluginName = '" + pluginName + "' ) ";
						}
						
						//platform
						if(!idPlatform.isEmpty()) {
							isSelectNeeded = true;
							platform_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.idPlatform = '" + idPlatform + "' ) ";
						}
						else if(!platformName.isEmpty()) {
							isSelectNeeded = true;
							platform_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.PlatformName = '" + platformName + "' ) ";
						}
						
						if(!applicationName.isEmpty()) {
							isSelectNeeded = true;
							platform_cond += "and workflows.idWorkflow in ( " +
									"select distinct workflows.idWorkflow from workflows, platforms_workflows, platforms " +
									"where workflows.idWorkflow = platforms_workflows.idWorkflow " +
									"and platforms_workflows.idPlatform = platforms.idPlatform " +
									"and platforms.ApplicationName = '" + applicationName + "' ) ";
						}
						
						if(isSelectNeeded == true) {
							query_cond += "and queries.idQuery in ( " + //paranteza se deschide aici
									"select distinct queries.idQuery from queries, queries_workflows, workflows " +
									"where queries.idQuery = queries_workflows.idQuery " +
									"and queries_workflows.idWorkflow = workflows.idWorkflow ";
							query_cond += workflow_cond + plugin_cond + platform_cond + " ) ";  //paranteza se inchide aici
						}
						
						myQuery = myQuery_select + query_cond;
					}
					//systems context information
					else if(tableName.equalsIgnoreCase("systems")) {
						
						if(useMetrics.equalsIgnoreCase("Metrics")) {
							myQuery_select ="select systems_metrics.idSystem, metrics.MetricName, " +
									"systems_metrics.Value, systems_metrics.Timestamp " +
									"from systems, metrics, systems_metrics where " +
									"systems.idSystem=systems_metrics.idSystem and metrics.idMetric=systems_metrics.idMetric and " +
									"systems_metrics.Timestamp >= '" + startDate + "' and " +
									"systems_metrics.Timestamp <= '" + endDate + "' ";
							
							//metric
							if(!idMetric.isEmpty()) {
								myQuery_select += "and metrics.idMetric = '" + idMetric + "' ";
								
							}
							//metric name from get parameters has priority over metric name from table preferences
							else if(!metricNameGet.isEmpty()) { 
									myQuery_select += "and metrics.MetricName = '" + metricNameGet + "' ";
							}
							else if(!metricName.isEmpty() && !metricName.equalsIgnoreCase("All")) {
								myQuery_select += "and metrics.MetricName = '" + metricName + "' ";
							}
						}
						else {//context
							myQuery_select = "select systems.SystemName, systems.SystemTotalMemory, " +
								"systems.SystemCPUCount, systems.idSystem from systems where " +
								"systems.idSystem in ( " +
									"select distinct systems.idSystem from systems, systems_metrics where " +
										"systems.idSystem = systems_metrics.idSystem and " +
										"systems_metrics.Timestamp >= '" + startDate + "' and " +
										"systems_metrics.Timestamp <= '" + endDate + "' ) ";
						
						
						}
						//add get parameters: only SystemName and idSystem
						if(!idSystem.isEmpty()) {
							myQuery_select += "and systems.idSystem = '" + idSystem + "' " ;
						}
						else if(!systemName.isEmpty()) {
							myQuery_select += "and systems.SystemName = '" + systemName + "' ";
						}
						myQuery = myQuery_select;
					}
					
					// add limit and other clauses
					if(!limit.isEmpty() || !limit.equalsIgnoreCase("null") || !(limit == null)) {
						if(!limit.equalsIgnoreCase("MAX") ) {
							myQuery += " limit " + limit + ";";
						}
					}	
				}
				
			}
						
			//obtain results
			rs = s.executeQuery(myQuery);
			rsMetaData = (ResultSetMetaData) rs.getMetaData();
			while(rs.next()) {
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
				jsonArray.put(row);
			}
			return jsonArray.toString();
						
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(myQuery);
			JSONObject row = new JSONObject();
			try {
				row.put("SQL Error", e.toString());
				rs.close();
				s.close();
				dbConnector.closeConnection();
			} catch (JSONException je) {
				je.printStackTrace();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			jsonArray.put(row);
			return jsonArray.toString();
		} 
		finally {
			try {
				rs.close();
				s.close();
				dbConnector.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
		}
		
	}

}
