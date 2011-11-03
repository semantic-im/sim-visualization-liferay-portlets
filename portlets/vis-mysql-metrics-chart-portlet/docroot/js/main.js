function PortletInstance(mysqlQuery, charttype, chartcmd, idWorkflow, workflowName, idPlugin, pluginName,
						idMetric, groupOrderLimitClauses, platformLevel, metricName, 
						idSystem, systemName, idPlatform ,platformName, idQuery, queryName, appName,
						portletID,
						mysqlQuery2,chartcmd2,idWorkflow2,workflowName2,idPlugin2,pluginName2,
						idMetric2,groupOrderLimitClauses2,platformLevel2,metricName2,
						idSystem2, systemName2, idPlatform2 ,platformName2, idQuery2, queryName2,appName2,
						liveMode, refreshTime, preselectedPeriod, lineChartResolution, lineChartAggregateMethod, interpolateMode) {

	
	this.mysqlQuery = mysqlQuery;
	this.charttype = charttype;
    this.chartcmd = chartcmd;
    this.idWorkflow = idWorkflow;
    this.workflowName = workflowName;
    this.idPlugin = idPlugin;
    this.pluginName = pluginName;
    this.idMetric = idMetric;   
    this.groupOrderLimitClauses = groupOrderLimitClauses;  
    this.platformLevel = platformLevel;
    this.metricName = metricName;
    this.idSystem = idSystem;
    this.systemName = systemName;
    this.idPlatform = idPlatform;
    this.platformName = platformName;
    this.idQuery = idQuery;
    this.queryName = queryName;
    this.appName = appName;
    
    this.portletID = portletID;
    
    this.mysqlQuery2 = mysqlQuery2;
    this.chartcmd2 = chartcmd2;
    this.idWorkflow2 = idWorkflow2;
    this.workflowName2 = workflowName2;
    this.idPlugin2 = idPlugin2;
    this.pluginName2 = pluginName2;
    this.idMetric2 = idMetric2;   
    this.groupOrderLimitClauses2 = groupOrderLimitClauses2;  
    this.platformLevel2 = platformLevel2;
    this.metricName2 = metricName2;
    this.idSystem2 = idSystem2;
    this.systemName2 = systemName2;
    this.idPlatform2 = idPlatform2;
    this.platformName2 = platformName2;
    this.idQuery2 = idQuery2;
    this.queryName2 = queryName2;
    this.appName2 = appName2;
    
    this.liveMode = liveMode;
    this.refreshTime = refreshTime;
    this.preselectedPeriod = preselectedPeriod;
    this.lineChartResolution = lineChartResolution;
    this.lineChartAggregateMethod = lineChartAggregateMethod;
    this.interpolateMode = interpolateMode;
    
}


var portletInstances = []; 
