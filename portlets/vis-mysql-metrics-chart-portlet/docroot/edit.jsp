<%@ page import="com.liferay.portlet.MysqlAccessPortlet"%>
<%@ page import="java.util.ArrayList" %> 
<%@ include file="/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet_2_0" %> 
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>


<jsp:useBean class="java.lang.String" id="editPortletUrl" scope="request" />

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

<jsp:useBean id="lineChartResolution" class="java.lang.String" scope="request"/>
<jsp:useBean id="lineChartAggregateMethod" class="java.lang.String" scope="request"/>

<jsp:useBean id="infoMode" class="java.lang.String" scope="request"/>
<jsp:useBean id="interpolateMode" class="java.lang.String" scope="request"/>

<portlet:defineObjects />

<script type="text/javascript">
<%			
			MysqlAccessPortlet metricAccess = new MysqlAccessPortlet();
			
			ArrayList<String> metricNames = null;
			ArrayList<String> platformLevels = metricAccess.getPlatformLevels();
			ArrayList<String> chartTypes = metricAccess.getChartTypes();

			ArrayList<String> refreshTimeTexts = metricAccess.getRefreshTimeTexts();
			ArrayList<String> refreshTimeValues = metricAccess.getRefreshTimeValues();

			ArrayList<String> preselectedPeriodTexts = metricAccess.getPreselectedPeriodTexts();
			ArrayList<String> preselectedPeriodValues = metricAccess.getPreselectedPeriodValues();
			ArrayList<String> lineChartResolutions  = metricAccess.getLineChartResolutions();
			ArrayList<String> lineChartAggregateMethods  = metricAccess.lineChartAggregateMethods();
			
%>

    function  <portlet:namespace/>updateView(selectedID,selectedLevel){
    
    	var metricSelect = document.getElementById('<portlet:namespace />editForm').metricSelect;
		
    	if(selectedID =="visLevelSelect"){
			metricSelect = document.getElementById('<portlet:namespace />editForm').metricSelect;
		}else if(selectedID =="visLevelSelect2"){
			metricSelect = document.getElementById('<portlet:namespace />editForm').metricSelect2;
		}
    	
    	if(selectedLevel.toLowerCase()=="system"){
    		metricSelect.options.length=0;	
    		<%metricNames = metricAccess.getMetricsNames("system");%>
    		<%for (int i = 0; i < metricNames.size(); i++) {%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>  
    	}else if(selectedLevel.toLowerCase()=="platform"){
    		metricSelect.options.length=0;	
    		<%metricNames = metricAccess.getMetricsNames("platform");%>
    		<%for (int i = 0; i < metricNames.size(); i++) {%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>  
    	}else if(selectedLevel.toLowerCase()=="workflows"){
    		metricSelect.options.length=0;
    		<%metricNames = metricAccess.getMetricsNames("workflows");%>
    		<%for (int i = 0; i < metricNames.size(); i++) {%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>
    	}else if(selectedLevel.toLowerCase()=="plugins"){
    		metricSelect.options.length=0;
    		<%metricNames = metricAccess.getMetricsNames("plugins");%>
    		<%for (int i = 0; i < metricNames.size(); i++) {%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>
    	}else if(selectedLevel.toLowerCase()=="queries"){
    		metricSelect.options.length=0;
    		<%metricNames = metricAccess.getMetricsNames("queries");%>
    		<%for (int i = 0; i < metricNames.size(); i++) {%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>
    	}
    
			    
    }
    
    
    function <portlet:namespace/>enableDisableLiveMode(disableIt){   	
    	var elm = document.getElementById('<portlet:namespace />editForm').liveMode;
    	var refreshTimeSelect = document.getElementById('<portlet:namespace />editForm').visRefreshTimeSelect;
    	var preselectedPeriodSelect = document.getElementById('<portlet:namespace />editForm').visPreselectedPeriodSelect;
    	refreshTimeSelect.disabled = !(disableIt);
    	preselectedPeriodSelect.disabled = !(disableIt);
    }
    
    function <portlet:namespace/>enableDisableLineChartResolution(selectedLevel){
    	var chartResolution = document.getElementById('<portlet:namespace />editForm').lineChartResolution;
    	var chartAggregateMethod = document.getElementById('<portlet:namespace />editForm').aggregateMethodSelect;
    	var chartInterpolateMode = document.getElementById('<portlet:namespace />editForm').interpolateMode;
    
    	if(selectedLevel.toLowerCase()=="linechart"){
    		chartResolution.disabled = false;
    		chartAggregateMethod.disabled = false;
    		chartInterpolateMode.disabled = false;
    	}else{
    		chartResolution.disabled = true;
    		chartAggregateMethod.disabled = true;
    		chartInterpolateMode.disabled = true;
    	}
    }
    
</script>

<form id="<portlet:namespace />editForm" action="<%=editPortletUrl%>"
	method="post">
	
	<table>
		<tr><td colspan="9"><hr size="1px"></td></tr>
		<tr>
			<td>Chart Type:</td>
			<td>
			<select name="chartTypeSelect"
				onchange='<portlet:namespace/>enableDisableLineChartResolution(this.options[this.options.selectedIndex].value);'
			>
					<%
						for (int i = 0; i < chartTypes.size(); i++) {
					%>
						<option 
							value='<%=chartTypes.get(i)%>' 
							<%if (chartTypes.get(i).equalsIgnoreCase(charttype)) {
					out.print("selected");
				}%> 
						><%=chartTypes.get(i)%></option>
					<%
						}
					%>
			</select>
			</td>
			<td>Line Chart Resolution:</td>
			<td>
			
			<select name="lineChartResolution"
				<%if (!charttype.equalsIgnoreCase("linechart")) {
				out.print(" disabled");
				}%>>
					<option value="600">Line Chart Resolution</option>
						<%
						for (int i = 0; i < lineChartResolutions.size(); i++) {
						%>
							<option value='<%=lineChartResolutions.get(i)%>'
								<%if (lineChartResolutions.get(i).equalsIgnoreCase(lineChartResolution)) {
									out.print("selected");
								}
							%>>
							<%=lineChartResolutions.get(i)%>
					</option>
						<% } %>

			</select> 
			</td>
			
			<td>Aggregate Method:</td>
			<td>
			<select name="aggregateMethodSelect"
				<%if (!charttype.equalsIgnoreCase("linechart")) {
				out.print(" disabled");
				}%>>
					<option value="Average">Aggregate Method:</option>
						<%
						for (int i = 0; i < lineChartAggregateMethods.size(); i++) {
						%>
							<option value='<%=lineChartAggregateMethods.get(i)%>'
								<%if (lineChartAggregateMethods.get(i).equalsIgnoreCase(lineChartAggregateMethod)) {
									out.print("selected");
								}
							%>>
							<%=lineChartAggregateMethods.get(i)%>
					</option>
						<% } %>

			</select> 
			</td>
			<td align="right">Interpolate:</td>
			<td>
				<input type="checkbox" name="interpolateMode" value="interpolateMode" 
				<%if (!interpolateMode.isEmpty()) {
				out.print(" checked");
			}%>
				>
			</td>	
				
		</tr>
		<tr>
			<td>Live Mode:</td>
			<td>
				<input type="checkbox" name="liveMode" value="isLive" 
				onclick='<portlet:namespace/>enableDisableLiveMode(this.checked);'
				<%if (!liveMode.isEmpty()) {
				out.print(" checked");
			}%>
				>
			</td>
			
			<td>Refresh Time:</td>
			<td>
				<select name="visRefreshTimeSelect"		
				<%if (liveMode.isEmpty()) {
				out.print(" disabled");
			}%>
				>
						<option value="5000">Refresh Time</option>
						<%
							for (int i = 0; i < refreshTimeTexts.size(); i++) {
						%>
							<option 
								value='<%=refreshTimeValues.get(i)%>' 
								<%if (refreshTimeValues.get(i).equalsIgnoreCase(refreshTime)) {
					out.print("selected");
				}%> 
							><%=refreshTimeTexts.get(i)%></option>
						<%
							}
						%>
						
				</select> 
			</td>
			
			<td>Pre-selected period:</td>
			<td>
				<select name="visPreselectedPeriodSelect"
				<%if (liveMode.isEmpty()) {
				out.print(" disabled");
			}%>
				>
						<option value="60000">Preselected period</option>
						<%
							for (int i = 0; i < preselectedPeriodTexts.size(); i++) {
						%>
							<option 
								value='<%=preselectedPeriodValues.get(i)%>' 
								<%if (preselectedPeriodValues.get(i).equalsIgnoreCase(
						preselectedPeriod)) {
						out.print("selected");
				}%> 
							><%=preselectedPeriodTexts.get(i)%></option>
						<%
							}
						%>
	
				</select> 
			</td>
			
		</tr>
		
		<tr>
			<td>Info Mode:</td>
			<td>
				<input type="checkbox" name="infoMode" value="infoMode" 
				<%if (!infoMode.isEmpty()) {
				out.print(" checked");
			}%>
				>
			</td>
			
		</tr>
		
		<tr><td colspan="9"><hr size="1px"></td></tr>
		<tr><td colspan="9"><div style="font-size:9px; font-weight: bold; margin: 5px 5px 5px 5px;">Chart 1 Settings:</div></tr>
		
		<tr>
			<td>Visualization Level:</td>
			<td colspan="8" width="100%"><select name="visLevelSelect"
				onchange='<portlet:namespace/>updateView("visLevelSelect",this.options[this.options.selectedIndex].value);'>
					<option value="-1">Metrics Type</option>
					<%
						for (int i = 0; i < platformLevels.size(); i++) {
					%>
						<option 
							value='<%=platformLevels.get(i)%>' 
							<%if (platformLevels.get(i).equalsIgnoreCase(platformLevel)) {
					out.print("selected");
				}%> 
						><%=platformLevels.get(i)%></option>
					<%
						}
					%>

			</select> 
			<select name="metricSelect">
					<%
						if (!platformLevel.equalsIgnoreCase("-1")) {
							metricNames = metricAccess.getMetricsNames(platformLevel);
							for (int i = 0; i < metricNames.size(); i++) {
					%>
							<option 
								value='<%=metricNames.get(i)%>' 
								<%if (metricNames.get(i).equalsIgnoreCase(metricName)) {
						out.print("selected");
					}%> 
							><%=metricNames.get(i)%></option>
						<%
							}
						%>
					<%
						}
					%>
			</select>
			</td>
		</tr>
		
		<tr>
			
			<td>Command:</td>
			<td><input type="text" name="chartcmd" value="<%=chartcmd%>"></td>
			<td>Application Name:</td>
			<td><input type="text" name="appName" value="<%=appName%>"></td>
			<td>System ID:</td>
			<td><input type="text" name="idSystem" value="<%=idSystem%>"></td>	
			<td>System Name:</td>
			<td><input type="text" name="systemName" value="<%=systemName%>"></td>		
			
			
		</tr>
		<tr>
			<td>Platform ID:</td>
			<td><input type="text" name="idPlatform" value="<%=idPlatform%>"></td>	
			<td>Platform Name:</td>
			<td><input type="text" name="platformName" value="<%=platformName%>"></td>	
			<td>Workflow ID:</td>
			<td><input type="text" name="idWorkflow" value="<%=idWorkflow%>"></td>
			<td>Workflow Name:</td>
			<td><input type="text" name="workflowName" value="<%=workflowName%>"></td>
		</tr>
		<tr>
			<td>Plugin ID:</td>
			<td><input type="text" name="idPlugin" value="<%=idPlugin%>"></td>
			<td>Plugin Name:</td>
			<td><input type="text" name="pluginName" value="<%=pluginName%>"></td>
			<td>Query ID:</td>
			<td><input type="text" name="idQuery" value="<%=idQuery%>"></td>
			<td>Query Name:</td>
			<td><input type="text" name="queryName" value="<%=queryName%>"></td>
			
		</tr>
		<tr>
			<td>Metric ID:</td>
			<td><input type="text" name="idMetric" value="<%=idMetric%>"><br/><br/></td>
		</tr>
		<tr>
			
			<td>MySQL Query:</td>
			<td colspan="8" width="100%">
				<textarea  Name="mysqlQuery" rows="10" style="width: 100%; height: 99px;"><%=mysqlQuery%></textarea> 
				<br> 
			</td>
		</tr> 
		<tr>
			<td>Group by <br> Order by <br> Limit <br>clauses:</td>
			<td colspan="8" width="100%">
				<textarea  Name="groupOrderLimitClauses" rows="10" style="width: 100%; height: 99px;"><%=groupOrderLimitClauses%></textarea> 
				<br> 
			</td>
		</tr>
		
		
		
		
		
		<tr><td colspan="9"><div style="font-size:9px; font-weight: bold; margin: 5px 5px 5px 5px;">Chart 2 Settings:</div></tr>
		<tr>
			<td>Visualization Level:</td>
			<td colspan="8" width="100%"><select name="visLevelSelect2"
				onchange='<portlet:namespace/>updateView("visLevelSelect2",this.options[this.options.selectedIndex].value);'>
					<option value="-1">Metrics Type</option>
					<%
						for (int i = 0; i < platformLevels.size(); i++) {
					%>
						<option 
							value='<%=platformLevels.get(i)%>' 
							<%if (platformLevels.get(i).equalsIgnoreCase(platformLevel2)) {
					out.print("selected");
				}%> 
						><%=platformLevels.get(i)%></option>
					<%
						}
					%>

			</select> 
			<select name="metricSelect2">
					<%
						if (!platformLevel2.equalsIgnoreCase("-1")) {
							metricNames = metricAccess.getMetricsNames(platformLevel2);
							for (int i = 0; i < metricNames.size(); i++) {
					%>
							<option 
								value='<%=metricNames.get(i)%>' 
								<%if (metricNames.get(i).equalsIgnoreCase(metricName2)) {
						out.print("selected");
					}%> 
							><%=metricNames.get(i)%></option>
						<%
							}
						%>
					<%
						}
					%>
			</select>
			</td>
		</tr>
		
		<tr>
			
			<td>Command:</td>
			<td><input type="text" name="chartcmd2" value="<%=chartcmd2%>"></td>
			<td>Application Name:</td>
			<td><input type="text" name="appName2" value="<%=appName2%>"></td>
			<td>System ID:</td>
			<td><input type="text" name="idSystem2" value="<%=idSystem2%>"></td>	
			<td>System Name:</td>
			<td><input type="text" name="systemName2" value="<%=systemName2%>"></td>		
			
			
		</tr>
		<tr>
			<td>Platform ID:</td>
			<td><input type="text" name="idPlatform2" value="<%=idPlatform2%>"></td>	
			<td>Platform Name:</td>
			<td><input type="text" name="platformName2" value="<%=platformName2%>"></td>	
			<td>Workflow ID:</td>
			<td><input type="text" name="idWorkflow2" value="<%=idWorkflow2%>"></td>
			<td>Workflow Name:</td>
			<td><input type="text" name="workflowName2" value="<%=workflowName2%>"></td>
		</tr>
		<tr>
			<td>Plugin ID:</td>
			<td><input type="text" name="idPlugin2" value="<%=idPlugin2%>"></td>
			<td>Plugin Name:</td>
			<td><input type="text" name="pluginName2" value="<%=pluginName2%>"></td>
			<td>Query ID:</td>
			<td><input type="text" name="idQuery2" value="<%=idQuery2%>"></td>
			<td>Query Name:</td>
			<td><input type="text" name="queryName2" value="<%=queryName2%>"></td>
			
		</tr>
		<tr>
			<td>Metric ID:</td>
			<td><input type="text" name="idMetric2" value="<%=idMetric2%>"><br/><br/></td>
		</tr>
		<tr>
			<td>MySQL Query:</td>
			<td colspan="8" width="100%">
				<textarea  Name="mysqlQuery2" rows="10" style="width: 100%; height: 99px;"><%=mysqlQuery2%></textarea> 
				<br> 
			</td>
		</tr> 
		<tr>
			<td>Group by <br> Order by <br> Limit <br>clauses:</td>
			<td colspan="8" width="100%">
				<textarea  Name="groupOrderLimitClauses2" rows="10" style="width: 100%; height: 99px;"><%=groupOrderLimitClauses2%></textarea> 
				<br> 
			</td>
		</tr>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</table>
	<br>
	
	<br>
	<input type="submit" id="nameButton" title="Save" value="Save">
</form>
