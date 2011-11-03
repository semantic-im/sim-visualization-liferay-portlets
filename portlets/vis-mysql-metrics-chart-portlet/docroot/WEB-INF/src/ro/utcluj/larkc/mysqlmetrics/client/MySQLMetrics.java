package ro.utcluj.larkc.mysqlmetrics.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import ro.utcluj.larkc.mysqlmetrics.client.charts.OfcgwtBarChart;
import ro.utcluj.larkc.mysqlmetrics.client.charts.OfcgwtPieChart;
import ro.utcluj.larkc.mysqlmetrics.client.view.MySQLMetricsView;
import ro.utcluj.larkc.mysqlmetrics.shared.FieldVerifier;

import ro.utcluj.larkc.mysqlmetrics.shared.JsPortletInstance;
import ro.utcluj.larkc.mysqlmetrics.shared.JsniWizard;
import ro.utcluj.larkc.mysqlmetrics.shared.MetricEntries;
import ro.utcluj.larkc.mysqlmetrics.shared.MetricEntry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;

import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sun.org.apache.xpath.internal.operations.Bool;



public class MySQLMetrics {

	
	int chartRefresh = 5000;
	JsniWizard jsniWizard = new JsniWizard();
	
	Timer refreshTimer = null;
	
	//get the Servlet Context Path using the JSNI
	String metricServletPath = jsniWizard.getMetricServletPath();
	
	MySQLMetricsView mysqlMetricView = null;
	String charttype = "PieChart";
	
	//Servlet URL
	String url = "";
	String urlExtraQueryStringParameters = "";
	
	//Jsonp Request Builder Object
	JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
	
	//Parameters map
	Map<?,?> parameters = null;
	
	boolean isLive = false;
	
	String chartcmd = "";
	String idWorkflow = "";
	String workflowName = "";
	String idPlugin = "";
	String pluginName = "";
	String idMetric = "";	
	String portletId = "";		
	String platformLevel = "";
	String metricName = "";		
	String mysqlQuery = "";
	String groupOrderLimitClauses = "";
	String idSystem = "";
	String systemName = "";
	String idPlatform = "";
	String platformName = "";
	String idQuery = "";
	String queryName = "";
	String appName = "";
	
	String chartcmd2 = "";
	String idWorkflow2 = "";
	String workflowName2 = "";
	String idPlugin2 = "";
	String pluginName2 = "";
	String idMetric2 = "";	
	String platformLevel2 = "";
	String metricName2 = "";		
	String mysqlQuery2 = "";
	String groupOrderLimitClauses2 = "";
	String idSystem2 = "";
	String systemName2 = "";
	String idPlatform2 = "";
	String platformName2 = "";
	String idQuery2 = "";
	String queryName2 = "";
	String appName2 = "";
	
	String chartNumber = "";
	
    String liveMode = "";
    String refreshTime = "";
    String preselectedPeriod = "";
    String lineChartResolution = "";
    String lineChartAggregateMethod = "";
    String interpolateMode = "";
    

	//RootPanel.get().add(new Label(portletId+"Portlet"));

	public MySQLMetrics(JsPortletInstance instance){

		jsonp.setTimeout(2*60*60*1000);
		
		if(instance != null){		
			
			//Chart 1
			mysqlQuery = instance.getMysqlQuery();
			chartcmd = instance.getChartcmd();
			idWorkflow = instance.getidWorkflow();
			workflowName = instance.getWorkflowName();
			idPlugin = instance.getIdPlugin();
			pluginName = instance.getPluginName();
			idMetric = instance.getIdMetric();		
			groupOrderLimitClauses = instance.getGroupOrderLimitClauses();
			platformLevel = instance.getPlatformLevel();
			metricName = instance.getMetricName();
			idSystem = instance.getidSystem();
			systemName = instance.getSystemName();
			idPlatform = instance.getIdPlatform();
			platformName = instance.getPlatformName();
			idQuery = instance.getIdQuery();
			queryName = instance.getQueryName();
			appName = instance.getAppName();
			
			//portlet ID
			portletId = instance.getPortletID();
			
			//Chart 2
			mysqlQuery2 = instance.getMysqlQuery2();
			chartcmd2 = instance.getChartcmd2();
			idWorkflow2 = instance.getidWorkflow2();
			workflowName2 = instance.getWorkflowName2();
			idPlugin2 = instance.getIdPlugin2();
			pluginName2 = instance.getPluginName2();
			idMetric2 = instance.getIdMetric2();
			groupOrderLimitClauses2 = instance.getGroupOrderLimitClauses2();			
			platformLevel2 = instance.getPlatformLevel2();
			metricName2 = instance.getMetricName2();
			idSystem2 = instance.getidSystem2();
			systemName2 = instance.getSystemName2();
			idPlatform2 = instance.getIdPlatform2();
			platformName2 = instance.getPlatformName2();
			idQuery2 = instance.getIdQuery2();
			queryName2 = instance.getQueryName2();
			appName2 = instance.getAppName2();
			
			liveMode = instance.getLiveMode();
		    refreshTime = instance.getRefreshTime();
		    preselectedPeriod = instance.getPreselectedPeriod();
		    lineChartResolution = instance.getLineChartResolution();
		    lineChartAggregateMethod = instance.getlineChartAggregateMethod();
		    interpolateMode = instance.getInterpolateMode();
		    
	   	
		}
		
		
		parameters = Window.Location.getParameterMap();
		if(parameters.containsKey("charttype")){
			charttype = parameters.get("charttype").toString();
			charttype = charttype.replace("[", "");
			charttype = charttype.replace("]", "");
			
		}
		else{
			charttype = instance.getChartType();
		}
		
		if(charttype == null){
			return;
		}
		
		chartNumber = "";
		urlExtraQueryStringParameters = getServletURLExtraParameters(liveMode,refreshTime,preselectedPeriod,lineChartResolution,lineChartAggregateMethod, interpolateMode);
		url = getServletURL(mysqlQuery, chartcmd, idWorkflow, workflowName, idPlugin, pluginName, idMetric, groupOrderLimitClauses, platformLevel, metricName, idSystem, systemName, idPlatform, platformName, idQuery, queryName, appName, chartNumber);	
		url +=urlExtraQueryStringParameters;
		url = URL.encode(url);
		
		
		mysqlMetricView = new MySQLMetricsView(charttype);


		
		jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
			public void onFailure(Throwable throwable) {
				GWT.log("Error: " + throwable);
			}

			public void onSuccess(MetricEntries metrics) {
				JsArray<MetricEntry> entries = metrics.getMetricEntries();
				mysqlMetricView.loadData(entries,charttype, isLive,1);
				mysqlMetricView.drawChart(charttype);
				
			}
		});
		
		//For LineChart there could be many lines on the same chart
		if( charttype.equalsIgnoreCase("LineChart") && 
		    ( (parameters.containsKey("metricName2")) || (parameters.containsKey("mysqlQuery2")) || (parameters.containsKey("idMetric2")) 
		    		|| (!metricName2.isEmpty()) || (!mysqlQuery2.isEmpty()) || (!idMetric2.isEmpty())) 
		    
		){
			chartNumber = "2";
			 
			url = getServletURL(mysqlQuery2, chartcmd2, idWorkflow2, workflowName2, idPlugin2, pluginName2, idMetric2, groupOrderLimitClauses2, platformLevel2, metricName2, idSystem2 , systemName2, idPlatform2, platformName2, idQuery2, queryName2, appName2, chartNumber);
			url +=urlExtraQueryStringParameters;
			url = URL.encode(url);
			
			jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
				public void onFailure(Throwable throwable) {
					GWT.log("Error: " + throwable);
				}

				public void onSuccess(MetricEntries metrics) {
					JsArray<MetricEntry> entries = metrics.getMetricEntries();
					mysqlMetricView.loadData(entries,charttype,isLive,2);
					mysqlMetricView.drawChart(charttype);
				}
			});
		}

		if(isLive){
			refreshTimer= new Timer() {
				public void run() {			
					refreshChart();		
				}
			};
			
			refreshTimer.scheduleRepeating(chartRefresh);
		}
		
		
		RootPanel.get(portletId).add(mysqlMetricView);
		//RootPanel.get().add(mysqlMetricView);
		
	}
	
	public void refreshChart(){
		
		url = getServletURL(mysqlQuery, chartcmd, idWorkflow, workflowName, idPlugin, pluginName, idMetric, groupOrderLimitClauses, platformLevel, metricName, idSystem, systemName, idPlatform, platformName, idQuery, queryName, appName, chartNumber);
		url +=urlExtraQueryStringParameters;
		url = URL.encode(url);
		
		chartNumber = "";
		jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
			public void onFailure(Throwable throwable) {
				GWT.log("Error: " + throwable);
			}

			public void onSuccess(MetricEntries metrics) {
				JsArray<MetricEntry> entries = metrics.getMetricEntries();
				if(charttype.equalsIgnoreCase("PieChart")||charttype.equalsIgnoreCase("[PieChart]")){
					mysqlMetricView.ofcgwtPie.reloadChart(entries);
				}
				else if(charttype.equalsIgnoreCase("BarChart")||charttype.equalsIgnoreCase("[BarChart]")){
					mysqlMetricView.ofcgwtBar.reloadChart(entries);
				}
				else {
					if(mysqlMetricView.hasTextMessage){
						String metricName = entries.get(0).getMetricName();
						
						mysqlMetricView.hasTextMessage = false;
						
						if((metricName==null)||
								   (metricName.isEmpty())||
								   (metricName.compareToIgnoreCase("undefined") == 0)){
									metricName = entries.get(0).getName();
									if( (metricName==null)||
										(metricName.compareToIgnoreCase("undefined") == 0)||
									    (metricName.isEmpty())){
											mysqlMetricView.hasTextMessage = true;
											
											
									}
									
								}
						
						if(!mysqlMetricView.hasTextMessage){
							mysqlMetricView.noData.setVisible(false);
							
							//reload data with refresh
							mysqlMetricView.ofcgwtLine.reloadLine(entries, 1, true);
							
							
						}
					}else{
						if(!mysqlMetricView.ofcgwtLine.reloadLine(entries, 1, false)){
							mysqlMetricView.noData.setVisible(true);
							mysqlMetricView.hasTextMessage = true;
						}
					}
					//mysqlMetricView.drawChart(charttype);
				}
			}
		});
		
		//For LineChart there could be many lines on the same chart
		if( charttype.equalsIgnoreCase("LineChart") && 
		    ( (parameters.containsKey("metricName2")) || (parameters.containsKey("mysqlQuery2")) || (parameters.containsKey("idMetric2")) 
		    		|| (!metricName2.isEmpty()) || (!mysqlQuery2.isEmpty()) || (!idMetric2.isEmpty())) 
		    
		){
			chartNumber = "2";
			 
			url = getServletURL(mysqlQuery2, chartcmd2, idWorkflow2, workflowName2, idPlugin2, pluginName2, idMetric2, groupOrderLimitClauses2, platformLevel2, metricName2, idSystem2 , systemName2, idPlatform2, platformName2, idQuery2, queryName2, appName2, chartNumber);
			url +=urlExtraQueryStringParameters;
			url = URL.encode(url);
			
			jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
				public void onFailure(Throwable throwable) {
					GWT.log("Error: " + throwable);
				}

				public void onSuccess(MetricEntries metrics) {
					JsArray<MetricEntry> entries = metrics.getMetricEntries();
					if(charttype.equalsIgnoreCase("PieChart")||charttype.equalsIgnoreCase("[PieChart]")){
						mysqlMetricView.ofcgwtPie.reloadChart(entries);
					}
					else if(charttype.equalsIgnoreCase("BarChart")||charttype.equalsIgnoreCase("[BarChart]")){
						mysqlMetricView.ofcgwtBar.reloadChart(entries);
					}
					else {
						if(mysqlMetricView.hasTextMessage){
							
							String metricName = entries.get(0).getMetricName();
							
							mysqlMetricView.hasTextMessage = false;
							
							if((metricName==null)||
									   (metricName.isEmpty())||
									   (metricName.compareToIgnoreCase("undefined") == 0)){
										metricName = entries.get(0).getName();
										if( (metricName==null)||
											(metricName.compareToIgnoreCase("undefined") == 0)||
										    (metricName.isEmpty())){
												mysqlMetricView.hasTextMessage = true;
												
												
										}
										
									}
							
							if(!mysqlMetricView.hasTextMessage){

								mysqlMetricView.noData.setVisible(false);
								
								//reload data with refresh
								mysqlMetricView.ofcgwtLine.reloadLine(entries, 2, true);
								
//								mysqlMetricView.vp.clear();
//								mysqlMetricView.vp.add(mysqlMetricView.noData);
//								mysqlMetricView.noData.setVisible(false);
//								//mysqlMetricView.errorText.setVisible(false);
//								mysqlMetricView.loadData(entries,charttype,isLive,1);
//								mysqlMetricView.drawChart(charttype);
							}
						}else{
							if(!mysqlMetricView.ofcgwtLine.reloadLine(entries, 2, false)){
								mysqlMetricView.noData.setVisible(true);
								mysqlMetricView.hasTextMessage = true;
							}
						}
					}
				}
			});
		}
	}
	
	public String getServletURL(String mysqlQueryVal, String chartcmdVal, String idWorkflowVal, String workflowNameVal, String idPluginVal, String pluginNameVal,
								String idMetricVal, String groupOrderLimitClausesVal, String platformLevelVal,String metricNameVal, 
								String idSystemVal, String systemNameVal, String idPlatformVal, String platformNameVal, String idQueryVal, String queryNameVal, String appNameVal,
								String chartNumber){
		
		String url = metricServletPath;
		
		String chartcmd = "chartcmd" + chartNumber;
		String idWorkflow = "idWorkflow" + chartNumber;
		String workflowName = "workflowName" + chartNumber;
		String idPlugin = "idPlugin" + chartNumber;
		String pluginName = "pluginName" + chartNumber;
		String idMetric = "idMetric" + chartNumber;	
		String platformLevel = "platformLevel" + chartNumber;
		String metricName = "metricName" + chartNumber;		
		String mysqlQuery = "mysqlQuery" + chartNumber;
		String groupOrderLimitClauses = "groupOrderLimitClauses" + chartNumber;	
		String idSystem = "idSystem" + chartNumber;
		String systemName = "systemName" + chartNumber;
		String idPlatform = "idPlatform" + chartNumber;
		String platformName = "platformName" + chartNumber;
		String idQuery = "idQuery" + chartNumber;
		String queryName = "queryName" + chartNumber;
		String appName = "appName" + chartNumber;
		String startTime = "";
		String endTime = "";
	   
		//Set<Map.Entry<String, List<String>>> entrySet = Window.Location.getParameterMap().entrySet();
		//Iterator<Entry<String, List<String>>> iterator = entrySet.iterator();
		
		
		
		Map<String,List<String>> parameters = Window.Location.getParameterMap();
		
		/* deciding between load GET parameters and loading parameters from Portlet Configuration */
		if(!(chartcmd = getKeyIgnoreCase(parameters, chartcmd)).isEmpty()){
			url += "?" + "chartcmd=" + parameters.get(chartcmd);
		}
		else{
			url += "?" + "chartcmd=" + chartcmdVal;
		}
		
		if(!(platformLevel = getKeyIgnoreCase(parameters, platformLevel)).isEmpty()){
			url += "&" + "platformLevel=" + parameters.get(platformLevel).get(0);
		}
		else{
			url += !platformLevelVal.isEmpty() ? "&" + "platformLevel=" + platformLevelVal : "";
		}
		
		if(!(idWorkflow = getKeyIgnoreCase(parameters, idWorkflow)).isEmpty()){
			url += "&" + "idWorkflow=" + parameters.get(idWorkflow).get(0);
		}
		else{
			url += !idWorkflowVal.isEmpty() ? "&" + "idWorkflow=" + idWorkflowVal : "";
		}
		
		if(!(idPlugin = getKeyIgnoreCase(parameters, idPlugin)).isEmpty()){
			url += "&" + "idPlugin=" + parameters.get(idPlugin).get(0);
		}
		else{
			url += !idPluginVal.isEmpty() ? "&" + "idPlugin=" + idPluginVal : "";
		}
		
		
		if(!(mysqlQuery = getKeyIgnoreCase(parameters, mysqlQuery)).isEmpty()){
			url += "&" + "mysqlQuery=" + parameters.get(mysqlQuery).get(0);
		}
		else{
			url += !mysqlQueryVal.isEmpty() ? "&" + "mysqlQuery=" + mysqlQueryVal : "";
		}
		
		if(!(idMetric = getKeyIgnoreCase(parameters, idMetric)).isEmpty()){
			url += "&" + "idMetric=" + parameters.get(idMetric).get(0);
		}
		else{
			url += !idMetricVal.isEmpty() ? "&" + "idMetric=" + idMetricVal : "";
		}
		
		if(!(groupOrderLimitClauses = getKeyIgnoreCase(parameters, groupOrderLimitClauses)).isEmpty()){
			url += "&" + "groupOrderLimitClauses=" + parameters.get(groupOrderLimitClauses).get(0);
		}
		else{
			url += !groupOrderLimitClausesVal.isEmpty() ? "&" + "groupOrderLimitClauses=" + groupOrderLimitClausesVal : "";
		}		
		
		if(!(metricName = getKeyIgnoreCase(parameters, metricName)).isEmpty()){
			url += "&" + "metricName=" + parameters.get(metricName).get(0);
		}
		else{
			url += !metricNameVal.isEmpty() ? "&" + "metricName=" + metricNameVal : "";
		}
		
		if(!(workflowName = getKeyIgnoreCase(parameters, workflowName)).isEmpty()){
			url += "&" + "workflowName=" + parameters.get(workflowName).get(0);
		}else{
			url += !workflowNameVal.isEmpty() ? "&" + "workflowName=" + workflowNameVal : "";
		}
	
		if(!(pluginName = getKeyIgnoreCase(parameters, pluginName)).isEmpty()){
			url += "&" + "pluginName=" + parameters.get(pluginName).get(0);
		}else{
			url += !pluginNameVal.isEmpty() ? "&" + "pluginName=" + pluginNameVal : "";
		}
		
		if(!(idQuery = getKeyIgnoreCase(parameters, idQuery)).isEmpty()){
			url += "&" + "idQuery=" + parameters.get(idQuery).get(0);
		}else{
			url += !idQueryVal.isEmpty() ? "&" + "idQuery=" + idQueryVal : "";
		}
		
		if(!(queryName = getKeyIgnoreCase(parameters, queryName)).isEmpty()){
			url += "&" + "queryName=" + parameters.get(queryName).get(0);
		}else{
			url += !queryNameVal.isEmpty() ? "&" + "queryName=" + queryNameVal : "";
		}
		
		if(!(appName = getKeyIgnoreCase(parameters, appName)).isEmpty()){
			url += "&" + "appName=" + parameters.get(appName).get(0);
		}else{
			url += !appNameVal.isEmpty() ? "&" + "appName=" + appNameVal : "";
		}
		
		if(!(idSystem = getKeyIgnoreCase(parameters, idSystem)).isEmpty()){
			url += "&" + "idSystem=" + parameters.get(idSystem).get(0);
		}else{
			url += !idSystemVal.isEmpty() ? "&" + "idSystem=" + idSystemVal : "";
		}
		
		if(!(systemName = getKeyIgnoreCase(parameters, systemName)).isEmpty()){
			url += "&" + "systemName=" + parameters.get(systemName).get(0);
		}else{
			url += !systemNameVal.isEmpty() ? "&" + "systemName=" + systemNameVal : "";
		}
	
		if(!(idPlatform = getKeyIgnoreCase(parameters, idPlatform)).isEmpty()){
			url += "&" + "idPlatform=" + parameters.get(idPlatform).get(0);
			
		}else{
			url += !idPlatformVal.isEmpty() ? "&" + "idPlatform=" + idPlatformVal : "";
			
		}
		
		if(!(platformName = getKeyIgnoreCase(parameters, platformName)).isEmpty()){
			url += "&" + "platformName=" + parameters.get(platformName).get(0);
		}else{
			url += !platformNameVal.isEmpty() ? "&" + "platformName=" + platformNameVal : "";
		}
		
		/* only from get parameters */
		
		if(!(startTime = getKeyIgnoreCase(parameters, "startTime")).isEmpty()){
			url += "&" + "startTime=" + parameters.get(startTime).get(0);
		}
		
	
		if(!(endTime = getKeyIgnoreCase(parameters, "endTime")).isEmpty()){
			url += "&" + "endTime=" + parameters.get(endTime).get(0);
		}
		
		url = url.replace("[", "");
		url = url.replace("]", "");
		
		return url;
	}
	
	
	public String getServletURLExtraParameters(String liveModeVal, String refreshTimeVal, String preselectedPeriodVal, String lineChartResolutionVal, String lineChartAggregateMethodVal, String interpolateModeVal){

		String url = "";
		
		String liveMode = "";
		String refreshTime = "";
		String preselectedPeriod = "";
		String lineChartResolution = "";
		String lineChartAggregateMethod = "";
		String interpolateMode = "";
		
		Map<String,List<String>> parameters = Window.Location.getParameterMap();

		/* deciding between load GET parameters and loading parameters from Portlet Configuration */
		if(!(liveMode = getKeyIgnoreCase(parameters, "liveMode")).isEmpty()){
			
			String liveModeStr = parameters.get(liveMode).get(0).toString();
			liveModeStr = liveModeStr.replace("[", "");
			liveModeStr = liveModeStr.replace("]", "");
			liveModeStr = liveModeStr.trim();
			
			url += "&" + "liveMode=" + liveModeStr;
			
			//isLive give value of true or false
			isLive = liveModeStr.equalsIgnoreCase("isLive");
			
		}
		else{
			url += "&" + "liveMode=" + liveModeVal;
			isLive = liveModeVal.equalsIgnoreCase("isLive");
		}
		
		
		if(!(refreshTime = getKeyIgnoreCase(parameters, "refreshTime")).isEmpty()){
			url += "&" + "refreshTime=" + parameters.get(refreshTime).get(0);
			String rTime = parameters.get(refreshTime).toString();
			rTime = rTime.replace("[", "");
			rTime = rTime.replace("]", "");
			chartRefresh = !refreshTime.isEmpty() ? Integer.valueOf(rTime) : chartRefresh;
		}
		else{
			url += "&" + "refreshTime=" + refreshTimeVal;
			chartRefresh = (!refreshTimeVal.isEmpty()) && (refreshTimeVal != null)? Integer.valueOf(refreshTimeVal) : chartRefresh;
		}

		if(!(preselectedPeriod = getKeyIgnoreCase(parameters, "preselectedPeriod")).isEmpty()){
			url += "&" + "preselectedPeriod=" + parameters.get(preselectedPeriod).get(0);
		}
		else{
			url += "&" + "preselectedPeriod=" + preselectedPeriodVal;
		}
		
		if(!(lineChartResolution = getKeyIgnoreCase(parameters, "lineChartResolution")).isEmpty()){
			url += "&" + "lineChartResolution=" + parameters.get(lineChartResolution).get(0);
		}
		else{
			url += "&" + "lineChartResolution=" + lineChartResolutionVal;
		}
		
		if(!(lineChartAggregateMethod = getKeyIgnoreCase(parameters, "lineChartAggregateMethod")).isEmpty()){
			url += "&" + "lineChartAggregateMethod=" + parameters.get(lineChartAggregateMethod).get(0);
		}
		else{
			url += "&" + "lineChartAggregateMethod=" + lineChartAggregateMethodVal;
		}
		
		if(!(interpolateMode = getKeyIgnoreCase(parameters, "interpolateMode")).isEmpty()){
			url += "&" + "interpolateMode=" + parameters.get(interpolateMode).get(0);
		}
		else{
			url += "&" + "interpolateMode=" + interpolateModeVal;
		}
		
		url = url.replace("[", "");
		url = url.replace("]", "");

		return url;
	}
	@SuppressWarnings("unchecked")
	public String getKeyIgnoreCase(Map<String, List<String>> map, String key){
		
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
