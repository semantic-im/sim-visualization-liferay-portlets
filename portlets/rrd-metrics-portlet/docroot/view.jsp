<%
/**
 *
 */
%>

<script type="text/javascript" language="javascript" src='<%=request.getContextPath()%>/sim_vis_platform_metrics_rrd/sim_vis_platform_metrics_rrd.nocache.js'></script>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>


<!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

<portlet:defineObjects />



<div id="platform-metrics-portlet">
</div>
    