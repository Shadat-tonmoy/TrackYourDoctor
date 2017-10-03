package docs;


import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.*;
import org.json.*;


@WebServlet("/GetSingleSchedule")
public class GetSingleSchedule extends HttpServlet {
	
	boolean connectionSuccess = false;
	DBConnection connection;
	
	public GetSingleSchedule() {
        super();
        
    }	
	public void init(ServletConfig config) throws ServletException {
		connection = new DBConnection();
    	if(connection.getConnection())
    	{
    		connectionSuccess = true;    		
    	}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(connectionSuccess)
    	{
			Connection conn = connection.connection;
			Statement statement;
    		String scheduleId = request.getParameter("scheduleId");
    		String doctorId = request.getParameter("doctorId");
    		String sql = "select * from doctor_schedule join clinic on doctor_schedule.clinic_id"
					+ "=clinic.clinic_id where doctor_schedule.schedule_id='"+scheduleId+"'";
			ResultSet resultSet;
			try {
				statement = conn.createStatement();
				resultSet = statement.executeQuery(sql);
				JSONArray jsonArray = new JSONArray();
				int rows=0;
				while(resultSet.next())
				{
					String sql2 = "select clinic_name,clinic_id from clinic where doctor_id='"+doctorId+"'";
					Statement statement2 = conn.createStatement();
					ResultSet resultSet2 = statement2.executeQuery(sql2);
					JSONArray jsonArray2 = new JSONArray();
					while(resultSet2.next())
					{
						String clinicName = resultSet2.getString("clinic_name");
						String clinicId = resultSet2.getString("clinic_id");
						JSONObject jsonObject2 = new JSONObject();
						jsonObject2.put("clinicName", clinicName);
						jsonObject2.put("clinicId", clinicId);
						jsonArray2.add(jsonObject2);
					}
					
					String clinicId = resultSet.getString("clinic_id");
					String day = resultSet.getString("day");
					String startHour = resultSet.getString("hour_start");
					String endHour = resultSet.getString("hour_end");
					String clinicName = resultSet.getString("clinic_name");
					String clinicAddress = resultSet.getString("clinic_address");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("scheduleId", scheduleId);
					jsonObject.put("day", day);
					jsonObject.put("startHour", startHour);
					jsonObject.put("endHour", endHour);
					jsonObject.put("clinicName", clinicName);
					jsonObject.put("clinicAddress", clinicAddress);
					jsonArray.add(jsonObject);
					jsonArray.add(jsonArray2);
					rows++;					
				}
				if(rows==0)
				{
					PrintWriter pw = response.getWriter();
					pw.println("0");
				}
				else
				{
					PrintWriter pw = response.getWriter();
					pw.println(jsonArray);					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
