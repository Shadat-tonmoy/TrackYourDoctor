package docs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/GetTodaysAppointmentForDoctor")
public class GetTodaysAppointmentForDoctor extends HttpServlet {
	
	boolean connectionSuccess = false;
	DBConnection connection;
	
    public GetTodaysAppointmentForDoctor() {
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
    		String date = request.getParameter("date");
    		String doctorId = request.getParameter("doctor_id");
    		String clinicId = request.getParameter("clinic_id");
    		
    		//System.out.println("Date is "+date);
			int rows=0;
			
			Connection conn = connection.connection;
			Statement statement;
			try {
				statement = conn.createStatement();
				String sql = "select * from doctor_appointment join doctor_schedule "
						+ "on doctor_appointment.schedule_id = doctor_schedule.schedule_id "
						+ "join clinic on doctor_appointment.clinic_id"
						+ "=clinic.clinic_id join patient_detail on "
						+ "doctor_appointment.patient_id = patient_detail.patient_id"
						+ " where doctor_appointment.date_of_appointment ='"+date+"' and "
								+ "doctor_appointment.doctor_id ='"+doctorId+"' and "
								+ "doctor_appointment.clinic_id ='"+clinicId+"' order by doctor_appointment.appointment_id";
				ResultSet resultSet = statement.executeQuery(sql);
				JSONArray jsonArray = new JSONArray();
				while(resultSet.next())
				{
					String appointmentId = resultSet.getString("appointment_id");
					//String clinicId = resultSet.getString("clinic_id");
					String clinicName = resultSet.getString("clinic_name");
					String clinicAddress = resultSet.getString("clinic_address");
					String firstName = resultSet.getString("first_name");
					String lastName = resultSet.getString("last_name");
					String dateOfBirth = resultSet.getString("date_of_birth");
					String address = resultSet.getString("address");
					int isDone = resultSet.getInt("isDone");
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("appointmentId", appointmentId);
					jsonObject.put("clinicId", clinicId);
					jsonObject.put("firstName", firstName);
					jsonObject.put("lastName", lastName);
					jsonObject.put("clinicName", clinicName);
					jsonObject.put("clinicAddress", clinicAddress);
					jsonObject.put("dateOfBirth", dateOfBirth);
					jsonObject.put("address", address);
					jsonObject.put("isDone", isDone);
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
