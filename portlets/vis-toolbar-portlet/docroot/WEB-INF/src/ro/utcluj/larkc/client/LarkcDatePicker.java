package ro.utcluj.larkc.client;

import java.util.ArrayList;
import java.util.Date;
import ro.utcluj.larkc.shared.MetricEntry;
import ro.utcluj.larkc.shared.MetricEntries;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.datepicker.client.DatePicker;

@SuppressWarnings({ "rawtypes", "unchecked" })

public class LarkcDatePicker {
	
	Date startDate;
	Date endDate;
	String listBoxText;
	HorizontalPanel mp;
	DatePicker dpStart;
	DatePicker dpEnd;
	TextBox tbStart;
	TextBox tbEnd;
	Label lbStart;
	Label lbEnd;
	PopupPanel popStart;
	PopupPanel popEnd;
	String url = "http://localhost:8080/vis-toolbar-portlet/GetDatesServlet";
	ArrayList<String> data; 
	
	public LarkcDatePicker(HorizontalPanel p) {
		this.mp=p;
		mp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		mp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		mp.setSpacing(3);
		//mp.setWidth("1000");
		
		startDate = new Date();
		endDate = new Date();
		data = new ArrayList<String>();
		
		JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
		jsonp.requestObject(url, new AsyncCallback<MetricEntries>() {

			public void onFailure(Throwable caught) {
				GWT.log("Error: " + caught);
			}

			public void onSuccess(MetricEntries metrics) {
				JsArray<MetricEntry> entries  = metrics.getMetricEntries();
	    		JsArrayString header;
	    		for(int i = 0; i < entries.length(); i++) {
	    			header = entries.get(i).keys();
	    			data.add(entries.get(i).get(header.toString()));
	    		}
	    		startDate = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm:ss").parse(data.get(0));
	    		endDate = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm:ss").parse(data.get(1));
	    		tbStart.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(startDate));
	    		tbEnd.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(endDate));
	    		dpStart.setCurrentMonth(startDate);
	    		dpEnd.setCurrentMonth(endDate);
			} 
		});
		
		lbStart = new Label("Start Date:");
		lbEnd = new Label("End Date:");
				
		tbStart = new TextBox();
		tbEnd = new TextBox();
		//tbStart.setWidth("80");
		//tbEnd.setWidth("80");
		tbStart.setAlignment(TextAlignment.CENTER);
		tbEnd.setAlignment(TextAlignment.CENTER);
		//tbStart.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(startDate));
		//tbEnd.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(endDate));
		
		//tbStart.setReadOnly(true);
		//tbEnd.setReadOnly(true);
		
		dpStart = new DatePicker();
		dpEnd = new DatePicker();
		//dpStart.setVisible(false);
		//dpEnd.setVisible(false);
		
		dpStart.addValueChangeHandler(new ValueChangeHandler() {
		   	public void onValueChange(ValueChangeEvent event) {
		    	  startDate = (Date)event.getValue();
		          String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(startDate);
		          tbStart.setText(dateString);
		          popStart.hide();
		        }
		      });

		dpEnd.addValueChangeHandler(new ValueChangeHandler() {
		   	public void onValueChange(ValueChangeEvent event) {
		    	  endDate = (Date)event.getValue();
		    	  checkDataErrorsAndUpdate();
		    	  /*
		    	  if(startDate.compareTo(endDate)> 0){
		    		  endDate = startDate;
		    	  }
		    	  String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(endDate);
		          tbEnd.setText(dateString);
		          */
		          popEnd.hide();		          
		        }
		      });

		
		
		popStart = new PopupPanel(true);
		popEnd = new PopupPanel(true);
		popStart.add(dpStart);
		popEnd.add(dpEnd);
				
		tbStart.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popStart.setPopupPosition(event.getClientX(), event.getClientY()+20);
				popStart.show();
			}
		});
		tbEnd.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popEnd.setPopupPosition(event.getClientX(), event.getClientY()+20);
				popEnd.show();
				
			}
		});
		tbStart.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				try {
					String data = tbStart.getText();
					startDate = DateTimeFormat.getFormat("dd/MM/yyyy").parse(data);
					
					String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(startDate);
			        tbStart.setText(dateString);
			          
				} catch (Exception e) {
					tbStart.setText("Date Error");
				}
			}
		});
		
		tbEnd.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				try {
					String data = tbEnd.getText();
					endDate = DateTimeFormat.getFormat("dd/MM/yyyy").parse(data);
					checkDataErrorsAndUpdate();
					/*
					if(startDate.compareTo(endDate)> 0){
			    		  endDate = startDate;
			    	  }
			    	  String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(endDate);
			          tbEnd.setText(dateString);
					*/
				} catch (Exception e) {
					tbEnd.setText("Date Error");

				}
				
				
			}
		});
			
		//add elements to the Horizontal Panel
		mp.clear();
		mp.add(lbStart);
		mp.add(tbStart);
		mp.add(lbEnd);
		mp.add(tbEnd);
		mp.setVisible(true);
	}

	public String getStartDate() {
		String s = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm:ss").format(startDate);
		return s;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		String s = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm:ss").format(endDate);
		return s;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void checkDataErrorsAndUpdate() {
		if(startDate.compareTo(endDate)> 0){
  		  endDate = startDate;
  		}
		String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(endDate);
        tbEnd.setText(dateString);
	}
		

}
