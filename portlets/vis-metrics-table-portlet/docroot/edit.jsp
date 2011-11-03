<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<%@ page import="com.liferay.portlet.MysqlAccessPortlet"%>
<%@ page import="java.util.ArrayList" %> 

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

<script type="text/javascript">
<%
	MysqlAccessPortlet metricAccess = new MysqlAccessPortlet();
	ArrayList<String> metricNames = null;
	ArrayList<String> tableLevels = metricAccess.getPlatformLevels();
	ArrayList<String> limitLevels = metricAccess.getLimit();
	ArrayList<String> pageSizeLevels = metricAccess.getPageSize();
	ArrayList<String> metricLevels = metricAccess.getMetricLevels();
	ArrayList<String> useQLevels = metricAccess.getUseQueryLevels();
	
%>
	
	function <portlet:namespace/>updateMetrics(selectedLevel) {
		if(selectedLevel=="Context") {
			document.getElementById('<portlet:namespace />editForm').metricName.disabled=true;	
		}
		else {
			document.getElementById('<portlet:namespace />editForm').metricName.disabled=false;
		}
		
	}
	
	function <portlet:namespace/>updateTextArea(selLevel) {
		
		if(selLevel=="yes") {
			document.getElementById('<portlet:namespace />editForm').mysqlQuery.disabled=false;
		}
		else {
			document.getElementById('<portlet:namespace />editForm').mysqlQuery.disabled=true;
		}
	}
    function  <portlet:namespace/>updateView(selectedLevel){
    	
		var metricSelect = document.getElementById('<portlet:namespace />editForm').metricName;
        	
    	if(selectedLevel.toLowerCase()=="platforms"){
    		metricSelect.options.length=0;	
    		<% metricNames = metricAccess.getMetricsNames("platforms"); %>
    		<%for (int i = 0; i < metricNames.size(); i++){%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>  
    	}else if(selectedLevel.toLowerCase()=="workflows"){
    		metricSelect.options.length=0;
    		<% metricNames = metricAccess.getMetricsNames("workflows"); %>
    		<%for (int i = 0; i < metricNames.size(); i++){%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>
    	}else if(selectedLevel.toLowerCase()=="plugins"){
    		metricSelect.options.length=0; 
    		<% metricNames = metricAccess.getMetricsNames("plugins"); %>
    		<%for (int i = 0; i < metricNames.size(); i++){%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>
    	}else if(selectedLevel.toLowerCase()=="queries"){
    		metricSelect.options.length=0;
    		<% metricNames = metricAccess.getMetricsNames("queries"); %>
    		<%for (int i = 0; i < metricNames.size(); i++){%>
				metricSelect.options[metricSelect.options.length]=new Option('<%=metricNames.get(i)%>', '<%=metricNames.get(i)%>');
			<%}%>
    	}	    
    }
</script>

<form id="<portlet:namespace />editForm" action="<%=editPortletUrl%>"
	method="post">
	
	<div style="color:#0066CC;"> Table Parameters: </div>
	<hr>
	<table>
		<tr>
			<td>Level: </td>
			<td> 	
				<select name="useMetrics" style="width:100%;" >
					<%for (int i = 0; i < metricLevels.size(); i++){%>
						<option 
							value='<%=metricLevels.get(i) %>' 
							<% if(metricLevels.get(i).equalsIgnoreCase(useMetrics)) {out.print("selected");} %> 
						><%=metricLevels.get(i)%></option>
					<%}%>	
				</select> 
			</td>
			
			<td style="padding-left:20px;">Page Size:</td>
			<td>    
				<select name="pageSize" style="width:100%; text-align:right;">
					<%for (int i = 0; i < pageSizeLevels.size(); i++){%>
						<option 
							value='<%=pageSizeLevels.get(i) %>' 
							<% if(pageSizeLevels.get(i).equalsIgnoreCase(pageSize)) {out.print("selected");} %> 
						><%=pageSizeLevels.get(i)%></option>
					<%}%>		
				</select>
			</td>
			
			<td style="padding-left:20px;"> Link Target Page: </td>
			<td> 
				<input type="text" name="targetPage" value ='<%=targetPage%>'/> 
			</td>
			
		</tr>
		<tr>
			<td>Visualization:</td>
			<td>
				<select name="tableName" style="width:100%;" onchange='<portlet:namespace/>updateView(this.options[this.options.selectedIndex].value);'>
					<%for (int i = 0; i < tableLevels.size(); i++){%>
						<option 
							value='<%=tableLevels.get(i) %>' 
							<% if(tableLevels.get(i).equalsIgnoreCase(tableName)) {out.print("selected");} %> 
						><%=tableLevels.get(i)%></option>
					<%}%>
				</select> 
			</td>
			
			<td style="padding-left:20px;">Result Limit:</td>
			<td>  
				<select name="limit" style="width:100%; text-align:right;"> 
					<%for (int i = 0; i < limitLevels.size(); i++){%>
						<option 
							value='<%=limitLevels.get(i) %>' 
							<% if(limitLevels.get(i).equalsIgnoreCase(limit)) {out.print("selected");} %> 
						><%=limitLevels.get(i)%></option>
					<%}%>					
				</select>
			</td>
			
			<td style="padding-left:20px;"> Chart Command: </td>
			<td> 
				<input type="text" name="chartCmd" value = '<%=chartCmd%>'/> 
			</td>
		</tr>
		<tr>
			<td> Metric: </td>
			<td> 	
				<select name="metricName" style="width:150px;">  
					<%
					metricNames = metricAccess.getMetricsNames(tableName);
					for (int i = 0; i < metricNames.size(); i++){%>
						<option 
							value='<%=metricNames.get(i) %>' 
							<% if(metricNames.get(i).equalsIgnoreCase(metricName)) {out.print("selected");} %> 
						><%=metricNames.get(i)%></option>
					<%}%>
				</select> 
			</td>
		</tr>
	</table>
	<br>
	<div style="color:#0066CC;"> MySQL Query:</div>
	<hr>
	<table width="100%">
		<tr>
			<td width="110px"> Use Mysql Query:</td>
			<td > 
				<select name="useQuery" onchange='<portlet:namespace/>updateTextArea(this.options[this.options.selectedIndex].value);'> 
					<%for (int i = 0; i < useQLevels.size(); i++){%>
						<option 
							value='<%=useQLevels.get(i) %>' 
							<% if(useQLevels.get(i).equalsIgnoreCase(useQuery)) {out.print("selected");} %> 
						><%=useQLevels.get(i)%></option>
					<%}%>		
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2"> 
				<textarea  Name="mysqlQuery" style="width: 100%; height: 99px;" 
					<% if(!useQuery.equalsIgnoreCase("yes")) out.print("disabled"); %> ><%=mysqlQuery%></textarea> 
			</td>
		</tr>
	</table>
	<br>
	<div style="color:#0066CC;"> Advanced Table Parameters:</div>
	<hr>
	
	<input type="checkbox" name="clearURLParameters" value="clearURLParameters" 	<%if (!clearURLParameters.isEmpty()) { out.print(" checked");}%> > &nbsp;Exclude existing URL parameters
	
	<table>
		<tr> 
			<td> Parameter 1: </td>
			<td style="padding-left: 20px;"> Name: </td>
			<td> <input type="text" name="paramName1" value ='<%=paramName1%>'/> </td>
			
			<td style="padding-left: 20px;"> Value: </td>
			<td> <input type="text" name="paramVal1" value ='<%=paramVal1%>'/> </td>
		</tr>
		<tr>
			<td> Parameter 2: </td>
						
			<td style="padding-left: 20px;"> Name: </td>
			<td> <input type="text" name="paramName2" value ='<%=paramName2%>'/> </td>
			
			<td style="padding-left: 20px;"> Value: </td>
			<td> <input type="text" name="paramVal2" value ='<%=paramVal2%>'/> </td>
		</tr>
		<tr>
			<td> Parameter 3: </td>
			<td style="padding-left: 20px;"> Column: </td>
			<td> <input type="text" name="paramCol" value ='<%=paramCol%>'/> </td>
			
			<td style="padding-left: 20px;"> Name: </td>
			<td> <input type="text" name="paramName" value ='<%=paramName%>'/> </td>
		</tr>
	</table>
	<input type="submit" id="nameButton" title="Save" value="Save">
</form>
