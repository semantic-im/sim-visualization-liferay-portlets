package com.liferay.portlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liferay.util.bridges.mvc.MVCPortlet;

public class MysqlMetricsPortlet extends MVCPortlet {
	
	public static String dbName;
	public static String userName;
	public static String password;
	
	protected String editJSP;
	protected String viewJSP;
	
	private static Log _log = LogFactory.getLog(MysqlMetricsPortlet.class);
	
	public void init() throws PortletException {
		editJSP = getInitParameter("edit-jsp");
		viewJSP = getInitParameter("view-jsp");
		dbName = getInitParameter("dbName");
		userName = getInitParameter("userName");
		password = getInitParameter("password");
	}
	
	ArrayList<String> portletInfoTexts = new ArrayList<String>();
	ArrayList<String> portletInfoValues = new ArrayList<String>(); 
	ArrayList<String> portletInfoNames = new ArrayList<String>();

	/* Forward to a given path */
	protected void include(
			String path, RenderRequest renderRequest,
			RenderResponse renderResponse)
	throws IOException, PortletException {
		PortletRequestDispatcher portletRequestDispatcher =
			getPortletContext().getRequestDispatcher(path);
		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		} else {
			portletRequestDispatcher.include(
					renderRequest, renderResponse);
		}
	}
	
	/* Render View Mode */
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse)
	throws IOException, PortletException {

		portletInfoTexts.clear();
		portletInfoValues.clear();
		portletInfoNames.clear();
		
		PortletPreferences prefs = renderRequest.getPreferences();
		
		String metricName = (String)prefs.getValue("metricName", "no");
		if((metricName == null)){
			metricName="";
		}else if(metricName.equalsIgnoreCase("no")) { 
			metricName="";
		}
		portletInfoTexts.add("Metric Name:");
		portletInfoValues.add(metricName);
		portletInfoNames.add("metricName");
		
		String metricName2 = (String)prefs.getValue("metricName2", "no");
		if((metricName2 == null)){
			metricName2="";
		}else if(metricName2.equalsIgnoreCase("no")) { 
			metricName2="";
		}
		portletInfoTexts.add("Second Metric Name:");
		portletInfoValues.add(metricName2);
		portletInfoNames.add("metricName2");
		
		String platformLevel = (String)prefs.getValue("platformLevel", "no");
		if((platformLevel == null)){
			platformLevel="";
		}else if(platformLevel.equalsIgnoreCase("no")) { 
			platformLevel="";
		}
		portletInfoTexts.add("Visualization Level:");
		portletInfoValues.add(platformLevel.replace("-1", ""));
		portletInfoNames.add("platformLevel");
		
		String platformLevel2 = (String)prefs.getValue("platformLevel2", "no");
		if((platformLevel2 == null)){
			platformLevel2="";
		}else if(platformLevel2.equalsIgnoreCase("no")) { 
			platformLevel2="";
		}
		portletInfoTexts.add("Visualization Level for seccond chart:");
		portletInfoValues.add(platformLevel2.replace("-1", "")); 
		portletInfoNames.add("platformLevel2");
		
		String charttype = (String)prefs.getValue("charttype", "no");
		if((charttype == null)){
			charttype="";
		}else if (charttype.equalsIgnoreCase("no")) {
			charttype="";
		}
		
		String chartcmd = (String)prefs.getValue("chartcmd", "no");
		if((chartcmd == null)){
			chartcmd="";
		}else if(chartcmd.equalsIgnoreCase("no")) {
			chartcmd="";
		}
		
		String chartcmd2 = (String)prefs.getValue("chartcmd2", "no");
		if((chartcmd2 == null)){
			chartcmd2="";
		}else if(chartcmd2.equalsIgnoreCase("no")) {
			chartcmd2="";
		}
		
		String idWorkflow = (String)prefs.getValue("idWorkflow", "no");

		if((idWorkflow == null)){
			idWorkflow="";
		}else if(idWorkflow.equalsIgnoreCase("no")) {
			idWorkflow="";
		}	
		portletInfoTexts.add("Workflow ID:");
		portletInfoValues.add(idWorkflow);
		portletInfoNames.add("idWorkflow");
		
		
		String idWorkflow2 = (String)prefs.getValue("idWorkflow2", "no");
		if((idWorkflow2 == null)){
			idWorkflow2="";
		}else if(idWorkflow2.equalsIgnoreCase("no")) {
			idWorkflow2="";
		}
		portletInfoTexts.add("Second Workflow ID:");
		portletInfoValues.add(idWorkflow2);
		portletInfoNames.add("idWorkflow2");
		
		String workflowName = (String)prefs.getValue("workflowName", "no");
		if((workflowName == null)){
			workflowName="";
		}else if(workflowName.equalsIgnoreCase("no")) {
			workflowName="";
		}
		portletInfoTexts.add("Workflow Name:");
		portletInfoValues.add(workflowName.replace(";"," "));
		portletInfoNames.add("workflowName");
		
		String workflowName2 = (String)prefs.getValue("workflowName2", "no");
		if((workflowName2 == null)){
			workflowName2="";
		}else if(workflowName2.equalsIgnoreCase("no")) {
			workflowName2="";
		}
		portletInfoTexts.add("Second Workflow Name:");
		portletInfoValues.add(workflowName2.replace(";", " "));
		portletInfoNames.add("workflowName2");
		
		String idPlugin = (String)prefs.getValue("idPlugin", "no");
		if((idPlugin == null)){
			idPlugin="";
		}else if(idPlugin.equalsIgnoreCase("no")) {
			idPlugin="";
		}
		portletInfoTexts.add("Plugin ID:");
		portletInfoValues.add(idPlugin);
		portletInfoNames.add("idPlugin");
		
		String idPlugin2 = (String)prefs.getValue("idPlugin2", "no");
		if((idPlugin2 == null)){
			idPlugin2="";
		}else if(idPlugin2.equalsIgnoreCase("no")) {
			idPlugin2="";
		}
		portletInfoTexts.add("Second Plugin ID:");
		portletInfoValues.add(idPlugin2);
		portletInfoNames.add("idPlugin2");
		
		String pluginName = (String)prefs.getValue("pluginName", "no");
		if((pluginName == null)){
			pluginName="";
		}else if(pluginName.equalsIgnoreCase("no")) {
			pluginName="";
		}
		portletInfoTexts.add("Plugin Name:");
		portletInfoValues.add(pluginName);
		portletInfoNames.add("pluginName");
		
		String pluginName2 = (String)prefs.getValue("pluginName2", "no");
		if((pluginName2 == null)){
			pluginName2="";
		}else if(pluginName2.equalsIgnoreCase("no")) {
			pluginName2="";
		}
		portletInfoTexts.add("Second Plugin Name:");
		portletInfoValues.add(pluginName2);
		portletInfoNames.add("pluginName2");
		
		String idQuery = (String)prefs.getValue("idQuery", "no");
		if((idQuery == null)){
			idQuery="";
		}else if(idQuery.equalsIgnoreCase("no")) {
			idQuery="";
		}
		portletInfoTexts.add("Query ID:");
		portletInfoValues.add(idQuery);
		portletInfoNames.add("idQuery");
		
		String idQuery2 = (String)prefs.getValue("idQuery2", "no");
		if((idQuery2 == null)){
			idQuery2="";
		}else if(idQuery2.equalsIgnoreCase("no")) {
			idQuery2="";
		}
		portletInfoTexts.add("Second Query ID:");
		portletInfoValues.add(idQuery2);
		portletInfoNames.add("idQuery2");
		
		String queryName = (String)prefs.getValue("queryName", "no");
		if((queryName == null)){
			queryName="";
		}else if(queryName.equalsIgnoreCase("no")) {
			queryName="";
		}
		portletInfoTexts.add("Query Name:");
		portletInfoValues.add(queryName);
		portletInfoNames.add("queryName");
		
		String queryName2 = (String)prefs.getValue("queryName2", "no");
		if((queryName2 == null)){
			queryName2="";
		}else if(queryName2.equalsIgnoreCase("no")) {
			queryName2="";
		}
		portletInfoTexts.add("Second Query Name:");
		portletInfoValues.add(queryName2);
		portletInfoNames.add("queryName2");
		
		String idMetric = (String)prefs.getValue("idMetric", "no");
		if((idMetric == null)){
			idMetric="";
		}else if(idMetric.equalsIgnoreCase("no")) {
			idMetric="";
		}
		portletInfoTexts.add("Metric ID:");
		portletInfoValues.add(idMetric);
		portletInfoNames.add("idMetric");
		
		String idMetric2 = (String)prefs.getValue("idMetric2", "no");
		if((idMetric2 == null)){
			idMetric2="";
		}else if(idMetric2.equalsIgnoreCase("no")) {
			idMetric2="";
		}
		portletInfoTexts.add("Second Metric ID:");
		portletInfoValues.add(idMetric2);
		portletInfoNames.add("idMetric2");
		
		String mysqlQuery = (String)prefs.getValue("mysqlQuery", "no");
		if((mysqlQuery == null)){
			mysqlQuery="";
		}else if (mysqlQuery.equalsIgnoreCase("no")) {
			mysqlQuery="";
		}
		
		String mysqlQuery2 = (String)prefs.getValue("mysqlQuery2", "no");
		if((mysqlQuery2 == null)){
			mysqlQuery2="";
		}else if (mysqlQuery2.equalsIgnoreCase("no")) {
			mysqlQuery2="";
		}
		
		String groupOrderLimitClauses = (String)prefs.getValue("groupOrderLimitClauses", "no");
		if((groupOrderLimitClauses == null)){
			groupOrderLimitClauses="";
		}else if (groupOrderLimitClauses.equalsIgnoreCase("no")) {
			groupOrderLimitClauses="";
		}
		
		String groupOrderLimitClauses2 = (String)prefs.getValue("groupOrderLimitClauses2", "no");
		if((groupOrderLimitClauses2 == null)){
			groupOrderLimitClauses2="";
		}else if (groupOrderLimitClauses2.equalsIgnoreCase("no")) {
			groupOrderLimitClauses2="";
		}	
		
		String idSystem = (String)prefs.getValue("idSystem", "no");
		if((idSystem == null)){
			idSystem="";
		}else if(idSystem.equalsIgnoreCase("no")) { 
			idSystem="";
		}
		portletInfoTexts.add("System ID:");
		portletInfoValues.add(idSystem);
		portletInfoNames.add("idSystem");
		
		String idSystem2 = (String)prefs.getValue("idSystem2", "no");
		if((idSystem2 == null)){
			idSystem2="";
		}else if(idSystem2.equalsIgnoreCase("no")) { 
			idSystem2="";
		}
		portletInfoTexts.add("Second System ID:");
		portletInfoValues.add(idSystem2);
		portletInfoNames.add("idSystem2");
		
		String systemName = (String)prefs.getValue("systemName", "no");
		if((systemName == null)){
			systemName="";
		}else if(systemName.equalsIgnoreCase("no")) { 
			systemName="";
		}
		portletInfoTexts.add("System Name:");
		portletInfoValues.add(systemName);
		portletInfoNames.add("systemName");
		
		String systemName2 = (String)prefs.getValue("systemName2", "no");
		if((systemName2 == null)){
			systemName2="";
		}else if(systemName2.equalsIgnoreCase("no")) { 
			systemName2="";
		}
		portletInfoTexts.add("Second System Name:");
		portletInfoValues.add(systemName2);
		portletInfoNames.add("systemName2");
		
		String idPlatform = (String)prefs.getValue("idPlatform", "no");
		if((idPlatform == null)){
			idPlatform="";
		}else if(idPlatform.equalsIgnoreCase("no")) { 
			idPlatform="";
		}
		portletInfoTexts.add("Platform ID:");
		portletInfoValues.add(idPlatform);
		portletInfoNames.add("idPlatform");
		
		String idPlatform2 = (String)prefs.getValue("idPlatform2", "no");
		if((idPlatform2 == null)){
			idPlatform2="";
		}else if(idPlatform2.equalsIgnoreCase("no")) { 
			idPlatform2="";
		}
		portletInfoTexts.add("Second Platform ID:");
		portletInfoValues.add(idPlatform2);
		portletInfoNames.add("idPlatform2");
		
		String platformName = (String)prefs.getValue("platformName", "no");
		if((platformName == null)){
			platformName="";
		}else if(platformName.equalsIgnoreCase("no")) { 
			platformName="";
		}
		portletInfoTexts.add("Platform Name:");
		portletInfoValues.add(platformName);
		portletInfoNames.add("platformName");
		
		String platformName2 = (String)prefs.getValue("platformName2", "no");
		if((platformName2 == null)){
			platformName2="";
		}else if(platformName2.equalsIgnoreCase("no")) { 
			platformName2="";
		}
		portletInfoTexts.add("Second Platform Name:");
		portletInfoValues.add(platformName2);
		portletInfoNames.add("platformName2");
		
		String appName = (String)prefs.getValue("appName", "no");
		if((appName == null)){
			appName="";
		}else if(appName.equalsIgnoreCase("no")) { 
			appName="";
		}
		portletInfoTexts.add("Application Name:");
		portletInfoValues.add(appName);
		portletInfoNames.add("appName");
		
		String appName2 = (String)prefs.getValue("appName2", "no");
		if((appName2 == null)){
			appName2="";
		}else if(appName2.equalsIgnoreCase("no")) { 
			appName2="";
		}
		portletInfoTexts.add("Second Application Name:");
		portletInfoValues.add(appName2);
		portletInfoNames.add("appName2");
		
		String refreshTime = (String)prefs.getValue("refreshTime", "no");
		if((refreshTime == null)){
			refreshTime="";
		}else if(refreshTime.equalsIgnoreCase("no")) { 
			refreshTime="";
		} 
		
		String preselectedPeriod = (String)prefs.getValue("preselectedPeriod", "no");
		if((preselectedPeriod == null)){
			preselectedPeriod="";
		}else if(preselectedPeriod.equalsIgnoreCase("no")) { 
			preselectedPeriod="";
		}

		String liveMode = (String)prefs.getValue("liveMode", "no");
		if((liveMode == null)){
			liveMode="";
		}else if(liveMode.equalsIgnoreCase("no")) { 
			liveMode="";
		}
		
		String lineChartResolution = (String)prefs.getValue("lineChartResolution", "no");
		if((lineChartResolution == null)){
			lineChartResolution="";
		}else if(lineChartResolution.equalsIgnoreCase("no")) { 
			lineChartResolution="";
		}
		
		String lineChartAggregateMethod = (String)prefs.getValue("lineChartAggregateMethod", "no");
		if((lineChartAggregateMethod == null)){
			lineChartAggregateMethod="";
		}else if(lineChartAggregateMethod.equalsIgnoreCase("no")) { 
			lineChartAggregateMethod="";
		}
		
		String infoMode = (String)prefs.getValue("infoMode", "no");
		if((infoMode == null)){
			infoMode="";
		}else if(infoMode.equalsIgnoreCase("no")) { 
			infoMode="";
		}
		
		String interpolateMode = (String)prefs.getValue("interpolateMode", "no");
		if((interpolateMode == null)){
			interpolateMode="";
		}else if(interpolateMode.equalsIgnoreCase("no")) { 
			interpolateMode="";
		}
		
		
	
		renderRequest.setAttribute("charttype", charttype);
		
		renderRequest.setAttribute("chartcmd", chartcmd);
		renderRequest.setAttribute("chartcmd2", chartcmd2);
		
		renderRequest.setAttribute("idWorkflow", idWorkflow);
		renderRequest.setAttribute("idWorkflow2", idWorkflow2);
		
		renderRequest.setAttribute("workflowName", workflowName);
		renderRequest.setAttribute("workflowName2", workflowName2);
		
		renderRequest.setAttribute("idPlugin", idPlugin);
		renderRequest.setAttribute("idPlugin2", idPlugin2);
		
		renderRequest.setAttribute("pluginName", pluginName);
		renderRequest.setAttribute("pluginName2", pluginName2);
		
		renderRequest.setAttribute("idQuery", idQuery);
		renderRequest.setAttribute("idQuery2", idQuery2);
		
		renderRequest.setAttribute("queryName", queryName);
		renderRequest.setAttribute("queryName2", queryName2);
		
		renderRequest.setAttribute("idMetric", idMetric);
		renderRequest.setAttribute("idMetric2", idMetric2);
		
		renderRequest.setAttribute("metricName", metricName);
		renderRequest.setAttribute("metricName2", metricName2);	
		
		renderRequest.setAttribute("mysqlQuery", mysqlQuery);
		renderRequest.setAttribute("mysqlQuery2", mysqlQuery2);
		
		renderRequest.setAttribute("groupOrderLimitClauses", groupOrderLimitClauses);
		renderRequest.setAttribute("groupOrderLimitClauses2", groupOrderLimitClauses2);
		
		renderRequest.setAttribute("platformLevel", platformLevel);
		renderRequest.setAttribute("platformLevel2", platformLevel2);	
		
		renderRequest.setAttribute("idSystem", idSystem);
		renderRequest.setAttribute("idSystem2", idSystem2);
		
		renderRequest.setAttribute("systemName", systemName);
		renderRequest.setAttribute("systemName2", systemName2);
		
		renderRequest.setAttribute("idPlatform", idPlatform);
		renderRequest.setAttribute("idPlatform2", idPlatform2);
		
		renderRequest.setAttribute("platformName", platformName);
		renderRequest.setAttribute("platformName2", platformName2);
		
		renderRequest.setAttribute("appName", appName);
		renderRequest.setAttribute("appName2", appName2);
		
		renderRequest.setAttribute("portletInfoTexts", portletInfoTexts);
		renderRequest.setAttribute("portletInfoValues", portletInfoValues);
		renderRequest.setAttribute("portletInfoNames", portletInfoNames);
	
		renderRequest.setAttribute("liveMode", liveMode);
		renderRequest.setAttribute("refreshTime", refreshTime);
		renderRequest.setAttribute("preselectedPeriod", preselectedPeriod);	
		renderRequest.setAttribute("interpolateMode", interpolateMode);
		
		renderRequest.setAttribute("infoMode", infoMode);

		
		renderRequest.setAttribute("lineChartResolution", lineChartResolution);
		renderRequest.setAttribute("lineChartAggregateMethod", lineChartAggregateMethod);
		
		include(viewJSP, renderRequest, renderResponse);
	}

	/* Render Edit Mode */
	public void doEdit(RenderRequest renderRequest,
			RenderResponse renderResponse)
	throws IOException, PortletException {
		renderResponse.setContentType("text/html");
		PortletURL editPortlet = renderResponse.createActionURL();
		editPortlet.setParameter("editPortlet", "editPortlet");
		
PortletPreferences prefs = renderRequest.getPreferences();
		
		String charttype = (String)prefs.getValue("charttype", "no");
		if((charttype == null)){
			charttype="";
		}else if (charttype.equalsIgnoreCase("no")) {
			charttype="";
		}
		
		String chartcmd = (String)prefs.getValue("chartcmd", "no");
		if((chartcmd == null)){
			chartcmd="";
		}else if(chartcmd.equalsIgnoreCase("no")) {
			chartcmd="";
		}
		
		String chartcmd2 = (String)prefs.getValue("chartcmd2", "no");
		if((chartcmd2 == null)){
			chartcmd2="";
		}else if(chartcmd2.equalsIgnoreCase("no")) {
			chartcmd2="";
		}
		
		String idWorkflow = (String)prefs.getValue("idWorkflow", "no");
		if((idWorkflow == null)){
			idWorkflow="";
		}else if(idWorkflow.equalsIgnoreCase("no")) {
			idWorkflow="";
		}
		
		String idWorkflow2 = (String)prefs.getValue("idWorkflow2", "no");
		if((idWorkflow2 == null)){
			idWorkflow2="";
		}else if(idWorkflow2.equalsIgnoreCase("no")) {
			idWorkflow2="";
		}
		
		String workflowName = (String)prefs.getValue("workflowName", "no");
		if((workflowName == null)){
			workflowName="";
		}else if(workflowName.equalsIgnoreCase("no")) {
			workflowName="";
		}
		
		String workflowName2 = (String)prefs.getValue("workflowName2", "no");
		if((workflowName2 == null)){
			workflowName2="";
		}else if(workflowName2.equalsIgnoreCase("no")) {
			workflowName2="";
		}
		
		String idPlugin = (String)prefs.getValue("idPlugin", "no");
		if((idPlugin == null)){
			idPlugin="";
		}else if(idPlugin.equalsIgnoreCase("no")) {
			idPlugin="";
		}
		
		String idPlugin2 = (String)prefs.getValue("idPlugin2", "no");
		if((idPlugin2 == null)){
			idPlugin2="";
		}else if(idPlugin2.equalsIgnoreCase("no")) {
			idPlugin2="";
		}
		
		String pluginName = (String)prefs.getValue("pluginName", "no");
		if((pluginName == null)){
			pluginName="";
		}else if(pluginName.equalsIgnoreCase("no")) {
			pluginName="";
		}
		
		String pluginName2 = (String)prefs.getValue("pluginName2", "no");
		if((pluginName2 == null)){
			pluginName2="";
		}else if(pluginName2.equalsIgnoreCase("no")) {
			pluginName2="";
		}
		
		String idQuery = (String)prefs.getValue("idQuery", "no");
		if((idQuery == null)){
			idQuery="";
		}else if(idQuery.equalsIgnoreCase("no")) {
			idQuery="";
		}
		
		String idQuery2 = (String)prefs.getValue("idQuery2", "no");
		if((idQuery2 == null)){
			idQuery2="";
		}else if(idQuery2.equalsIgnoreCase("no")) {
			idQuery2="";
		}
		
		String queryName = (String)prefs.getValue("queryName", "no");
		if((queryName == null)){
			queryName="";
		}else if(queryName.equalsIgnoreCase("no")) {
			queryName="";
		}
		
		String queryName2 = (String)prefs.getValue("queryName2", "no");
		if((queryName2 == null)){
			queryName2="";
		}else if(queryName2.equalsIgnoreCase("no")) {
			queryName2="";
		}
		
		String idMetric = (String)prefs.getValue("idMetric", "no");
		if((idMetric == null)){
			idMetric="";
		}else if(idMetric.equalsIgnoreCase("no")) {
			idMetric="";
		}
		
		String idMetric2 = (String)prefs.getValue("idMetric2", "no");
		if((idMetric2 == null)){
			idMetric2="";
		}else if(idMetric2.equalsIgnoreCase("no")) {
			idMetric2="";
		}
		
		String mysqlQuery = (String)prefs.getValue("mysqlQuery", "no");
		if((mysqlQuery == null)){
			mysqlQuery="";
		}else if (mysqlQuery.equalsIgnoreCase("no")) {
			mysqlQuery="";
		}
		
		String mysqlQuery2 = (String)prefs.getValue("mysqlQuery2", "no");
		if((mysqlQuery2 == null)){
			mysqlQuery2="";
		}else if (mysqlQuery2.equalsIgnoreCase("no")) {
			mysqlQuery2="";
		}
		
		String groupOrderLimitClauses = (String)prefs.getValue("groupOrderLimitClauses", "no");
		if((groupOrderLimitClauses == null)){
			groupOrderLimitClauses="";
		}else if (groupOrderLimitClauses.equalsIgnoreCase("no")) {
			groupOrderLimitClauses="";
		}
		
		String groupOrderLimitClauses2 = (String)prefs.getValue("groupOrderLimitClauses2", "no");
		if((groupOrderLimitClauses2 == null)){
			groupOrderLimitClauses2="";
		}else if (groupOrderLimitClauses2.equalsIgnoreCase("no")) {
			groupOrderLimitClauses2="";
		}	
		
		String platformLevel = (String)prefs.getValue("platformLevel", "no");
		if((platformLevel == null)){
			platformLevel="";
		}else if(platformLevel.equalsIgnoreCase("no")) { 
			platformLevel="";
		}
		
		String platformLevel2 = (String)prefs.getValue("platformLevel2", "no");
		if((platformLevel2 == null)){
			platformLevel2="";
		}else if(platformLevel2.equalsIgnoreCase("no")) { 
			platformLevel2="";
		}
		
		String metricName = (String)prefs.getValue("metricName", "no");
		if((metricName == null)){
			metricName="";
		}else if(metricName.equalsIgnoreCase("no")) { 
			metricName="";
		}
		
		String metricName2 = (String)prefs.getValue("metricName2", "no");
		if((metricName2 == null)){
			metricName2="";
		}else if(metricName2.equalsIgnoreCase("no")) { 
			metricName2="";
		}
		
		String idSystem = (String)prefs.getValue("idSystem", "no");
		if((idSystem == null)){
			idSystem="";
		}else if(idSystem.equalsIgnoreCase("no")) { 
			idSystem="";
		}
		
		String idSystem2 = (String)prefs.getValue("idSystem2", "no");
		if((idSystem2 == null)){
			idSystem2="";
		}else if(idSystem2.equalsIgnoreCase("no")) { 
			idSystem2="";
		}
		
		String systemName = (String)prefs.getValue("systemName", "no");
		if((systemName == null)){
			systemName="";
		}else if(systemName.equalsIgnoreCase("no")) { 
			systemName="";
		}
		
		String systemName2 = (String)prefs.getValue("systemName2", "no");
		if((systemName2 == null)){
			systemName2="";
		}else if(systemName2.equalsIgnoreCase("no")) { 
			systemName2="";
		}
		
		String idPlatform = (String)prefs.getValue("idPlatform", "no");
		if((idPlatform == null)){
			idPlatform="";
		}else if(idPlatform.equalsIgnoreCase("no")) { 
			idPlatform="";
		}
		
		String idPlatform2 = (String)prefs.getValue("idPlatform2", "no");
		if((idPlatform2 == null)){
			idPlatform2="";
		}else if(idPlatform2.equalsIgnoreCase("no")) { 
			idPlatform2="";
		}
		
		String platformName = (String)prefs.getValue("platformName", "no");
		if((platformName == null)){
			platformName="";
		}else if(platformName.equalsIgnoreCase("no")) { 
			platformName="";
		}
		
		String platformName2 = (String)prefs.getValue("platformName2", "no");
		if((platformName2 == null)){
			platformName2="";
		}else if(platformName2.equalsIgnoreCase("no")) { 
			platformName2="";
		}
		
		String appName = (String)prefs.getValue("appName", "no");
		if((appName == null)){
			appName="";
		}else if(appName.equalsIgnoreCase("no")) { 
			appName="";
		}
		
		String appName2 = (String)prefs.getValue("appName2", "no");
		if((appName2 == null)){
			appName2="";
		}else if(appName2.equalsIgnoreCase("no")) { 
			appName2="";
		}
		
		
		String refreshTime = (String)prefs.getValue("refreshTime", "no");
		if((refreshTime == null)){
			refreshTime="";
		}else if(refreshTime.equalsIgnoreCase("no")) { 
			refreshTime="";
		}
		
		String preselectedPeriod = (String)prefs.getValue("preselectedPeriod", "no");
		if((preselectedPeriod == null)){
			preselectedPeriod="";
		}else if(preselectedPeriod.equalsIgnoreCase("no")) { 
			preselectedPeriod="";
		}
		
		String liveMode = (String)prefs.getValue("liveMode", "no");
		if((liveMode == null)){
			liveMode="";
		}else if(liveMode.equalsIgnoreCase("no")) { 
			liveMode="";
		}
		
		String lineChartResolution = (String)prefs.getValue("lineChartResolution", "no");
		if((lineChartResolution == null)){
			lineChartResolution="";
		}else if(lineChartResolution.equalsIgnoreCase("no")) { 
			lineChartResolution="";
		}
		
		String lineChartAggregateMethod = (String)prefs.getValue("lineChartAggregateMethod", "no");
		if((lineChartAggregateMethod == null)){
			lineChartAggregateMethod="";
		}else if(lineChartAggregateMethod.equalsIgnoreCase("no")) { 
			lineChartAggregateMethod="";
		}
		
		String infoMode = (String)prefs.getValue("infoMode", "no");
		if((infoMode == null)){
			infoMode="";
		}else if(infoMode.equalsIgnoreCase("no")) { 
			infoMode="";
		}
		
		String interpolateMode = (String)prefs.getValue("interpolateMode", "no");
		if((interpolateMode == null)){
			interpolateMode="";
		}else if(interpolateMode.equalsIgnoreCase("no")) { 
			interpolateMode="";
		}
		
		renderRequest.setAttribute("charttype", charttype);
		
		renderRequest.setAttribute("chartcmd", chartcmd);
		renderRequest.setAttribute("chartcmd2", chartcmd2);
		
		renderRequest.setAttribute("idWorkflow", idWorkflow);
		renderRequest.setAttribute("idWorkflow2", idWorkflow2);
		
		renderRequest.setAttribute("workflowName", workflowName);
		renderRequest.setAttribute("workflowName2", workflowName2);
		
		renderRequest.setAttribute("idPlugin", idPlugin);
		renderRequest.setAttribute("idPlugin2", idPlugin2);
		
		renderRequest.setAttribute("pluginName", pluginName);
		renderRequest.setAttribute("pluginName2", pluginName2);
		
		renderRequest.setAttribute("idQuery", idQuery);
		renderRequest.setAttribute("idQuery2", idQuery2);
		
		renderRequest.setAttribute("queryName", queryName);
		renderRequest.setAttribute("queryName2", queryName2);
		
		renderRequest.setAttribute("idMetric", idMetric);
		renderRequest.setAttribute("idMetric2", idMetric2);
		
		renderRequest.setAttribute("metricName", metricName);
		renderRequest.setAttribute("metricName2", metricName2);	
		
		renderRequest.setAttribute("mysqlQuery", mysqlQuery);
		renderRequest.setAttribute("mysqlQuery2", mysqlQuery2);
		
		renderRequest.setAttribute("groupOrderLimitClauses", groupOrderLimitClauses);
		renderRequest.setAttribute("groupOrderLimitClauses2", groupOrderLimitClauses2);
		
		renderRequest.setAttribute("platformLevel", platformLevel);
		renderRequest.setAttribute("platformLevel2", platformLevel2);	
		
		renderRequest.setAttribute("idSystem", idSystem);
		renderRequest.setAttribute("idSystem2", idSystem2);
		
		renderRequest.setAttribute("systemName", systemName);
		renderRequest.setAttribute("systemName2", systemName2);
		
		renderRequest.setAttribute("idPlatform", idPlatform);
		renderRequest.setAttribute("idPlatform2", idPlatform2);
		
		renderRequest.setAttribute("platformName", platformName);
		renderRequest.setAttribute("platformName2", platformName2);
		
		renderRequest.setAttribute("appName", appName);
		renderRequest.setAttribute("appName2", appName2);
		
		renderRequest.setAttribute("refreshTime", refreshTime);
		renderRequest.setAttribute("preselectedPeriod", preselectedPeriod);
		renderRequest.setAttribute("liveMode", liveMode);
		
		renderRequest.setAttribute("lineChartResolution", lineChartResolution);
		
		renderRequest.setAttribute("infoMode", infoMode);
		
		renderRequest.setAttribute("lineChartAggregateMethod", lineChartAggregateMethod);
		
		renderRequest.setAttribute("interpolateMode", interpolateMode);
		
		renderRequest.setAttribute("editPortletUrl", editPortlet.toString());
		
		include(editJSP, renderRequest, renderResponse);
	}
	
	/* Executes after pressing submit button */
	public void processAction(
			ActionRequest actionRequest,
			ActionResponse actionResponse)
	throws IOException, PortletException {
		String editPortlet = actionRequest.getParameter("editPortlet");
		if (editPortlet != null) {
			PortletPreferences prefs = actionRequest.getPreferences();
			
			prefs.setValue(	"charttype", actionRequest.getParameter("chartTypeSelect"));
			
			prefs.setValue(	"chartcmd", actionRequest.getParameter("chartcmd"));
			prefs.setValue(	"chartcmd2", actionRequest.getParameter("chartcmd2"));
			
			prefs.setValue(	"idSystem", actionRequest.getParameter("idSystem"));
			prefs.setValue(	"idSystem2", actionRequest.getParameter("idSystem2"));
			
			prefs.setValue(	"systemName", actionRequest.getParameter("systemName"));
			prefs.setValue(	"systemName2", actionRequest.getParameter("systemName2"));
			
			prefs.setValue(	"idPlatform", actionRequest.getParameter("idPlatform"));
			prefs.setValue(	"idPlatform2", actionRequest.getParameter("idPlatform2"));
			
			prefs.setValue(	"platformName", actionRequest.getParameter("platformName"));
			prefs.setValue(	"platformName2", actionRequest.getParameter("platformName2"));
			
			prefs.setValue(	"idWorkflow", actionRequest.getParameter("idWorkflow"));
			prefs.setValue(	"idWorkflow2", actionRequest.getParameter("idWorkflow2"));
			
			prefs.setValue(	"workflowName", actionRequest.getParameter("workflowName"));
			prefs.setValue(	"workflowName2", actionRequest.getParameter("workflowName2"));
			
			prefs.setValue(	"idPlugin", actionRequest.getParameter("idPlugin"));
			prefs.setValue(	"idPlugin2", actionRequest.getParameter("idPlugin2"));
			
			prefs.setValue(	"pluginName", actionRequest.getParameter("pluginName"));
			prefs.setValue(	"pluginName2", actionRequest.getParameter("pluginName2"));
			
			prefs.setValue(	"idQuery", actionRequest.getParameter("idQuery"));
			prefs.setValue(	"idQuery2", actionRequest.getParameter("idQuery2"));
			
			prefs.setValue(	"queryName", actionRequest.getParameter("queryName"));
			prefs.setValue(	"queryName2", actionRequest.getParameter("queryName2"));
			
			prefs.setValue(	"idMetric", actionRequest.getParameter("idMetric"));
			prefs.setValue(	"idMetric2", actionRequest.getParameter("idMetric2"));
			
			prefs.setValue(	"metricName", actionRequest.getParameter("metricSelect"));
			prefs.setValue(	"metricName2", actionRequest.getParameter("metricSelect2"));

			prefs.setValue(	"appName", actionRequest.getParameter("appName"));
			prefs.setValue(	"appName2", actionRequest.getParameter("appName2"));
			
			prefs.setValue(	"platformLevel", actionRequest.getParameter("visLevelSelect"));
			prefs.setValue(	"platformLevel2", actionRequest.getParameter("visLevelSelect2"));
			
			prefs.setValue(	"mysqlQuery", actionRequest.getParameter("mysqlQuery"));
			prefs.setValue(	"mysqlQuery2", actionRequest.getParameter("mysqlQuery2"));
			
			prefs.setValue(	"groupOrderLimitClauses", actionRequest.getParameter("groupOrderLimitClauses"));
			prefs.setValue(	"groupOrderLimitClauses2", actionRequest.getParameter("groupOrderLimitClauses2"));
			
			prefs.setValue(	"liveMode", actionRequest.getParameter("liveMode"));
			prefs.setValue(	"refreshTime", actionRequest.getParameter("visRefreshTimeSelect"));
			prefs.setValue(	"preselectedPeriod", actionRequest.getParameter("visPreselectedPeriodSelect"));
			
			prefs.setValue(	"lineChartResolution", actionRequest.getParameter("lineChartResolution"));
				
			prefs.setValue("infoMode", actionRequest.getParameter("infoMode"));
			
			prefs.setValue("lineChartAggregateMethod", actionRequest.getParameter("aggregateMethodSelect"));
			
			prefs.setValue("interpolateMode", actionRequest.getParameter("interpolateMode"));
			
			prefs.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
		} 
	}
	
	

}
