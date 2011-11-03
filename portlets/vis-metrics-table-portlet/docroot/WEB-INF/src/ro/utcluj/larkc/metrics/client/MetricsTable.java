package ro.utcluj.larkc.metrics.client;

import ro.utcluj.larkc.metrics.shared.JsTablePortletInstance;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MetricsTable implements EntryPoint {
	
	public static native JsArray<JsTablePortletInstance> getMetricsPortletInstances()
	/*-{
	    return $wnd.metricsPortletInstances;
	}-*/;

	
	public static native String getMetricsTablePortletPath() /*-{
   		return $wnd.metricsTablePortletPath;
	}-*/;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		JsArray<JsTablePortletInstance> metricsPortletInstances = getMetricsPortletInstances();
		String myUrl = getMetricsTablePortletPath();
		//new MetricsTableInstance(null, myUrl);				
		
		for (int i = 0; i < metricsPortletInstances.length(); i++) {
			JsTablePortletInstance metricsInstance = metricsPortletInstances.get(i);
			GWT.log(metricsInstance.toString());
			new MetricsTableInstance(metricsInstance, myUrl);
			
			
			
		}
		
		
	}
	
}
