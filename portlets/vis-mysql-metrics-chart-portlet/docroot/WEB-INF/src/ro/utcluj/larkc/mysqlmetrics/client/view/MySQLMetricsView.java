package ro.utcluj.larkc.mysqlmetrics.client.view;

import java.util.ArrayList;
import java.util.List;

import ro.utcluj.larkc.mysqlmetrics.client.charts.OfcgwtBarChart;
import ro.utcluj.larkc.mysqlmetrics.client.charts.OfcgwtLineChart;
import ro.utcluj.larkc.mysqlmetrics.client.charts.OfcgwtPieChart;
import ro.utcluj.larkc.mysqlmetrics.shared.MetricEntry;



import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class MySQLMetricsView extends Composite {
	
	HTML homeText = new HTML("<h3>System Metrics</h3>");
	public HTML errorText = new HTML("<h3 class=\"portlet-msg-error\"> Chart Cannot Be Represented</h3>");
	public HTML noData = new HTML("<h3 class=\"portlet-msg-info\"> No data</h3>");
	
	public OfcgwtLineChart ofcgwtLine;
	public OfcgwtPieChart ofcgwtPie;
	public OfcgwtBarChart ofcgwtBar;

	JsArray<MetricEntry> metricEntries = null;
	public VerticalPanel vp = null;
	HorizontalPanel hp = null;
	
	String chartType = null;
	public boolean hasTextMessage = false;
	
	
	/** Constructor */
	public MySQLMetricsView(String chartType){		
		
		this.chartType = chartType;
		vp = new VerticalPanel();
		hp = new HorizontalPanel();
		vp.addStyleName("MySQLMetricsChartVP");
		hp.addStyleName("MySQLMetricsChartHP");
		vp.setWidth("100%");
		vp.setHeight("250px");
		hp.setWidth("100%");
		
		//adding text messages to the vertical pannel
		vp.add(noData);
		vp.add(errorText);
		noData.setVisible(false);
		errorText.setVisible(false);
		
		initWidget(vp);
		if(chartType == null){
			return;
		}else if(chartType.equalsIgnoreCase("PieChart")||chartType.equalsIgnoreCase("[PieChart]")){
//			ofcgwtPie = new OfcgwtPieChart(hp);
//			ofcgwtPie.loadOfcgwtPieChart("Metrics",metricEntries);
		}else if(chartType.equalsIgnoreCase("BarChart")||chartType.equalsIgnoreCase("[BarChart]")){
//			ofcgwtBar = new OfcgwtBarChart(hp);
//			ofcgwtBar.loadOfcgwtBarChart("Bar chart", metricEntries);
		}else {
			ofcgwtLine = new OfcgwtLineChart(hp, "ChartTitle");	
		}
		
	}

	
	
	/*
	 * Initializing chart with the JSON Metrics
	 */
	public void loadData(JsArray<MetricEntry> metricEntries, String chartType, boolean isLive, int lineNumber){
		
			
		this.metricEntries = metricEntries;
	
		String metricName = metricEntries.get(0).getMetricName();
		
		
		if((metricName==null)||
		   (metricName.isEmpty())||
		   (metricName.compareToIgnoreCase("undefined") == 0)){
			metricName = metricEntries.get(0).getName();
			if( (metricName==null)||
				(metricName.compareToIgnoreCase("undefined") == 0)||
			    (metricName.isEmpty())){
					this.hasTextMessage = true;
					if(isLive){
						//vp.clear();
						//vp.add(noData);
						this.hasTextMessage = true;	
						this.noData.setVisible(true);
						return;
						
					}else{
						//vp.clear();
						//vp.add(errorText);
						this.hasTextMessage = true;
						this.errorText.setVisible(true);
						return;
						
					}
					
			}
			
		}
		 
		
		if(chartType.equalsIgnoreCase("PieChart")||chartType.equalsIgnoreCase("[PieChart]")){
			ofcgwtPie = new OfcgwtPieChart(hp);
			ofcgwtPie.loadOfcgwtPieChart("Metrics",metricEntries);
			vp.add(hp);
		}
		else if(chartType.equalsIgnoreCase("BarChart")||chartType.equalsIgnoreCase("[BarChart]")){
			ofcgwtBar = new OfcgwtBarChart(hp);
			ofcgwtBar.loadOfcgwtBarChart("Bar chart", metricEntries);
			vp.add(hp);
		}
		else {
			ofcgwtLine.addLine(metricEntries,lineNumber,true, isLive);
		}
	}

	public void drawChart(String chartType){
		if(chartType.equalsIgnoreCase("LineChart")||chartType.equalsIgnoreCase("[LineChart]")){

			ofcgwtLine.drawLineChart();
			vp.add(hp);
		}
	}
	
	
	public Widget asWidget() {
		return this;
	 }
	
}
