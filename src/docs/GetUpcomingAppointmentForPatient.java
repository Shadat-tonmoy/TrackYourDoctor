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


@WebServlet("/GetUpcomingAppointmentForPatient")
public class GetUpcomingAppointmentForPatient extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
	public GetUpcomingAppointmentForPatient() {
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
    		String patientId = request.getParameter("patientId");
    		String date = request.getParameter("date");
			int rows=0;
			
			Connection conn = connection.connection;
			Statement statement;
			try {
				statement = conn.createStatement();
				String sql = "select * from doctor_appointment join doctor_detail on doctor_appointment.doctor_id"
						+ "=doctor_detail.doctor_id join clinic on doctor_appointment.clinic_id = clinic.clinic_id "
						+ " join doctor_schedule on doctor_appointment.schedule_id = "
						+ "doctor_schedule.schedule_id where doctor_appointment.patient_id='"+patientId+"' "
						+ "and doctor_appointment.date_of_appointment >= '"+date+"'";
				ResultSet resultSet = statement.executeQuery(sql);
				JSONArray jsonArray = new JSONArray();
				while(resultSet.next())
				{
					String scheduleId = resultSet.getString("schedule_id");
					String appointmentId = resultSet.getString("appointment_id");
					String clinicId = resultSet.getString("clinic_id");
					String day = resultSet.getString("date_of_appointment");
					String startHour = resultSet.getString("hour_start");
					String endHour = resultSet.getString("hour_end");
					String clinicName = resultSet.getString("clinic_name");
					String clinicAddress = resultSet.getString("clinic_address");
					String doctorFirstName = resultSet.getString("first_name");
					String doctorLastName = resultSet.getString("last_name");
					String doctorDegree= resultSet.getString("degree");
					String doctorFieldOfTreatment= resultSet.getString("field_of_treatment");
					String doctorContactNo = resultSet.getString("contact_number");
					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("day", day);
					jsonObject.put("startHour", startHour);
					jsonObject.put("endHour", endHour);
					jsonObject.put("appointmentId", appointmentId);
					jsonObject.put("startHour", startHour);
					jsonObject.put("endHour", endHour);
					jsonObject.put("clinicName", clinicName);
					jsonObject.put("clinicId", clinicId);
					jsonObject.put("clinicAddress", clinicAddress);
					jsonObject.put("firstName",doctorFirstName);
					jsonObject.put("lastName",doctorLastName);
					jsonObject.put("degree",doctorDegree);
					jsonObject.put("fieldOfTreatment",doctorFieldOfTreatment);
					jsonObject.put("contactNo",doctorContactNo);
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
