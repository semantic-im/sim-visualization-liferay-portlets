package com.liferay.portlet;

import java.io.IOException;
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

public class MetricsTablePortlet extends GenericPortlet {
	
	public static String dbName;
	public static String userName;
	public static String password;
	
	protected String editJSP;
	protected String viewJSP;
	
	private static Log _log = LogFactory.getLog(MetricsTablePortlet.class);
	
	public void init() throws PortletException {
		editJSP = getInitParameter("edit-jsp");
		viewJSP = getInitParameter("view-jsp");
		dbName = getInitParameter("dbName");
		userName = getInitParameter("userName");
		password = getInitParameter("password");
	}

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
		PortletPreferences prefs = renderRequest.getPreferences();
		
		String useQuery = (String)prefs.getValue("useQuery", "xyz");
		if((useQuery == null || useQuery.equalsIgnoreCase("xyz"))) {
			useQuery="no";
		}
		
		String mysqlQuery = (String)prefs.getValue("mysqlQuery", "xyz");
		if((mysqlQuery == null || mysqlQuery.equalsIgnoreCase("xyz"))) {
			mysqlQuery="";
		}

		String tableName = (String)prefs.getValue("tableName", "xyz");
		if(tableName == null || tableName.equalsIgnoreCase("xyz")) {
			tableName = "Platforms";
		}
		
		String metricName = (String)prefs.getValue("metricName", "xyz");
		if(metricName == null || metricName.equalsIgnoreCase("xyz")) {
			metricName = "";
		}
		
		String useMetrics = (String)prefs.getValue("useMetrics", "xyz");
		if(useMetrics == null || useMetrics.equalsIgnoreCase("xyz")) {
			useMetrics = "Context";
		}
		
		String pageSize = (String)prefs.getValue("pageSize", "xyz");
		if(pageSize == null || pageSize.equalsIgnoreCase("xyz")) {
			pageSize = "";
		}
		
		String limit = (String)prefs.getValue("limit", "xyz");
		if(limit == null || limit.equalsIgnoreCase("xyz")) {
			limit = "100";
		}
		
		String chartCmd = (String)prefs.getValue("chartCmd", "xyz");
		if(chartCmd == null || chartCmd.equalsIgnoreCase("xyz")) {
			chartCmd = "";
		}
		
		String targetPage = (String)prefs.getValue("targetPage", "xyz");
		if(targetPage == null || targetPage.equalsIgnoreCase("xyz")) {
			targetPage = "";
		}
		
		String paramCol = (String)prefs.getValue("paramCol", "xyz");
		if((paramCol == null || paramCol.equalsIgnoreCase("xyz"))) {
			paramCol="";
		}
		
		String paramName = (String)prefs.getValue("paramName", "xyz");
		if((paramName == null || paramName.equalsIgnoreCase("xyz"))) {
			paramName="";
		}
		
		String paramName1 = (String)prefs.getValue("paramName1", "xyz");
		if((paramName1 == null || paramName1.equalsIgnoreCase("xyz"))) {
			paramName1="";
		}
		
		String paramName2 = (String)prefs.getValue("paramName2", "xyz");
		if((paramName2 == null || paramName2.equalsIgnoreCase("xyz"))) {
			paramName2="";
		}
		
		String paramVal1 = (String)prefs.getValue("paramVal1", "xyz");
		if((paramVal1 == null || paramVal1.equalsIgnoreCase("xyz"))) {
			paramVal1="";
		}
		
		String paramVal2 = (String)prefs.getValue("paramVal2", "xyz");
		if((paramVal2 == null || paramVal2.equalsIgnoreCase("xyz"))) {
			paramVal2="";
		}
		
		String clearURLParameters = (String)prefs.getValue("clearURLParameters", "xyz");
		if((clearURLParameters == null || clearURLParameters.equalsIgnoreCase("xyz"))) {
			clearURLParameters="";
		}
				
		renderRequest.setAttribute("useQuery", useQuery);
		renderRequest.setAttribute("mysqlQuery", mysqlQuery);
		renderRequest.setAttribute("tableName", tableName);
		renderRequest.setAttribute("metricName", metricName);
		renderRequest.setAttribute("useMetrics", useMetrics);
		renderRequest.setAttribute("pageSize", pageSize);
		renderRequest.setAttribute("limit", limit);
		renderRequest.setAttribute("chartCmd", chartCmd);
		renderRequest.setAttribute("targetPage", targetPage);
		
		renderRequest.setAttribute("paramCol", paramCol);
		renderRequest.setAttribute("paramName", paramName);
		
		renderRequest.setAttribute("paramName1", paramName1);
		renderRequest.setAttribute("paramName2", paramName2);
		renderRequest.setAttribute("paramVal1", paramVal1);
		renderRequest.setAttribute("paramVal2", paramVal2);
				
		renderRequest.setAttribute("clearURLParameters", clearURLParameters);
								
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
		
		String useQuery = (String)prefs.getValue("useQuery", "xyz");
		if((useQuery == null || useQuery.equalsIgnoreCase("xyz"))) {
			useQuery="no";
		}
		
		String mysqlQuery = (String)prefs.getValue("mysqlQuery", "xyz");
		if((mysqlQuery == null || mysqlQuery.equalsIgnoreCase("xyz"))) {
			mysqlQuery="";
		}

		String tableName = (String)prefs.getValue("tableName", "xyz");
		if(tableName == null || tableName.equalsIgnoreCase("xyz")) {
			tableName = "Platforms";
		}
		
		String metricName = (String)prefs.getValue("metricName", "xyz");
		if(metricName == null || metricName.equalsIgnoreCase("xyz")) {
			metricName = "";
		}
		
		String useMetrics = (String)prefs.getValue("useMetrics", "xyz");
		if(useMetrics == null || useMetrics.equalsIgnoreCase("xyz")) {
			useMetrics = "Context";
		}
		
		String pageSize = (String)prefs.getValue("pageSize", "xyz");
		if(pageSize == null || pageSize.equalsIgnoreCase("xyz")) {
			pageSize = "";
		}
		
		String limit = (String)prefs.getValue("limit", "xyz");
		if(limit == null || limit.equalsIgnoreCase("xyz")) {
			limit = "100";
		}
		
		String chartCmd = (String)prefs.getValue("chartCmd", "xyz");
		if(chartCmd == null || chartCmd.equalsIgnoreCase("xyz")) {
			chartCmd = "";
		}
		
		String targetPage = (String)prefs.getValue("targetPage", "xyz");
		if(targetPage == null || targetPage.equalsIgnoreCase("xyz")) {
			targetPage = "";
		}
		
		String paramCol = (String)prefs.getValue("paramCol", "xyz");
		if((paramCol == null || paramCol.equalsIgnoreCase("xyz"))) {
			paramCol="";
		}
		
		String paramName = (String)prefs.getValue("paramName", "xyz");
		if((paramName == null || paramName.equalsIgnoreCase("xyz"))) {
			paramName="";
		}
		
		String paramName1 = (String)prefs.getValue("paramName1", "xyz");
		if((paramName1 == null || paramName1.equalsIgnoreCase("xyz"))) {
			paramName1="";
		}
		
		String paramName2 = (String)prefs.getValue("paramName2", "xyz");
		if((paramName2 == null || paramName2.equalsIgnoreCase("xyz"))) {
			paramName2="";
		}
		
		String paramVal1 = (String)prefs.getValue("paramVal1", "xyz");
		if((paramVal1 == null || paramVal1.equalsIgnoreCase("xyz"))) {
			paramVal1="";
		}
		
		String paramVal2 = (String)prefs.getValue("paramVal2", "xyz");
		if((paramVal2 == null || paramVal2.equalsIgnoreCase("xyz"))) {
			paramVal2="";
		}
		
		String clearURLParameters = (String)prefs.getValue("clearURLParameters", "xyz");
		if((clearURLParameters == null || clearURLParameters.equalsIgnoreCase("xyz"))) {
			clearURLParameters="";
		}
		
		renderRequest.setAttribute("useQuery", useQuery);
		renderRequest.setAttribute("mysqlQuery", mysqlQuery);
		renderRequest.setAttribute("tableName", tableName);
		renderRequest.setAttribute("metricName", metricName);
		renderRequest.setAttribute("useMetrics", useMetrics);
		renderRequest.setAttribute("pageSize", pageSize);
		renderRequest.setAttribute("limit", limit);
		renderRequest.setAttribute("chartCmd", chartCmd);
		renderRequest.setAttribute("targetPage", targetPage);
		
		renderRequest.setAttribute("paramCol", paramCol);
		renderRequest.setAttribute("paramName", paramName);
		
		renderRequest.setAttribute("paramName1", paramName1);
		renderRequest.setAttribute("paramName2", paramName2);
		renderRequest.setAttribute("paramVal1", paramVal1);
		renderRequest.setAttribute("paramVal2", paramVal2);
		
		renderRequest.setAttribute("clearURLParameters", clearURLParameters);
		
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
			
			prefs.setValue(	"useQuery", actionRequest.getParameter("useQuery"));
			prefs.setValue(	"mysqlQuery", actionRequest.getParameter("mysqlQuery"));
			prefs.setValue(	"tableName", actionRequest.getParameter("tableName"));
			prefs.setValue(	"metricName", actionRequest.getParameter("metricName"));
			prefs.setValue(	"useMetrics", actionRequest.getParameter("useMetrics"));
			prefs.setValue(	"pageSize", actionRequest.getParameter("pageSize"));
			prefs.setValue(	"limit", actionRequest.getParameter("limit"));
			prefs.setValue("chartCmd", actionRequest.getParameter("chartCmd"));
			prefs.setValue("targetPage", actionRequest.getParameter("targetPage"));
			
			prefs.setValue("paramCol", actionRequest.getParameter("paramCol"));
			prefs.setValue("paramName", actionRequest.getParameter("paramName"));
			
			prefs.setValue("paramName1", actionRequest.getParameter("paramName1"));
			prefs.setValue("paramName2", actionRequest.getParameter("paramName2"));
			prefs.setValue("paramVal1", actionRequest.getParameter("paramVal1"));
			prefs.setValue("paramVal2", actionRequest.getParameter("paramVal2"));
			
			prefs.setValue("clearURLParameters", actionRequest.getParameter("clearURLParameters"));
			
			prefs.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
		} 
	}

}
