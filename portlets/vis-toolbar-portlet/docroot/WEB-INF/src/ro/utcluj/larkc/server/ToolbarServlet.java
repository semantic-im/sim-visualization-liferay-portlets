package ro.utcluj.larkc.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import ro.utcluj.larkc.server.DBConnector;

/**
 * Servlet implementation class ToolbarServlet
 */
public class ToolbarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnector dbConnector = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToolbarServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = "";
		String startDate = "";
		String endDate = "";
		String metricTable = "";
		
		Map<?, ?> parameters = request.getParameterMap();
		
		if(parameters.containsKey("StartDate")) {
			startDate = request.getParameter("StartDate");
		}
		if(parameters.containsKey("EndDate")) {
			endDate = request.getParameter("EndDate");
		}
		if(parameters.containsKey("Table")) {
			metricTable = request.getParameter("Table");
		}
		if (parameters.containsKey("callback")){
			callback = request.getParameter("callback");
		}
		
		//print out the JSON object
		PrintWriter out = response.getWriter();
		updateVisTable(metricTable, startDate, endDate);
		
		JSONArray jsonArray = new JSONArray();
		try {
			JSONObject row1 = new JSONObject();
			row1.put("StartDate", startDate);
			jsonArray.put(row1);
			
			JSONObject row2 = new JSONObject();
			row2.put("EndDate", endDate);
			jsonArray.put(row2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.println(callback + "(" + jsonArray.toString() + ");");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private int updateVisTable(String metricTable, String startDate, String endDate) {
		PreparedStatement s;
		dbConnector = new DBConnector();
		
		try {
			String sqlVisTemp = "update ignore larkc.vis_settings set SettingValue = ? where SettingName = ?;";
			s = dbConnector.conn.prepareStatement(sqlVisTemp);
			
			s.setTimestamp(1, Timestamp.valueOf(startDate));
			s.setString(2, "StartDate");
			s.executeUpdate();
			
			s.setTimestamp(1, Timestamp.valueOf(endDate));
			s.setString(2, "EndDate");
			s.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			try {
				dbConnector.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}
	

}
