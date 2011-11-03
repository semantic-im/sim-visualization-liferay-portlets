package ro.utcluj.larkc.platformMetrics.client.charts;

import java.text.DateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Date;
import ro.utcluj.larkc.platformMetrics.shared.MetricEntry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.ibm.icu.text.SimpleDateFormat;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;

public class OfcgwtLineChart {

	SimplePanel simplePanel = new SimplePanel();
	ChartWidget chart;
	
	/** Chart Data including X and Y axis, title, chart elements */
	ChartData chartData;
	LineChart lineChart1;
	LineChart lc2;
			
	public OfcgwtLineChart(Panel p) {
		init(p);
		
	}
	
	public void init(Panel p) {
		simplePanel.clear();
		p.add(simplePanel);
	}
	
	public void loadOfcgwtLineChart(String chartTitle, JsArray<MetricEntry> metricEntry) {
		//lineSp.clear();
		simplePanel.add(createLineChart(chartTitle, metricEntry));
	}
	
	public Widget createLineChart(String chartTitle, JsArray<MetricEntry> metricEntries) {
		
		String metricName = "userCPULoad";
		
		/* Get the necessary data for Chart Representation */
		List<String> timestampValues = new ArrayList<String>();
		timestampValues = getTimestamps(metricName,metricEntries);
		
		List<Number> metricValues = new ArrayList<Number>();
		metricValues = getMetricValues(metricName, metricEntries);
		
		/* Initialize the chart */
		chart = new ChartWidget();
		chart.setSize("100%", "250");
		chartData = new ChartData(chartTitle,"font-size: 14px; font-family: Verdana; font-weight:bold; text-align: center;");
		chartData.setBackgroundColour("#FFFFFF");
		
		/* Setting Y axis */
		YAxis yAxis = new YAxis();
		yAxis.setColour("000000");
				
		Double ySize = 200.0;
		Double yNrOfLabels = 10.0;
		Double yInterLabelDistance = ySize/yNrOfLabels;
		Double yMinValue = 0.0;
		Double yMaxValue = getMaxMetricValue(metricName,metricEntries);
		if(yMaxValue < 1){
			yMaxValue *=100.0;
			for(int i = 0; i < metricEntries.length(); i++ ){
				metricValues.set(i, (Double)metricValues.get(i)*100.00);
			}
		}
		Integer yValuesPerLabel = (int)(yInterLabelDistance* ( yMaxValue/ySize));		
		yAxis.setRange(yMinValue, yMaxValue, yValuesPerLabel);
		chartData.setYAxis(yAxis);
		
		for(int i = 0; i < yMaxValue; i++ ){
			//set a label for each
			if(	(i%yValuesPerLabel ) == 0 ){
				yAxis.addLabels(String.valueOf(i));				
			}
			else
				yAxis.addLabels("");
		}
		
		/* Setting X Axis */
		XAxis xAxis = new XAxis();
		xAxis.setColour("000000");
		
		
				
		Double xSize = 800.0;
		Double xNrOfLabels = 10.0;
		Double xInterLabelDistance = xSize/xNrOfLabels;
		Double xMinValue = 0.0;
		Integer xMaxValue = metricEntries.length();
		Integer xValuesPerLabel = (int)(xInterLabelDistance* ( metricEntries.length()/xSize));
		
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm");
		//xAxis.setLabels(labels)
		xAxis.setRange(xMinValue, xMaxValue, xValuesPerLabel);
	
		for(int i = 0; i < metricEntries.length(); i++ ){
			//set a label for each
			if(	(i%xValuesPerLabel ) == 0 )
				xAxis.addLabels(dateFormat.format( new Date(Long.valueOf(timestampValues.get(i)))));
			else
				xAxis.addLabels("");
		}
	
		chartData.setXAxis(xAxis);
			
		lineChart1 = new LineChart(LineStyle.NORMAL);
		lineChart1.setText("sysOpenFileDescrCnt");
		lineChart1.setColour("#000088");
		lineChart1.setTooltip(lineChart1.getText() + " #val#%");	
		lineChart1.setDotSize(1);
//		lc2 = new LineChart(LineStyle.NORMAL);
//		lc2.setColour("#0000ff");
//		lc2.setText("Platform CPU Load");
//		lc2.setTooltip(lc2.getText() + " #val#%");
		//lc1.addValues(50,60,90,80,65,50,40,60,80,70);
		//lc2.addValues(70,80,50,60,70,80,40,70,90,80);
		
		
		lineChart1.addValues(metricValues);
			
		chartData.addElements(lineChart1);
		//chartData.addElements(lc2);
		
		
		chart.setJsonData(chartData.toString());
				
		return chart;
	}
	
	/** Update Chart */
	public void updateLineChart(List<Number> first_line, List<Number> second_line){
		chartData.removeElement(lineChart1);
		chartData.removeElement(lc2);
		lineChart1.setValues(first_line);
		lc2.setValues(second_line);
		chartData.addElements(lineChart1);
		chartData.addElements(lc2);
		chart.setJsonData(chartData.toString());
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
			
		for (int i = 0; i < metricEntries.length(); i++) {
			
			if(metricName.equalsIgnoreCase("sysOpenFileDescrCnt"))
				values.add(metricEntries.get(i).getSysOpenFileDescrCnt());
			else if(metricName.equalsIgnoreCase("waitCPUTime"))
				values.add(metricEntries.get(i).getWaitCPUTime());
			else if(metricName.equalsIgnoreCase("userCPULoad"))
				values.add(metricEntries.get(i).getUserCPULoad());
			else if(metricName.equalsIgnoreCase("totalSysUsedMemory"))
				values.add(metricEntries.get(i).getTotalSysUsedMemory());
			else if(metricName.equalsIgnoreCase("idleCPUTime"))
				values.add(metricEntries.get(i).getIdleCPUTime());
			else if(metricName.equalsIgnoreCase("iORead"))
				values.add(metricEntries.get(i).getIORead());
			else if(metricName.equalsIgnoreCase("swapIn"))
				values.add(metricEntries.get(i).getSwapIn());
			else if(metricName.equalsIgnoreCase("waitCPULoad"))
				values.add(metricEntries.get(i).getWaitCPULoad());
			else if(metricName.equalsIgnoreCase("totalSysFreeMemory"))
				values.add(metricEntries.get(i).getTotalSysFreeMemory());
			else if(metricName.equalsIgnoreCase("userCPUTime"))
				values.add(metricEntries.get(i).getUserCPUTime());
			else if(metricName.equalsIgnoreCase("sysLoadAverage"))
				values.add(metricEntries.get(i).getSysLoadAverage());
			else if(metricName.equalsIgnoreCase("swapOut"))
				values.add(metricEntries.get(i).getSwapOut());
			else if(metricName.equalsIgnoreCase("iOWrite"))
				values.add(metricEntries.get(i).getIOWrite());
			else if(metricName.equalsIgnoreCase("idleCPULoad"))
				values.add(metricEntries.get(i).getIdleCPULoad());
			else if(metricName.equalsIgnoreCase("totalSysUsedSwap"))
				values.add(metricEntries.get(i).getTotalSysUsedSwap());
			else if(metricName.equalsIgnoreCase("irqCPUTime"))
				values.add(metricEntries.get(i).getIrqCPUTime());
			else if(metricName.equalsIgnoreCase("systemCPUTime"))
				values.add(metricEntries.get(i).getSystemCPUTime());
			else if(metricName.equalsIgnoreCase("systemCPULoad"))
				values.add(metricEntries.get(i).getSystemCPULoad());
			else if(metricName.equalsIgnoreCase("irqCPULoad"))
				values.add(metricEntries.get(i).getIrqCPULoad());
			
		}
		return values;
	}
	
	/** Gets metric timestamps */
	public List<String> getTimestamps(String metricName, JsArray<MetricEntry> metricEntries)
	{
		ArrayList<String> values = new ArrayList<String>();
			
		for (int i = 0; i < metricEntries.length(); i++) {
			values.add(metricEntries.get(i).getTimeStamp());
		}
		
		return values;
	}
	
	/** Gets maximum metric value */
	public Double getMaxMetricValue(String metricName, JsArray<MetricEntry> metricEntries){
		
		double value = 0.0f;
		
		/** works only for positive values */
		for (int i = 0; i < metricEntries.length(); i++) {

			if(metricName.equalsIgnoreCase("sysOpenFileDescrCnt"))
				value = (metricEntries.get(i).getSysOpenFileDescrCnt() > value) ? metricEntries.get(i).getSysOpenFileDescrCnt() : value;
			else if(metricName.equalsIgnoreCase("waitCPUTime"))
				value = (metricEntries.get(i).getWaitCPUTime() > value) ? metricEntries.get(i).getWaitCPUTime() : value;
			else if(metricName.equalsIgnoreCase("userCPULoad"))
				value = (metricEntries.get(i).getUserCPULoad() > value) ? metricEntries.get(i).getUserCPULoad() : value;
			else if(metricName.equalsIgnoreCase("totalSysUsedMemory"))
				value = (metricEntries.get(i).getTotalSysUsedMemory() > value) ? metricEntries.get(i).getTotalSysUsedMemory() : value;
			else if(metricName.equalsIgnoreCase("idleCPUTime"))
				value = (metricEntries.get(i).getIdleCPUTime() > value) ? metricEntries.get(i).getIdleCPUTime() : value;
			else if(metricName.equalsIgnoreCase("iORead"))
				value = (metricEntries.get(i).getIORead() > value) ? metricEntries.get(i).getIORead() : value;
			else if(metricName.equalsIgnoreCase("swapIn"))
				value = (metricEntries.get(i).getSwapIn() > value) ? metricEntries.get(i).getSwapIn() : value;
			else if(metricName.equalsIgnoreCase("waitCPULoad"))
				value = (metricEntries.get(i).getWaitCPULoad() > value) ? metricEntries.get(i).getWaitCPULoad() : value;
			else if(metricName.equalsIgnoreCase("totalSysFreeMemory"))
				value = (metricEntries.get(i).getTotalSysFreeMemory() > value) ? metricEntries.get(i).getTotalSysFreeMemory() : value;
			else if(metricName.equalsIgnoreCase("userCPUTime"))
				value = (metricEntries.get(i).getUserCPUTime() > value) ? metricEntries.get(i).getUserCPUTime() : value;
			else if(metricName.equalsIgnoreCase("sysLoadAverage"))
				value = (metricEntries.get(i).getSysLoadAverage() > value) ? metricEntries.get(i).getSysLoadAverage() : value;
			else if(metricName.equalsIgnoreCase("swapOut"))
				value = (metricEntries.get(i).getSwapOut() > value) ? metricEntries.get(i).getSwapOut() : value;
			else if(metricName.equalsIgnoreCase("iOWrite"))
				value = (metricEntries.get(i).getIOWrite() > value) ? metricEntries.get(i).getIOWrite() : value;
			else if(metricName.equalsIgnoreCase("idleCPULoad"))
				value = (metricEntries.get(i).getIdleCPULoad() > value) ? metricEntries.get(i).getIdleCPULoad() : value;
			else if(metricName.equalsIgnoreCase("totalSysUsedSwap"))
				value = (metricEntries.get(i).getTotalSysUsedSwap() > value) ? metricEntries.get(i).getTotalSysUsedSwap() : value;
			else if(metricName.equalsIgnoreCase("irqCPUTime"))
				value = (metricEntries.get(i).getIrqCPUTime() > value) ? metricEntries.get(i).getIrqCPUTime() : value;
			else if(metricName.equalsIgnoreCase("systemCPUTime"))
				value = (metricEntries.get(i).getSystemCPUTime() > value) ? metricEntries.get(i).getSystemCPUTime() : value;
			else if(metricName.equalsIgnoreCase("systemCPULoad"))
				value = (metricEntries.get(i).getSystemCPULoad() > value) ? metricEntries.get(i).getSystemCPULoad() : value;
			else if(metricName.equalsIgnoreCase("irqCPULoad"))
				value = (metricEntries.get(i).getIrqCPULoad() > value) ? metricEntries.get(i).getIrqCPULoad() : value;
			
		}
		return value;
	}
}
