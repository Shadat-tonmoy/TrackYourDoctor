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


@WebServlet("/GetDoctorSchedule")
public class GetDoctorSchedule extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
	
    public GetDoctorSchedule() {
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
    		String doctorId = request.getParameter("id");
			int rows=0;
			
			Connection conn = connection.connection;
			Statement statement;
			try {
				statement = conn.createStatement();
				String sql = "select * from doctor_schedule join clinic on doctor_schedule.clinic_id"
						+ "=clinic.clinic_id where doctor_schedule.doctor_id='"+doctorId+"'";
				ResultSet resultSet = statement.executeQuery(sql);
				JSONArray jsonArray = new JSONArray();
				while(resultSet.next())
				{
					String scheduleId = resultSet.getString("schedule_id");
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
