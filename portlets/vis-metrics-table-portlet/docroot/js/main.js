function TablePortletInstance (useQuery, mysqlQuery, tableName, metricName, useMetrics, pageSize, 
							   limit, chartCmd, targetPage, paramCol, paramName, paramName1, paramName2, paramVal1, paramVal2,
							   portletID, clearURLParameters) { 
	this.useQuery = useQuery;
	this.mysqlQuery = mysqlQuery;
	this.tableName = tableName;
	this.metricName = metricName;
	this.useMetrics = useMetrics;
	this.pageSize = pageSize;
	this.limit = limit;
	this.chartCmd = chartCmd;
	this.targetPage = targetPage;
	this.paramCol = paramCol;
	this.paramName = paramName;
	this.paramName1 = paramName1;
	this.paramName2 = paramName2;
	this.paramVal1 = paramVal1;
	this.paramVal2 = paramVal2;
	this.portletID = portletID;
	this.clearURLParameters = clearURLParameters;
}
 
 var metricsPortletInstances = [];  