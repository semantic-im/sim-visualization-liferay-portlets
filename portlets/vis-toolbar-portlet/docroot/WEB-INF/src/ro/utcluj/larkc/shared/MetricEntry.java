package ro.utcluj.larkc.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;


public class MetricEntry extends JavaScriptObject {
	
	protected MetricEntry() {}

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
