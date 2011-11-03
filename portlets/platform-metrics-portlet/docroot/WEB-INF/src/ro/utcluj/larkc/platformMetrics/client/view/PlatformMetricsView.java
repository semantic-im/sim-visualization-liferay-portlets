package ro.utcluj.larkc.platformMetrics.client.view;

import java.util.ArrayList;
import java.util.List;

import ro.utcluj.larkc.platformMetrics.client.charts.OfcgwtLineChart;



import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class PlatformMetricsView extends Composite {
	
	HTML homeText = new HTML("<h3>Platform Metrics</h3>");
	//temporary
	
	//private static final String JSON_URL = "http://larkc.utcluj.ro:8182/test?dbtype=rrd&rrd=TotalSystemUsedMemory&start=1291807946100&end=1291807950000&resolution=1";
	
	//new
	OfcgwtLineChart ofcgwtLine;
	
	List<Number> aa;
	List<Number> bb;
	List<Number> cc;
	// chart refresh timer
	Timer refreshTimer_linechart = new Timer() {
		public void run() {			
			refresh_line_chart();		
		}
	};
	
	
	public PlatformMetricsView(){
		
		aa = new ArrayList<Number>();
		bb  = new ArrayList<Number>();
		cc  = new ArrayList<Number>();
		for(int i = 0; i<10; i++) {
			aa.add(0);
			bb.add(0);
		}
		cc.add(30);
		cc.add(30);
		cc.add(30);
		cc.add(30);
		
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		
		initWidget(vp);
		vp.add(homeText);
		
		//new
		ofcgwtLine = new OfcgwtLineChart(hp);
		
		vp.add(hp);
	
				
		//load charts
		ofcgwtLine.loadOfcgwtLineChart("CPU Load", aa, bb);
	

		//set the refresh interval
		refreshTimer_linechart.scheduleRepeating(1000);	
		//refresh();
		/*
		XJSONDataSource metricsDS = new XJSONDataSource();
		metricsDS.setDataURL("http://larkc.utcluj.ro:8182/test?dbtype=mysql&command=getmetrics");
		metricsDS.setRecordXPath("");
	
		DataSourceField queryContext = new DataSourceField("QueryContext", FieldType.TEXT);
		metricsDS.addField(queryContext);
		
		DataSourceField metricName = new DataSourceField("MetricName", FieldType.TEXT);
		metricsDS.addField(metricName);
		
		DataSourceField metricValue = new DataSourceField("MetricValue", FieldType.TEXT);
		metricsDS.addField(metricValue);
	
		final ListGrid metricsGrid = new ListGrid();
		//grid.setTop(120);
		//metricsGrid.setWidth100();
		metricsGrid.setHeight(250);
		metricsGrid.setAutoFitMaxWidth(400);
		metricsGrid.setWrapCells(true);
		metricsGrid.setFixedRecordHeights(false);
		metricsGrid.setShowAllRecords(true);
		metricsGrid.setDataSource(metricsDS);	
		metricsGrid.fetchData();
		metricsGrid.setWidth("80%");
		//p.setWidth("80%");
		vp.add(metricsGrid);
		*/
	}
	


	private void refresh_line_chart() {
		//dummy data again - include here rrd data
		aa.remove(0);
		bb.remove(0);
		
		aa.add(Random.nextInt(100));
		bb.add(Random.nextInt(100));
		
		// end rrd data
		ofcgwtLine.updateLineChart(aa, bb);
	}
	
	
	
	public Widget asWidget() {
		return this;
	 }
	
}
