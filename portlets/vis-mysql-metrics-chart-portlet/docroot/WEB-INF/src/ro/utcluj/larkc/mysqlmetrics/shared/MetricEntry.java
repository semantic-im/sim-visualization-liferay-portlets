package ro.utcluj.larkc.mysqlmetrics.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;


public class MetricEntry extends JavaScriptObject {
	
	protected MetricEntry() {}

	// JSNI methods to get RRD Metrics
	
	public final native String getName() /*-{ return this.Name + ''}-*/;
	public final native String getMetricName() /*-{ return this.MetricName + ''}-*/;
//	public final native String getIDMetric() /*-{ return this.idMetric + ''}-*/; 
//	public final native String getIdPlugin() /*-{ return this.idPlugin + ''}-*/; 
	public final native String getValue() /*-{ return this.Value + ''}-*/;
//	public final native String getIdWorkflow() /*-{ return this.Workflows_idWorkflow + ''}-*/;
//	public final native String getMetricType() /*-{ return this.MetricType + ''}-*/;
	public final native String getTimestamp() /*-{ return this.Timestamp + ''}-*/;
//	
	
	public final native String get(String key) /*-{ 	       
		return "" + this[key]; 
	}-*/; 


	public final native JsArrayString keys() /*-{ 
       
		var a = new Array(); 	       
		for (var p in this) { a.push(p); }
		return a; 
	  
	}-*/; 

	 

	/*
	 * Takes in a trusted JSON String and evals it.
	 * @param JSON String that you trust
	 * @return JavaScriptObject that you can cast to an Overlay Type
	 */
	public static native String parseJson() /*-{
	  return this;
	}-*/;

	

}
