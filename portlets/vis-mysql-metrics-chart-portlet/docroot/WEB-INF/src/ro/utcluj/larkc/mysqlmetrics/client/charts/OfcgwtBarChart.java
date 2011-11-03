package ro.utcluj.larkc.mysqlmetrics.client.charts;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ro.utcluj.larkc.mysqlmetrics.shared.MetricEntry;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Legend;
import com.rednels.ofcgwt.client.model.Legend.Position;
import com.rednels.ofcgwt.client.model.axis.Label;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.CylinderBarChart;
import com.rednels.ofcgwt.client.model.elements.Element;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.Bar;

public class OfcgwtBarChart {

	SimplePanel simplePanel = new SimplePanel();
	ChartWidget chartWidget;
	
	/** Chart Data including X and Y axis, title, chart elements */
	ChartData chartData;
	CylinderBarChart barChart;
	String chartTitle = "";
			
	public OfcgwtBarChart(Panel p) {
		init(p);
		
	}
	
	public void init(Panel p) {
		simplePanel.clear();
		p.add(simplePanel);
	}
	
	public void loadOfcgwtBarChart(String chartTitle, JsArray<MetricEntry> metricEntries) {
		//lineSp.clear();
		simplePanel.add(createBarChart(chartTitle, metricEntries));
	}
	
	public Widget createBarChart(String chartTitle, JsArray<MetricEntry> metricEntries ) {
		
		
		String metricName = metricEntries.get(0).getMetricName();

		if((metricName==null)||
				(metricName.isEmpty())||
				(metricName.compareToIgnoreCase("undefined") == 0)){
			metricName = metricEntries.get(0).getName();
			if( (metricName==null)||
					(metricName.compareToIgnoreCase("undefined") == 0)||
					(metricName.isEmpty())){
				
				return null;
			}

		}
		
		//chartTitle = metricName + " metric";
		//this.chartTitle = chartTitle = "Bar Chart";
		this.chartTitle = chartTitle = "";
		/* Get the necessary data for Chart Representation */
		//List<String> timestampValues = new ArrayList<String>();
		//timestampValues = getTimestamps(metricName,metricEntries);
		
		List<Number> metricValues = new ArrayList<Number>();
		metricValues = getMetricValues(metricName, metricEntries);
		
		/* Initialize the chart */
		chartWidget = new ChartWidget();
		
        
		//get maximum of 10 slices from the result
		Integer numberOfBars = metricValues.size() > 0 
							? ((metricValues.size() <= 10) ? metricValues.size() : 10) 
							: 0;
				
		//set Chart size based on chart number of bars
	    chartWidget.setSize("100%", "400");
	    //String test = String.valueOf((int)((numberOfBars+1)*(100.0/4.0)))+"%";
	    

		chartData = new ChartData(chartTitle,"font-size: 14px; font-family: Verdana; font-weight:bold; text-align: center;");
		chartData.setBackgroundColour("#FFFFFF");
		
		Legend chartLegend = new Legend(Position.TOP, false);
		chartLegend.setStroke(2);
		chartData.setLegend(chartLegend);
		
	    
		barChart = new CylinderBarChart();
		//barChart.setNoLabels(false);
        barChart.setTooltip("#val#"); 
        barChart.setAlpha(0.9f);
        ArrayList<String> barColours = new ArrayList<String>();  //barChart.setColours();
        addBarColours(barColours);
        
        XAxis xa = new XAxis();
        xa.setColour("#C1C1C1");
        xa.setZDepth3D(5);
        xa.setTickHeight(2);
        
        Double maxYSize = 5d;
        
        if(numberOfBars == 0){
        	xa.addLabels("No data");
        	CylinderBarChart.Bar bar = new CylinderBarChart.Bar(50);
        	bar.setColour("#C1C1C1");
        	barChart.addBars(bar);
        	
        }else{
        	for(int i = 0; i < numberOfBars; i++){
        		Label barXLabel = new Label(metricEntries.get(i).getName().replace(";", " "),30);
        		xa.addLabels(barXLabel);
            	CylinderBarChart.Bar bar = new CylinderBarChart.Bar(metricValues.get(i));
            	bar.setColour(barColours.get(i));
            	barChart.addBars(bar);
            	
            	maxYSize = maxYSize < metricValues.get(i).doubleValue() ? metricValues.get(i).doubleValue() : maxYSize;
        		//barChart.addSlices(new Slice(metricValues.get(i),fmt.format(metricValues.get(i)).toString(), metricEntries.get(i).getName()));
        		//String test = metricEntries.get(i).getName();
        	}
        	
        	//for other unprocessed bins we compute the average value
        	if(numberOfBars < metricValues.size()){
        		Double avgValue = 0.0;
        		for(int i = numberOfBars; i < metricValues.size(); i++){
        			avgValue += metricValues.get(i).doubleValue();
        		}
        		avgValue = avgValue/(metricValues.size()-numberOfBars);
        		
        		Label barXLabel = new Label("Others",30);
        		xa.addLabels(barXLabel);
            	CylinderBarChart.Bar bar = new CylinderBarChart.Bar(avgValue);
            	bar.setColour("#CCCCCC");
            	barChart.addBars(bar);
        	}
        	
        }
        
        YAxis ya = new YAxis();
        int yNrOfLabels = 11; 
        ya.setMax(maxYSize+maxYSize/yNrOfLabels); //setting the max scale value with one label more
        ya.setSteps(maxYSize/yNrOfLabels);
        
        chartData.setXAxis(xa);
        chartData.setYAxis(ya);
        
        chartData.addElements(barChart);
        chartWidget.setJsonData(chartData.toString());
        
		return chartWidget;
		
		
	}
	
	public void addBarColours(ArrayList<String> barColours){
		barColours.add("#058DC7"); 
        barColours.add("#50B432"); 
        barColours.add("#ED561B"); 
        barColours.add("#EDEF00"); 
        barColours.add("#24CBE5"); 
        barColours.add("#64E572"); 
        barColours.add("#FF9655"); 
        barColours.add("#FFF263"); 
        barColours.add("#6AF9C4"); 
        barColours.add("#B2DEFF");
      
	}
	
	/** Update Chart */
	public void updateBarChart(List<Number> first_line, List<Number> second_line){
		chartData.removeElement(barChart);
		//chartData.removeElement(lc2);
		barChart.setValues(first_line);
		
		chartData.addElements(barChart);
		//chartData.addElements(lc2);
		chartWidget.setJsonData(chartData.toString());
	}
	
	public void reloadChart(JsArray<MetricEntry> metricEntries){
		
		Iterator<Element> chartIterator = chartData.getElements().iterator();
		
		while(chartIterator.hasNext()){
			chartData.removeElement(chartIterator.next());
		}
		
		createBarChart(chartTitle, metricEntries);
		
		//chartWidget.setJsonData(chartData.toString());

	}
	
	/** Add a new line to an existing chart */
	public void addBarChart(List<Number> values)
	{
		//To be done
	}
	
	/**
	 * Get metric values from an existing JSONArray
	 * @param metricName
	 * @param metricEntries - array of JSON entries
	 */
	public List<Number> getMetricValues(String metricName, JsArray<MetricEntry> metricEntries)
	{
		ArrayList<Number> values = new ArrayList<Number>();
		JavaScriptObject jsObject = null;
		String key = "";
		String value = "";
		JsArrayString jsArrayString = null;
		for (int i = 0; i < metricEntries.length(); i++) {
			
			values.add(Double.parseDouble(metricEntries.get(i).getValue()));		
			
			//jsObject = metricEntries.get(i).parseJson();
			jsObject = metricEntries.get(i);
			
			jsArrayString = metricEntries.get(i).keys();
			for (int j = 0; j < jsArrayString.length(); j++){
				key = jsArrayString.get(j);
				value = metricEntries.get(i).get(key);
			}
			//s = jsObject.();
			
			
		}
		return values;
	}
	
	/** Gets metric timestamps */
	public List<String> getTimestamps(String metricName, JsArray<MetricEntry> metricEntries)
	{
		ArrayList<String> values = new ArrayList<String>();
			
		for (int i = 0; i < metricEntries.length(); i++) {
			values.add(metricEntries.get(i).getTimestamp());
		}
		
		return values;
	}
	
	/** Gets maximum metric value */
	public Double getMaxMetricValue(String metricName, JsArray<MetricEntry> metricEntries){
		
		double value = 0.0f;
		
		/** works only for positive values */
		for (int i = 0; i < metricEntries.length(); i++) {
		
			value = (Double.parseDouble(metricEntries.get(i).getValue()) > value) 
					? Double.parseDouble(metricEntries.get(i).getValue()) 
					: value;					
		}
		return value;
	}
}
