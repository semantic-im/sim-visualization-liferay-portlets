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
import com.rednels.ofcgwt.client.model.ToolTip;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.Element;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.PieChart.Slice;

public class OfcgwtPieChart {

	SimplePanel simplePanel = new SimplePanel();
	ChartWidget chartWidget;
	
	/** Chart Data including X and Y axis, title, chart elements */
	ChartData chartData;
	PieChart pieChart;
	
	String chartTitle = "";
	
	Double metricValuesSum = 0d;
	
			
	public OfcgwtPieChart(Panel p) {
		init(p);
		
	}
	
	public void init(Panel p) {
		simplePanel.clear();
		p.add(simplePanel);
	}
	
	public void loadOfcgwtPieChart(String chartTitle, JsArray<MetricEntry> metricEntry) {
		//lineSp.clear();
		simplePanel.add(createPieChart(chartTitle, metricEntry));
	}
	
	public Widget createPieChart(String chartTitle, JsArray<MetricEntry> metricEntries) {
		
		
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
		
		chartTitle = metricName + " metric";
		this.chartTitle = chartTitle = "";
		/* Get the necessary data for Chart Representation */
		//List<String> timestampValues = new ArrayList<String>();
		//timestampValues = getTimestamps(metricName,metricEntries);
	
		
		List<Number> metricValues = new ArrayList<Number>();
		metricValues = getMetricValues(metricName, metricEntries);
		
		/* Initialize the chart */
		chartWidget = new ChartWidget();
		chartWidget.setSize("100%", "500");
		chartData = new ChartData(chartTitle,"font-size: 14px; font-family: Verdana; font-weight:bold; text-align: center;");
		chartData.setBackgroundColour("#FFFFFF");
		
		Legend chartLegend = new Legend(Position.TOP, false);
		chartLegend.setStroke(2);
		chartData.setLegend(chartLegend);
		
        
		//get maximum of 10 slices from the result
		Integer numberOfSlices = metricValues.size() > 0 
							? ((metricValues.size() <= 10) ? metricValues.size() : 10) 
							: 0;
							
		pieChart = new PieChart();
		
		pieChart.setNoLabels(false);
        pieChart.setTooltip("#label# <br> #percent#"); 
      
        pieChart.setColours("#058DC7", "#50B432","#ED561B","#EDEF00","#24CBE5","#64E572","#FF9655","#FFF263", "#6AF9C4", "#B2DEFF", "#CCCCCC");
      
        
        NumberFormat fmt = NumberFormat.getFormat(".##");
        
     
        
        if(numberOfSlices == 0){
        	pieChart.addSlice(0,"No data");
        }else{
        	
        	//compute the sum of value for allocated slices
        	for(int i = 0; i < numberOfSlices; i++){
        		metricValuesSum += metricValues.get(i).doubleValue();
        	}
        	
        	//add the avg value for the remained slices to the sum of existing values
        	Double avgValue = 0.0;
        	if(numberOfSlices < metricValues.size()){
        		
        		//compute the avg Value
        		for(int i = numberOfSlices; i < metricValues.size(); i++){
        			avgValue += metricValues.get(i).doubleValue();
        		}
        		avgValue = avgValue/(metricValues.size()-numberOfSlices);
        		
        		metricValuesSum += avgValue;
      		
        	}
        	
        	for(int i = 0; i < numberOfSlices; i++){
        		String percents = (metricValuesSum != null) || (metricValuesSum > 0) ? " (" + String.valueOf( fmt.format((metricValues.get(i).doubleValue()/metricValuesSum)*100) ) +"%)" : "";
        		pieChart.addSlices(new Slice(metricValues.get(i),fmt.format(metricValues.get(i)).toString() + percents, metricEntries.get(i).getName().replace(";", " ")));
        		//String test = metricEntries.get(i).getName();
        	}
        	
        	if(numberOfSlices < metricValues.size()){
        		String percents = (metricValuesSum != null) || (metricValuesSum > 0) ? " (" + String.valueOf( fmt.format((avgValue/metricValuesSum)*100)) +"%)" : "";
        		pieChart.addSlices(new Slice(avgValue,fmt.format(avgValue).toString() + percents, "Others"));
        	}
        	
        }
       
        
        chartData.addElements(pieChart);
        chartWidget.setJsonData(chartData.toString());
        
		return chartWidget;
        
		
	
		
		
	}
	
	/** Update Chart */
	public void updateLineChart(List<Number> first_line, List<Number> second_line){
		chartData.removeElement(pieChart);
		//chartData.removeElement(lc2);
		pieChart.setValues(first_line);
		
		chartData.addElements(pieChart);
		//chartData.addElements(lc2);
		chartWidget.setJsonData(chartData.toString());
	}
	
	public void reloadChart(JsArray<MetricEntry> metricEntries){
		
		Iterator<Element> chartIterator = chartData.getElements().iterator();
		
		while(chartIterator.hasNext()){
			chartData.removeElement(chartIterator.next());
		}
		
		pieChart.setAnimate(false);
		createPieChart(chartTitle, metricEntries);
		//chartWidget.setJsonData(chartData.toString());

	}
	
	/** Add a new line to an existing chart */
	public void addChartLine(List<Number> values)
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
			
			
//			//jsObject = metricEntries.get(i).parseJson();
//			jsObject = metricEntries.get(i);
//			
//			jsArrayString = metricEntries.get(i).keys();
//			for (int j = 0; j < jsArrayString.length(); j++){
//				key = jsArrayString.get(j);
//				value = metricEntries.get(i).get(key);
//			}
//			//s = jsObject.();
			
			
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
