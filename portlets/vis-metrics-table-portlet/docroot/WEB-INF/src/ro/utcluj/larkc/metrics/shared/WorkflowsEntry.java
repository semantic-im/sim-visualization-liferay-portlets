package ro.utcluj.larkc.metrics.shared;

import com.google.gwt.core.client.JavaScriptObject;


public class WorkflowsEntry extends JavaScriptObject {
	
	protected WorkflowsEntry() {}

	// JSNI methods to get Metrics
	
	public final native String getWorkflowId() /*-{ return this.WorkflowId + ''}-*/;
	public final native String getWorkflowDescription() /*-{ return this.WorkflowDescription + ''}-*/;
	
}
