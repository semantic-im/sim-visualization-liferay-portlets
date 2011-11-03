
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>


<jsp:useBean id="charttype" class="java.lang.String" scope="request"/>

<jsp:useBean id="chartcmd" class="java.lang.String" scope="request"/>
<jsp:useBean id="chartcmd2" class="java.lang.String" scope="request"/>

<jsp:useBean id="idSystem" class="java.lang.String" scope="request"/>
<jsp:useBean id="idSystem2" class="java.lang.String" scope="request"/>

<jsp:useBean id="systemName" class="java.lang.String" scope="request"/>
<jsp:useBean id="systemName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="idPlatform" class="java.lang.String" scope="request"/>
<jsp:useBean id="idPlatform2" class="java.lang.String" scope="request"/>

<jsp:useBean id="platformName" class="java.lang.String" scope="request"/>
<jsp:useBean id="platformName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="idWorkflow" class="java.lang.String" scope="request"/>
<jsp:useBean id="idWorkflow2" class="java.lang.String" scope="request"/>

<jsp:useBean id="workflowName" class="java.lang.String" scope="request"/>
<jsp:useBean id="workflowName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="idPlugin" class="java.lang.String" scope="request"/>
<jsp:useBean id="idPlugin2" class="java.lang.String" scope="request"/>

<jsp:useBean id="pluginName" class="java.lang.String" scope="request"/>
<jsp:useBean id="pluginName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="idQuery" class="java.lang.String" scope="request"/>
<jsp:useBean id="idQuery2" class="java.lang.String" scope="request"/>

<jsp:useBean id="queryName" class="java.lang.String" scope="request"/>
<jsp:useBean id="queryName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="idMetric" class="java.lang.String" scope="request"/>
<jsp:useBean id="idMetric2" class="java.lang.String" scope="request"/>

<jsp:useBean id="metricName" class="java.lang.String" scope="request"/>
<jsp:useBean id="metricName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="appName" class="java.lang.String" scope="request"/>
<jsp:useBean id="appName2" class="java.lang.String" scope="request"/>

<jsp:useBean id="platformLevel" class="java.lang.String" scope="request"/>
<jsp:useBean id="platformLevel2" class="java.lang.String" scope="request"/>

<jsp:useBean id="mysqlQuery" class="java.lang.String" scope="request"/>
<jsp:useBean id="mysqlQuery2" class="java.lang.String" scope="request"/>

<jsp:useBean id="groupOrderLimitClauses" class="java.lang.String" scope="request"/>
<jsp:useBean id="groupOrderLimitClauses2" class="java.lang.String" scope="request"/>

<jsp:useBean id="refreshTime" class="java.lang.String" scope="request"/>
<jsp:useBean id="preselectedPeriod" class="java.lang.String" scope="request"/>
<jsp:useBean id="liveMode" class="java.lang.String" scope="request"/>

<jsp:useBean id="infoMode" class="java.lang.String" scope="request"/>


<portlet:defineObjects />




<script type="text/javascript" language="javascript" src='<%=request.getContextPath()%>/vis_mysql_metrics/vis_mysql_metrics.nocache.js'></script>
<script type="text/javascript" language="javascript">

 
	
	metricServletPath='<%=request.getContextPath()%>/MetricServlet';
	
	//alert(portletInstances.length);
	//portletInstances[portletInstances.length] = new PortletInstance(portletID, "http://localhost:8080/web/guest/workflows?p_p_id=vismysqlmetricschart_WAR_vismysqlmetricschartportlet_INSTANCE_rMW5&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1", "http://localhost:8080/web/guest/workflows?p_auth=0knMUYf8&p_p_id=vismysqlmetricschart_WAR_vismysqlmetricschartportlet_INSTANCE_rMW5&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1", "http://localhost:8080/web/guest/workflows?p_p_id=vismysqlmetricschart_WAR_vismysqlmetricschartportlet_INSTANCE_rMW5&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_cacheability=cacheLevelPage&p_p_col_id=column-2&p_p_col_count=1",'getpluginmetrics', '2aaad516-a8ef-4511-8ffb-3e5d076f3d1a', '2', '48', '_vismysqlmetricschart_WAR_vismysqlmetricschartportlet_INSTANCE_rMW5_');
	portletInstances[portletInstances.length] = new PortletInstance('<%=mysqlQuery%>',
																	'<%=charttype%>', 
																	'<%=chartcmd%>', 
																	'<%=idWorkflow%>',
																	'<%=workflowName%>',
																	'<%=idPlugin%>',
																	'<%=pluginName%>', 
																	'<%=idMetric%>',
																	'<%=groupOrderLimitClauses%>',
																	'<%=platformLevel%>',
																	'<%=metricName%>',
																	'<%=idSystem%>',
																	'<%=systemName%>',
																	'<%=idPlatform%>',
																	'<%=platformName%>',
																	'<%=idQuery%>',
																	'<%=queryName%>',
																	'<%=appName%>',
																	'<portlet:namespace />',
																	'<%=mysqlQuery2%>',
																	'<%=chartcmd2%>', 
																	'<%=idWorkflow2%>',
																	'<%=workflowName2%>',
																	'<%=idPlugin2%>',
																	'<%=pluginName2%>',
																	'<%=idMetric2%>',
																	'<%=groupOrderLimitClauses2%>',
																	'<%=platformLevel2%>',
																	'<%=metricName2%>',
																	'<%=idSystem2%>',
																	'<%=systemName2%>',
																	'<%=idPlatform2%>',
																	'<%=platformName2%>',
																	'<%=idQuery2%>',
																	'<%=queryName2%>',
																	'<%=appName2%>'
																	);
	
	
    
  
</script>  


<div id="<portlet:namespace />">

</div>

