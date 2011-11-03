package ro.utcluj.larkc.mysqlmetrics.client;

import java.util.ArrayList;
import java.util.Map;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/*
 * function PortletInstance(mysqlQuery, charttype, rederURL, actionURL, resourceURL, chartcmd, idWorkflow, idPlugin, idMetric, portletID) {

	this.mysqlQuery = mysqlQuery;
	this.charttype = charttype;
    this.rederURL = rederURL;
    this.actionURL = actionURL;
    this.resourceURL = resourceURL;        
    this.chartcmd = chartcmd;
    this.idWorkflow = idWorkflow;
    this.idPlugin = idPlugin;
    this.idMetric = idMetric;
    this.portletID = portletID;

chartcmd='<%=chartcmd %>';
	idWorkflow='<%=idWorkflow %>';
	idPlugin='<%=idPlugin %>';
	idMetric='<%=idMetric %>';
	portletID='<portlet:namespace />';
	mysqlQuery='<%=mysqlQuery%>';
	charttype='<%=charttype%>';
}
 */

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vis_mysql_metrics implements EntryPoint {
	
	/*
	 * Initializing the url of JSON service
	 */
	private static final String JSON_URL = "http://localhost:8080/vis-mysql-metrics-chart-portlet/MetricServlet?chartcmd=getworkflowmetrics&idWorkflow=2aaad516-a8ef-4511-8ffb-3e5d076f3d1a&idMetric=29";

	
	
	public static native JsArray<JsPortletInstance> getPortletInstances()
	/*-{
	    return $wnd.portletInstances;
	}-*/;

		

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		//JsPortletInstance instance1 = null;
		//new MySQLMetrics(null);
		
		JsArray<JsPortletInstance> portletInstances = getPortletInstances();
		for (int i = 0; i < portletInstances.length(); i++) {
			JsPortletInstance instance = portletInstances.get(i);
						
			GWT.log(instance.toString()); 
			new MySQLMetrics(instance);
		}
		
		
	}
}
