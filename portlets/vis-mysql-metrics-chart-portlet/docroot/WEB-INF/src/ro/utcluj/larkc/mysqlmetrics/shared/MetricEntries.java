package ro.utcluj.larkc.mysqlmetrics.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class MetricEntries extends JavaScriptObject {
	// Overlay types always have protected, zero argument constructors.
	protected MetricEntries() {}                                              

	public final native JsArray<MetricEntry> getMetricEntries() /*-{
  	 return this;
	}-*/;

	

}
