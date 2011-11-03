package ro.utcluj.larkc.platformMetrics.client.view;

import java.util.ArrayList;
import java.util.List;

import ro.utcluj.larkc.platformMetrics.client.charts.OfcgwtLineChart;
import ro.utcluj.larkc.platformMetrics.shared.MetricEntry;



import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class RRDMetricsView extends Composite {
	
	HTML homeText = new HTML("<h3>System Metrics</h3>");
	
	OfcgwtLineChart ofcgwtLine;
	JsArray<MetricEntry> metricEntry = null;
	VerticalPanel vp = null;
	HorizontalPanel hp = null;
	
	/** Constructor */
	public RRDMetricsView(){		
		
		vp = new VerticalPanel();
		hp = new HorizontalPanel();
		vp.addStyleName("rrdMetricsChartVP");
		hp.addStyleName("rrdMetricsChartHP");
		vp.setWidth("100%");
		hp.setWidth("100%");
		initWidget(vp);
		
	}

	// chart refresh timer
	Timer refreshTimer_linechart = new Timer() {
		public void run() {			
			refresh_line_chart();		
		}
	};
	
	/*
	 * Initializing chart with the JSON Metrics
	 */
	public void loadData(JsArray<MetricEntry> metricEntry){
		this.metricEntry = metricEntry;
		
//			
		
		vp.add(homeText);
		ofcgwtLine = new OfcgwtLineChart(hp);
		vp.add(hp);
	
				
		//load charts
		ofcgwtLine.loadOfcgwtLineChart("System Metrics",metricEntry);
	

		//set the refresh interval
		refreshTimer_linechart.scheduleRepeating(1000);	
	}

	
	


	private void refresh_line_chart() {
//		//dummy data again - include here rrd data
//		aa.remove(0);
//		bb.remove(0);
//		
//		aa.add(Random.nextInt(100));
//		bb.add(Random.nextInt(100));
//		
		// end rrd data
		//ofcgwtLine.updateLineChart(aa, bb);
	}
	
	
	
	public Widget asWidget() {
		return this;
	 }
	
}
