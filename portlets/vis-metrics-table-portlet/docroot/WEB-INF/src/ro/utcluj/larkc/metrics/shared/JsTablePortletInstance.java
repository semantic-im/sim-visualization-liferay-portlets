package ro.utcluj.larkc.metrics.shared;

import com.google.gwt.core.client.JavaScriptObject;

public class JsTablePortletInstance extends JavaScriptObject{

	protected JsTablePortletInstance() {
	}

	public final native String getRenderURL() /*-{ return this.renderURL }-*/;

	public final native String getActionURL() /*-{ return this.actionURL }-*/;

	public final native String getResourceURL() /*-{ return this.resourceURL }-*/;
	
	public final native String getPortletID() /*-{ return this.portletID; }-*/;
	
	public final native String getMysqlQuery() /*-{ return this.mysqlQuery; }-*/;
	
	public final native String getUseQuery() /*-{ return this.useQuery; }-*/;
	
	public final native String getTableName() /*-{ return this.tableName; }-*/;
	
	public final native String getMetricName() /*-{ return this.metricName; }-*/;
	
	public final native String getUseMetrics() /*-{ return this.useMetrics; }-*/;
		
	public final native String getPageSize() /*-{ return this.pageSize; }-*/;
	
	public final native String getLimit() /*-{ return this.limit; }-*/;
	
	public final native String getChartCmd() /*-{ return this.chartCmd; }-*/;
	
	public final native String getTargetPage() /*-{ return this.targetPage; }-*/;
	
	public final native String getCol() /*-{ return this.paramCol; }-*/;
	
	public final native String getName() /*-{ return this.paramName; }-*/;
	
	public final native String getName1() /*-{ return this.paramName1; }-*/;
	
	public final native String getName2() /*-{ return this.paramName2; }-*/;
	
	public final native String getVal1() /*-{ return this.paramVal1; }-*/;
	
	public final native String getVal2() /*-{ return this.paramVal2; }-*/;
	
	public final native String getClearURLParameters() /*-{ return this.clearURLParameters; }-*/;
	
}
