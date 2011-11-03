package ro.utcluj.larkc.client;

import ro.utcluj.larkc.shared.MetricEntries;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vis_toolbar implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	
	
	/**
	 * This is the entry point method.
	 */
	
	
	public void onModuleLoad() {
		
		HorizontalPanel datePanel = new HorizontalPanel();
		HorizontalPanel ledsPanel = new HorizontalPanel();
		HorizontalPanel mainPanel = new HorizontalPanel();
		LarkcLeds leds = new LarkcLeds(ledsPanel);
		leds.getPlatformRunning();
		
		final LarkcDatePicker ldp = new LarkcDatePicker(datePanel);
		final Button refresh = new Button("refresh");
		final JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
		
		refresh.setStyleName("display:block; font-size: 16px;");
		refresh.setLayoutData("http://users.utcluj.ro/~negrum/images/gray_led.png");
		refresh.addClickHandler(new ClickHandler() {
				
			public void onClick(ClickEvent event) {
				/*Date startDate = ldp.getStartDate();
				Date endDate = ldp.getEndDate();
				String lBoxText =ldp.getListBoxText(); 
				*/
				ldp.checkDataErrorsAndUpdate();
				String url = URL.encode("http://localhost:8080/vis-toolbar-portlet/ToolbarServlet");
				url+="?StartDate=" + ldp.getStartDate();
				url+="&EndDate=" + ldp.getEndDate();
				
				//System.out.println(url);
				
				jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {
								
					public void onFailure(Throwable caught) {
						GWT.log("Error: " + caught);
						Window.Location.reload();
					}

					public void onSuccess(MetricEntries result) {
						Window.Location.reload();
						
					}
				});
			}
		});
		mainPanel.setSpacing(5);
		mainPanel.setStyleName("padding:5px;");
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		mainPanel.setStyleName("background-color:#FFFFFF;");
		mainPanel.add(ledsPanel);
		mainPanel.add(datePanel);
		mainPanel.add(refresh);
		RootPanel.get("vis-toolbar").add(mainPanel);
				
	}
}
