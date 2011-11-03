package ro.utcluj.larkc.metrics.shared;

import com.google.gwt.core.client.JavaScriptObject;

public class JsPortletInstance extends JavaScriptObject{

	protected JsPortletInstance() {
	}

	public final native String getRenderURL() /*-{ return this.rederURL }-*/;

	public final native String getActionURL() /*-{ return this.actionURL }-*/;

	public final native String getResourceURL() /*-{ return this.resourceURL }-*/;
	
	public final native String getPortletID() /*-{ return this.portletID; }-*/;
	
	public final native String getMysqlQuery() /*-{ return this.mysqlQuery; }-*/;
	
}
