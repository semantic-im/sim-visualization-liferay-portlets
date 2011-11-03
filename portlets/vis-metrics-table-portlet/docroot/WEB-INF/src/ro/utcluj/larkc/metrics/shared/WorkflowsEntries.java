package ro.utcluj.larkc.metrics.shared;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class WorkflowsEntries extends JavaScriptObject {
	// Overlay types always have protected, zero argument constructors.
	protected WorkflowsEntries() {}                                              

	public final native JsArray<WorkflowsEntry> getWorkflowsEntries() /*-{
  	 return this;
	}-*/;

	

}
