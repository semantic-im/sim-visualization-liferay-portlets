package ro.utcluj.larkc.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class GetDatesServlet
 */
public class GetDatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnector dbConnector = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDatesServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = "";
		Map<?, ?> parameters = request.getParameterMap();
		if (parameters.containsKey("callback")){
			callback = request.getParameter("callback");
		}
		//print out the JSON object
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		
		Statement s = null;
		ResultSet rs = null;
		
		try {
			dbConnector = new DBConnector();
			s = dbConnector.conn.createStatement();
			
			String sqlStartDate = "select SettingValue from larkc.vis_settings where SettingName = 'StartDate';";
			String sqlEndDate = "select SettingValue from larkc.vis_settings where SettingName = 'EndDate';";
					
			//obtain the start date
			rs = s.executeQuery(sqlStartDate);
			while (rs.next()) {
				JSONObject row = new JSONObject();
				row.put("StartDate", rs.getString(1));
				jsonArray.put(row);
				
			}
			
			//obtain the end date
			rs = s.executeQuery(sqlEndDate);
			while (rs.next()) {
				JSONObject row = new JSONObject();
				row.put("EndDate", rs.getString(1));
				jsonArray.put(row);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				s.close();
				rs.close();
				dbConnector.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
		}
		
		out.println(callback + "(" + jsonArray.toString() + ");");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
