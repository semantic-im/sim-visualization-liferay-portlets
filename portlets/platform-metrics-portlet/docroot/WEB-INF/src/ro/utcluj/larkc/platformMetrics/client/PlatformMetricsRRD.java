package ro.utcluj.larkc.platformMetrics.client;

import ro.utcluj.larkc.platformMetrics.client.view.RRDMetricsView;
import ro.utcluj.larkc.platformMetrics.shared.FieldVerifier;
import ro.utcluj.larkc.platformMetrics.shared.MetricEntries;
import ro.utcluj.larkc.platformMetrics.shared.MetricEntry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PlatformMetricsRRD implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	
	private static final String JSON_URL = "http://localhost:8182/test?dbtype=rrd&rrd=system_metrics&start=1309253603&end=1309340003&resolution=100";
	RRDMetricsView rrdMetricsView = null;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		
	    String url = URL.encode(JSON_URL) ;
	    rrdMetricsView = new RRDMetricsView();
	    

	    JsonpRequestBuilder jsonp = new JsonpRequestBuilder();

	    jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
	    	public void onFailure(Throwable throwable) {
	    		//System.out("Error: " + throwable);
	    	}

	    	public void onSuccess(MetricEntries metrics) {
	    		JsArray<MetricEntry> entries = metrics.getMetricEntries();
	    		rrdMetricsView.loadData(entries);
	    			    		
	    	}
	    });
	    
	    
		RootPanel.get("platform-metrics-portlet").add(rrdMetricsView);
	}
}
