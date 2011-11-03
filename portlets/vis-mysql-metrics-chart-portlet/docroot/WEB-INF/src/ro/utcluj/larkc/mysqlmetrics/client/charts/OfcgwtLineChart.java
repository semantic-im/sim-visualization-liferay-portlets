package ro.utcluj.larkc.mysqlmetrics.client.charts;

import java.text.DateFormat;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Date;
import ro.utcluj.larkc.mysqlmetrics.shared.MetricEntry;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.ibm.icu.text.SimpleDateFormat;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.Element;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;

public class OfcgwtLineChart {

	SimplePanel simplePanel = new SimplePanel();
	ChartWidget chart;
	
	/** Chart Data including X and Y axis, title, chart elements */
	ChartData chartData;
	
	//The two lines for the line chart set to null by default
	LineChart lineChartFirst = null;
	LineChart lineChartSeccond = null;
	
	String chartTitle = "";
	
	YAxis yAxis = new YAxis();
	XAxis xAxis = new XAxis();;
	
	
	//LineChart lc2;
	
	Double ySize = 200.0;
	Double yNrOfLabels = 10.0;
	Double yInterLabelDistance = ySize/yNrOfLabels;
	Double yMinValue = 0.0;
	Double yMaxValue1 = 0.0;
	Double yMaxValue2 = 0.0;
	Double yMaxValue  = 0.0;
	
	Double xSize = 400.0;
	Double xNrOfLabels = 6.0;
	Double xInterLabelDistance = xSize/xNrOfLabels;
	Double xMinValue = 0.0;
	long xMaxValue = 0;
	
	
	
	
	
	List<String> timestampValues = new ArrayList<String>();
	ArrayList<String> lineColors = new ArrayList<String>();
	
	//int colorIndex = 0;
	
	public OfcgwtLineChart(Panel p, String chartTitle) {
		init(p, chartTitle);
		
	}
	
	public void init(Panel p, String chartTitle) {
		simplePanel.clear();
		p.add(simplePanel);
		
		/* Initialize the chart */
		chart = new ChartWidget();
		chart.setSize("100%", "250");
		//chart.setCacheFixEnabled(true);
		chartData = new ChartData(chartTitle,"font-size: 14px; font-family: Verdana; font-weight:bold; text-align: center;");
		chartData.setBackgroundColour("#FFFFFF");
		
		/* Setting X,Y axis */		
		yAxis.setColour("000000");
		
		xAxis.setRange(0, 10, 1);
		
		this.ySize = 200.0;
		this.yNrOfLabels = 10.0;
		this.yInterLabelDistance = ySize/yNrOfLabels;
		this.yMinValue = 0.0;
		
		addLineColours(lineColors);
		
//		chart = new ChartWidget();
//		chart.setSize("100%", "250");
//		chartData = new ChartData(chartTitle,"font-size: 14px; font-family: Verdana; font-weight:bold; text-align: center;");
//		chartData.setBackgroundColour("#FFFFFF");
//		
	}
	
	public void loadOfcgwtLineChart(String chartTitle, JsArray<MetricEntry> metricEntry) {
		
		
		//lineSp.clear();
		//simplePanel.add(createLineChart(chartTitle, metricEntry));
	}
	
	/**
	 * Add Line to a Line Chart.
	 * 
	 * @param metricEntries
	 * @param lineNumber
	 * @param appendChartTytle
	 * @param isLive
	 */
	public void addLine(JsArray<MetricEntry> metricEntries, int lineNumber, boolean appendChartTytle, boolean isLive) {
		

		String metricName = metricEntries.get(0).getMetricName();

		if((metricName==null)||
				(metricName.isEmpty())||
				(metricName.compareToIgnoreCase("undefined") == 0)){
			metricName = metricEntries.get(0).getName();
			if( (metricName==null)||
					(metricName.compareToIgnoreCase("undefined") == 0)||
					(metricName.isEmpty())){
				
				
					return;
				
			}

		}
		
		if(appendChartTytle){
			chartTitle += metricName + "; ";
		}
		
		
		//update the yMaxValue in order to draw the Y axis
		Double localYMaxValue = getMaxMetricValue(metricName,metricEntries);		
		
		yMaxValue1 = (lineNumber == 1) ? localYMaxValue : yMaxValue1;
		yMaxValue2 = lineNumber == 2 ? localYMaxValue : yMaxValue2;
		
		yMaxValue = yMaxValue1 > yMaxValue2 ? yMaxValue1 : yMaxValue2;
		
		//update the timestamp values
		//timestampValues = timestampValues.size() == 0 ? getTimestamps(metricName,metricEntries) : timestampValues;
		timestampValues = getTimestamps(metricName,metricEntries); 
		if(yMaxValue == 0 ){
			yMaxValue = 1.0;
		}
		

		
		List<Number> metricValues = new ArrayList<Number>();
		metricValues = getMetricValues(metricName, metricEntries);
		
				
	
	
		Integer xValuesPerLabel = (int)(xInterLabelDistance* ( metricEntries.length()/xSize));
		
		//if(xMaxValue == 0){
		
		xMaxValue = metricEntries.length();
		
		xAxis = new XAxis();
		xAxis.setColour("000000");
		xAxis.setRange(xMinValue, xMaxValue, xValuesPerLabel);

		com.rednels.ofcgwt.client.model.axis.Label label = null;
		for(int i = 0; i < metricEntries.length(); i++ ){
			//set a label for each bean
			if(i==0){
				// First element
				label = new com.rednels.ofcgwt.client.model.axis.Label(timestampValues.get(i));
				label.setRotationAngle(10);
				xAxis.addLabels(label);
			}else
			if(	(i%xValuesPerLabel ) == 0 ){
				label = new com.rednels.ofcgwt.client.model.axis.Label(timestampValues.get(i));
				label.setRotationAngle(10);
				xAxis.addLabels(label);
			}
			else
				xAxis.addLabels("");
		}
		//}
		
		//get the next color with a maximum value of lineColors size
		
		LineChart lineChart = new LineChart(LineStyle.NORMAL);
		
		
		lineChart.setText(metricName);
		int colorIndex = lineNumber > 0 ? lineNumber - 1 : 0;
		lineChart.setColour(lineColors.get(colorIndex));
		colorIndex = (colorIndex + 1) % lineColors.size(); 
		lineChart.setTooltip(lineChart.getText() + " #val#");	
		lineChart.setDotSize(1);
		lineChart.addValues(metricValues);
		
//		if(refresh){
//			Iterator<Element> chartIterator = chartData.getElements().iterator();
//			while(chartIterator.hasNext()){
//				chartData.removeElement(chartIterator.next());
//			}
//		}
		chartData.addElements(lineChart);
		//chartData.addElements(lc2);
		

		
	
	}
	
	public boolean reloadLine(JsArray<MetricEntry> metricEntries, int lineNumber, boolean refreshChart){
		
		
		String metricName = metricEntries.get(0).getMetricName();
		if((metricName==null)||
				(metricName.isEmpty())||
				(metricName.compareToIgnoreCase("undefined") == 0)){
			metricName = metricEntries.get(0).getName();
			if( (metricName==null)||
					(metricName.compareToIgnoreCase("undefined") == 0)||
					(metricName.isEmpty())){

				metricName = "";
				return false;
			}
		}

		int currentLineIndex = 0;
		int removedLineIndex = -1;
		
		
		Iterator<Element> chartIterator = chartData.getElements().iterator();
		if(refreshChart){
			//remove all elements
			chartData.setElements(new ArrayList<Element>());
//			while(chartIterator.hasNext()){
//				Element chartElement = chartIterator.next();
//				chartData.removeElement(chartElement);
//			}
//			
				
			addLine(metricEntries,lineNumber, true, true);
			
		}else{
			//retrieve and remove the Chart Line corresponding to a given lineIndex
			boolean isMarkedAsRemoved = false;
			while((chartIterator.hasNext() && (!isMarkedAsRemoved))/*&&(currentIndex < lineNumber)*/){
				
				Element chartElement = chartIterator.next();
				currentLineIndex++;
				if( metricName.equalsIgnoreCase(chartElement.getText()) && (!metricName.isEmpty()) ){
					
					chartData.removeElement(chartElement);
					removedLineIndex = currentLineIndex;
					isMarkedAsRemoved = true;
				}
				
			}
				
			addLine(metricEntries,lineNumber, false, true);
			
		}
	
		
		
		yAxis.setMin(0);
		yAxis.setMax(yMaxValue+(yMaxValue/yNrOfLabels));
		yAxis.setSteps(yMaxValue/yNrOfLabels);
		//yAxis.setRange(yMinValue, yMaxValue, );
		chartData.setYAxis(yAxis);
		chartData.setXAxis(xAxis);
		
		chart.setJsonData(chartData.toString());
		return true;
		
	}
	
	
	public void addLineColours(ArrayList<String> lineColours){
		lineColours.add("#058DC7"); 
		lineColours.add("#ED561B");
        lineColours.add("#50B432"); 
        lineColours.add("#EDEF00"); 
        lineColours.add("#24CBE5"); 
        lineColours.add("#64E572"); 
        lineColours.add("#FF9655"); 
        lineColours.add("#FFF263"); 
        lineColours.add("#6AF9C4"); 
        lineColours.add("#B2DEFF");
      
	}
	
	public void drawLineChart(){
		
		//ArrayList<?> elements = (ArrayList<?>) chartData.getElements();
//		if(chartData.getElements().size() == 0){
//			return;
//		}
		
		Text chartDataTitle = new Text(chartTitle);
		chartDataTitle.setStyle("font-size: 14px; font-family: Verdana; font-weight:bold; text-align: center;");
		chartData.setTitle(chartDataTitle);
		
		yAxis.setMin(0);
		yAxis.setMax(yMaxValue+(yMaxValue/yNrOfLabels));
		yAxis.setSteps(yMaxValue/yNrOfLabels);
		//yAxis.setRange(yMinValue, yMaxValue, );
		chartData.setYAxis(yAxis);
		chartData.setXAxis(xAxis);
		
		chart.setJsonData(chartData.toString());
		simplePanel.clear();
		simplePanel.add(chart);
			
	}
	
	public void setXYAxis(JsArray<MetricEntry> metricEntries, List<String> timestampValues){
		

	}
	
	
	
	/**
	 * Get metric values from an existing JSONArray
	 * @param metricName
	 * @param metricEntries - array of JSON entries
	 */
	public List<Number> getMetricValues(String metricName, JsArray<MetricEntry> metricEntries)
	{
		ArrayList<Number> values = new ArrayList<Number>();
	
		for (int i = 0; i < metricEntries.length(); i++) {
			
			values.add(Double.parseDouble(metricEntries.get(i).getValue()) );					
		}
		return values;
	}
	
	/** Gets metric timestamps */
	public List<String> getTimestamps(String metricName, JsArray<MetricEntry> metricEntries)
	{
		ArrayList<String> values = new ArrayList<String>();
		
		String starTimeString = metricEntries.get(0).getTimestamp();
		String endTimeString = metricEntries.get(metricEntries.length()-1).getTimestamp();
		
		DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
		
		
		Date startTime = starTimeString != null ? dtf.parse(starTimeString) : null;
		Date endTime = endTimeString != null ? dtf.parse(endTimeString) : null;
		
		
		long timeInterval = (startTime != null) || (endTime != null) ? 
							endTime.getTime() - startTime.getTime() : 0;
		
		DateTimeFormat dtfChart;
		
		
		
		if((timeInterval > 0) && (timeInterval <= 60000)){
			//less than 1 minute
			dtfChart = DateTimeFormat.getFormat("ss");
		}else if((timeInterval > 60000) && (timeInterval <= 60* 60000)){
			//less than 1 hour
			dtfChart = DateTimeFormat.getFormat("mm:ss");
		}else if((timeInterval > 60* 60000) && (timeInterval < 24* 60* 60000)){
			//less than 1 day
			dtfChart = DateTimeFormat.getFormat("HH:mm:ss");
		}else{
			dtfChart = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
		}
		
		for (int i = 0; i < metricEntries.length(); i++) {
			if((i == 0)||(i == (metricEntries.length()-1))){
				values.add(metricEntries.get(i).getTimestamp());
			}else{
				values.add(
							dtfChart.format( 
									dtf.parse(metricEntries.get(i).getTimestamp())
							)
						);
			}
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
