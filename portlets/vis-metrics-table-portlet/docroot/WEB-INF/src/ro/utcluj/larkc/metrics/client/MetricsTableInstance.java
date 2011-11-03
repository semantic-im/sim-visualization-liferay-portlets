package ro.utcluj.larkc.metrics.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import ro.utcluj.larkc.metrics.shared.MetricEntries;
import ro.utcluj.larkc.metrics.shared.MetricEntry;
import ro.utcluj.larkc.metrics.shared.JsTablePortletInstance;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

/*
 * url to servlet - need to be initialized into view.jsp (like in mysql metrics portlet)
 */

public class MetricsTableInstance {

	public MetricsTableInstance(JsTablePortletInstance instance, String myUrl){
		/*
		 * Get settings to be passed to the servlet. Checking if there are GET parameters or portlet parameters.
		 */
		
		final String mysqlQuery = instance.getMysqlQuery();
		final String useQuery = instance.getUseQuery();
		final String tableName = instance.getTableName();
		final String metricName = instance.getMetricName();
		final String useMetrics = instance.getUseMetrics();
		final String pageSize = instance.getPageSize();
		final String limit = instance.getLimit();
		final String chartCmd = instance.getChartCmd();
		final String targetPage = instance.getTargetPage();
		final String portletId = instance.getPortletID();
		//advanced table parameters
		final String col = instance.getCol();
		final String name = instance.getName();
		
		final String name1 = instance.getName1();
		final String name2 = instance.getName2();
		final String val1 = instance.getVal1();
		final String val2 = instance.getVal2();
		
		final String clearURLParameters = instance.getClearURLParameters();
		
		//settings that could be passed from the GET Parameters
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("idMetric");
		paramList.add("idPlatform");
		paramList.add("idWorkflow");
		paramList.add("idPlugin");
		paramList.add("idQuery");
		paramList.add("idSystem");
		paramList.add("PlatformName");
		paramList.add("ApplicationName");
		paramList.add("PluginName");
		paramList.add("MetricName");
		paramList.add("SystemName");
		paramList.add("QueryName");
		paramList.add("WorkflowName");
		
		// cell table
		final CellTable<ArrayList<String>> myCellTable = new CellTable<ArrayList<String>>();
		
		//create the URL
		String configURL = myUrl + "?" + "useQuery=" + useQuery + "&" + "mysqlQuery=" + mysqlQuery +  
		"&" + "tableName=" + tableName + "&" + "metricName=" + metricName + "&" + "useMetrics=" + useMetrics + "&" + "limit=" + limit;
		
		final Map<String,List<String>> paramMap = Window.Location.getParameterMap();
		
		for(int i = 0; i < paramList.size(); i++) {
			if(paramMap.containsKey(paramList.get(i))) {
				configURL += "&" + paramList.get(i) + "=" + paramMap.get(paramList.get(i)).get(0); 
				}
		}
		
		configURL = configURL.replace("[", "");
		configURL = configURL.replace("]", "");
		
		final String url = URL.encode(configURL);
		
		JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
		jsonp.setTimeout(3*60*1000);
		jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
	    	public void onFailure(Throwable throwable) {
	    		GWT.log("Error: " + throwable);
	    	}
	    	
	    	public void onSuccess(MetricEntries metrics) {
	    		JsArray<MetricEntry> entries  = metrics.getMetricEntries();
	    		final JsArrayString header = entries.get(0).keys();
	    		
	    		ArrayList<ArrayList<String>> metricsList = new ArrayList<ArrayList<String>>();
	    		myCellTable.setWidth("100%", true);
	    		
	    		for(int i = 0; i < entries.length(); i++){
	    			ArrayList<String> tempList = new ArrayList<String>();
	    			for (int j = 0; j < header.length(); j++ ) {
	    				if(header.get(j).equalsIgnoreCase("WorkflowDescription")){
	    					String x = entries.get(i).get(header.get(j));
	    					x = x.replace(";", " ");
	    					tempList.add(x);
	    				}else{
	    					tempList.add(entries.get(i).get(header.get(j)));
	    				}
	    				
	    			}
	    			metricsList.add(tempList);
	    		}
	    		
	    		// Create a data provider.
	    	    ListDataProvider<ArrayList<String>> myDataProvider = new ListDataProvider<ArrayList<String>>();
	    	    // Connect the table to the data provider.
	    	    myDataProvider.addDataDisplay(myCellTable);
	    	    // Add the data to the data provider, which automatically pushes it to the widget.
	    	    List<ArrayList<String>> myList = myDataProvider.getList();
	    	    for(ArrayList<String> str: metricsList) {
	    	    	myList.add(str);
	    	    }
	    	    final ArrayList<String> paramList = new ArrayList<String>();
	    	    paramList.add("idMetric");
	    		paramList.add("idPlatform");
	    		paramList.add("idWorkflow");
	    		paramList.add("idPlugin");
	    		paramList.add("idQuery");
	    		paramList.add("idSystem");
	    		paramList.add("PlatformName");
	    		paramList.add("ApplicationName");
	    		paramList.add("PluginName");
	    		paramList.add("MetricName");
	    		paramList.add("SystemName");
	    		paramList.add("QueryName");
	    		paramList.add("WorkflowName");
	    		paramList.add("WorkflowDescription");
	    		paramList.add("QueryContent");
	    		
	    	    for(int k = 0; k < header.length(); k++) {
	    			final int kk=k;
	    			ListHandler<ArrayList<String>> mySortHandler = new ListHandler<ArrayList<String>>(myList);	
	    			final String h = header.get(k);
	    			
	    			if(h.contains("id") || h.contains("Name") || h.equalsIgnoreCase("QueryContent") || 
	    					h.equalsIgnoreCase("WorkflowDescription")) {
	    				Column<ArrayList<String>, Anchor> colA = new Column<ArrayList<String>, Anchor>(
	    					new AnchorCell()){
								public Anchor getValue(ArrayList<String> ls) {
									String value = ls.get(kk);
									Anchor cell = new Anchor(value);
									String params="";
									
																		
									if(!paramMap.isEmpty()) {
										boolean firstParam = true;
										//search for the keys in the paramMap and introduce them in the new params list
										for(String key: paramList) {
											if(h.equalsIgnoreCase(key)){
												if(firstParam) {
													firstParam = false;
													if(h.equalsIgnoreCase(col)) {
														if(key.equalsIgnoreCase("WorkflowDescription")) {
															value = value.replace(" ", ";");
															if(!name.isEmpty()) {
																params += "?" + name + "=" + value;
															}
															else {
																params += "?" + "WorkflowName" + "=" + value;
															}
														}
														else if(key.equalsIgnoreCase("QueryContent")) {
															if(!name.isEmpty()) {
																params += "?" + name + "=" + value;
															}
															else {
																params += "?" + "QueryName" + "=" + value;
															}
														}
														else {
															if(!name.isEmpty()) {
																params += "?" + name + "=" + value;
															}
															else {
																params += "?" + key + "=" + value;
															}
														}
													}// !h = col
													else {
														if(key.equalsIgnoreCase("WorkflowDescription")) {
															value = value.replace(" ", ";");
															params += "?" + "WorkflowName" + "=" + value;
														}
														else if(key.equalsIgnoreCase("QueryContent")) {
															params += "?" + "QueryName" + "=" + value;
														}
														else {
															params += "?" + key + "=" + value;
														}
													}
												} // !first Parameter
												else {
													if(h.equalsIgnoreCase(col)) {
														if(key.equalsIgnoreCase("WorkflowDescription")) {
															value = value.replace(" ", ";");
															if(!name.isEmpty()) {
																params += "&" + name + "=" + value;
															}
															else {
																params += "&" + "WorkflowName" + "=" + value;
															}
														}
														else if(key.equalsIgnoreCase("QueryContent")) {
															if(!name.isEmpty()) {
																params += "&" + name + "=" + value;
															}
															else {
																params += "&" + "QueryName" + "=" + value;
															}
														}
														else {
															if(!name.isEmpty()) {
																params += "&" + name + "=" + value;
															}
															else {
																params += "&" + key + "=" + value;
															}
														}
													}//!h = col
													else {
														if(key.equalsIgnoreCase("WorkflowDescription")) {
															value = value.replace(" ", ";");
															params += "&" + "WorkflowName" + "=" + value;
														}
														else if(key.equalsIgnoreCase("QueryContent")) {
															params += "&" + "QueryName" + "=" + value;
														}
														else {
															params += "&" + key + "=" + value;
														}
													}													
												}		
											}//h != key
											else {
												if(!clearURLParameters.equalsIgnoreCase("clearURLParameters")) {
													if(paramMap.containsKey(key)){
														if(firstParam) {
															firstParam=false;															
															params += "?" + key + "=" + paramMap.get(key).get(0);
														}
														else {
															params += "&" + key + "=" + paramMap.get(key).get(0);
														}	
													}
												}
											}
										}// for paramList
										
										for(String x: paramMap.keySet()) {
											if(x.equalsIgnoreCase(name) && !params.contains(name)) {
												params += "&" + x + "=" + paramMap.get(x).get(0);
											}
											else if(!paramList.contains(x) && !x.equalsIgnoreCase(name)) {
												params += "&" + x + "=" + paramMap.get(x).get(0);											
											
											}
										}
									}//ParamMap = empty
									else {
										if(h.equalsIgnoreCase(col)) {
											if(h.equalsIgnoreCase("WorkflowDescription")) {
												value = value.replace(" ", ";");
												if(!name.isEmpty()) {
													params += "?" + name + "=" + value;
												}
												else {
													params += "?" + "WorkflowName" + "=" + value;
												}
											}
											else if(h.equalsIgnoreCase("QueryContent")) {
												if(!name.isEmpty()) {
													params += "?" + name + "=" + value;
												}
												else {
													params += "?" + "QueryName" + "=" + value;
												}												
											}
											else {
												if(!name.isEmpty()) {
													params += "?" + name + "=" + value;
												}
												else {
													params += "?" + h + "=" + value;
												}
											}											
										}
										else if(h.equalsIgnoreCase("WorkflowDescription")) { 
											value = value.replace(" ", ";");
											params += "?" + "WorkflowName" + "=" + value;
										}
										else if(h.equalsIgnoreCase("QueryContent")) {
											params += "?" + "QueryName" + "=" + value;
										}
										else {
											params += "?" + h + "=" + value;
										}
									}
																		
									//other parameters: chartCmd
									if(!chartCmd.isEmpty()) {
										params += "&chartCmd=" + chartCmd;
									}
									
									//other parameters: name, value pairs
									if(!name1.isEmpty() && !val1.isEmpty() && !paramMap.containsKey(name1)) {
										params += "&" + name1 + "=" + val1;
									}
									if(!name2.isEmpty() && !val2.isEmpty() && !paramMap.containsKey(name2)) {
										params += "&" + name2 + "=" + val2;
									}
									
									//Build path
									if(!targetPage.isEmpty()) {
										cell.setHref("http://" + Window.Location.getHost() + "/" + targetPage + "/" + params);
									}
									else {
										cell.setHref(Window.Location.getPath() + params);
									}
									
									return cell;
								}
	    				
		    			};
		    			colA.setSortable(true);
		    		
						mySortHandler.setComparator(colA,new Comparator<ArrayList<String>>() {
							public int compare(ArrayList<String> o1, ArrayList<String> o2) {
								if (o1 == o2) {
									return 0;
						        }
					            if (o1 != null) {
					              return (o2 != null) ? o1.get(kk).compareTo(o2.get(kk)) : 1;
					            }
					            return -1;
							}
						});

						myCellTable.addColumn(colA, header.get(k));
						if(h.contains("id")) {
							myCellTable.setColumnWidth(colA, 275.0, Unit.PX);
						}
						if(h.contains("WorkflowDescription") || h.contains("QueryContent")) {
							myCellTable.setColumnWidth(colA, 600.0, Unit.PX);
							
						}
						myCellTable.addColumnSortHandler(mySortHandler);
		    			
	    			}
	    			else {
	    				TextColumn<ArrayList<String>> colT = new TextColumn<ArrayList<String>>() {
		    				public String getValue(ArrayList<String> ls) {
								return ls.get(kk);
							}
						};
						
						colT.setSortable(true);
						mySortHandler.setComparator(colT,new Comparator<ArrayList<String>>() {
							public int compare(ArrayList<String> o1, ArrayList<String> o2) {
								if (o1 == o2) {
									return 0;
						        }
					            if (o1 != null) {
					              return (o2 != null) ? o1.get(kk).compareTo(o2.get(kk)) : 1;
					            }
					            return -1;
							}
						});

						myCellTable.addColumn(colT, header.get(k));
						//myCellTable.setColumnWidth(colT, 275.0, Unit.PX);
						myCellTable.addColumnSortHandler(mySortHandler);
	    			}   			
	    		}
	    	    
	    	    myCellTable.setTableLayoutFixed(false);
				//myCellTable.setStyleName("visCellTable");
	    	    
	    	    SimplePager myPager;
	    	    SimplePager.Resources myPagerRes = GWT.create(SimplePager.Resources.class);
	    	    int pageSizeInt = Integer.parseInt(pageSize);
	    	    myPager = new SimplePager(TextLocation.CENTER, myPagerRes, true, 2*pageSizeInt, true);
	    	    myPager.setVisible(true);
	    	    myPager.setDisplay(myCellTable);
	    	    myPager.setTitle("Metrics Table");
	    	    myPager.setPageStart(0);
	    	    myPager.setPageSize(pageSizeInt);
	    	    
	    	    VerticalPanel vp = new VerticalPanel();
	    	    vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    	    vp.setHeight("100px");
	    	   		    		
	    		vp.add(myCellTable);
	    	    vp.add(myPager);
	    	    RootPanel.get(portletId).add(vp);
	    	    //RootPanel.get().add(vp);
	    	}
	    });	
	}
	/* final NoSelectionModel<ArrayList<String>> selectionModel = new NoSelectionModel<ArrayList<String>>();
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
    	public void onSelectionChange(SelectionChangeEvent event) {
    		ArrayList<String> clickedRow = new ArrayList<String>(selectionModel.getLastSelectedObject());
    		String url="?page=caca.jsp";
    		for(int i = 0; i < clickedRow.size(); i++) {
    			url+="&" + header.get(i) + "=" + clickedRow.get(i);
    		}
    		Window.open(url, "Visualization", "");
       }
    });
    
    myCellTable.setSelectionModel(selectionModel); //add selection model to your celltable
    */	    	
}
