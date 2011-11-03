
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<jsp:useBean class="java.lang.String" id="editPortletUrl" scope="request" />
<jsp:useBean id="useQuery" class="java.lang.String" scope="request"/>
<jsp:useBean id="mysqlQuery" class="java.lang.String" scope="request"/>
<jsp:useBean id="tableName" class="java.lang.String" scope="request"/>
<jsp:useBean id="metricName" class="java.lang.String" scope="request"/>
<jsp:useBean id="useMetrics" class="java.lang.String" scope="request"/>
<jsp:useBean id="pageSize" class="java.lang.String" scope="request"/>
<jsp:useBean id="limit" class="java.lang.String" scope="request"/>
<jsp:useBean id="chartCmd" class="java.lang.String" scope="request"/>
<jsp:useBean id="targetPage" class="java.lang.String" scope="request"/>

<jsp:useBean id="paramCol" class="java.lang.String" scope="request"/>
<jsp:useBean id="paramName" class="java.lang.String" scope="request"/>

<jsp:useBean id="paramName1" class="java.lang.String" scope="request"/>
<jsp:useBean id="paramName2" class="java.lang.String" scope="request"/>
<jsp:useBean id="paramVal1" class="java.lang.String" scope="request"/>
<jsp:useBean id="paramVal2" class="java.lang.String" scope="request"/>

<jsp:useBean id="clearURLParameters" class="java.lang.String" scope="request"/>

<portlet:defineObjects />

<script type="text/javascript" language="javascript" src='<%=request.getContextPath()%>/metricstable/metricstable.nocache.js'></script>

<script type="text/javascript" language="javascript">

	metricsTablePortletPath='<%=request.getContextPath()%>/MetricDBServlet';
	
	metricsPortletInstances[metricsPortletInstances.length] = new TablePortletInstance('<%=useQuery%>','<%=mysqlQuery%>','<%=tableName%>', '<%=metricName%>', '<%=useMetrics%>', '<%=pageSize%>', 
	'<%=limit%>', '<%=chartCmd%>', '<%=targetPage%>', '<%=paramCol%>', '<%=paramName%>', '<%=paramName1%>', '<%=paramName2%>', '<%=paramVal1%>', '<%=paramVal2%>', '<portlet:namespace />', '<%=clearURLParameters%>');
		 
</script>  

<div class="myMetricsScrol" id="<portlet:namespace />"> </div>
