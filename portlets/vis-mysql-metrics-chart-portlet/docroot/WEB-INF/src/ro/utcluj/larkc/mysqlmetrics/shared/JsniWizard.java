package ro.utcluj.larkc.mysqlmetrics.shared;

public class JsniWizard {
	
	public static native String getMetricServletPath()
    /*-{
        return $wnd.metricServletPath
        ;
    }-*/;
	
	public static native String getChartcmd()
    /*-{
        return $wnd.chartcmd
        ;
    }-*/;
	
	public static native String getidWorkflow()
    /*-{
        return $wnd.idWorkflow
        ;
    }-*/;
	
	public static native String getIdPlugin()
    /*-{
        return $wnd.idPlugin
        ;
    }-*/;
	
	public static native String getIdMetric()
    /*-{
        return $wnd.idMetric
        ;
    }-*/;
	
	public static native String getPortletID()
    /*-{
        return $wnd.portletID
        ;
    }-*/;

}
