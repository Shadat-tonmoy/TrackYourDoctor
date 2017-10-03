package docs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddDoctorAppointment")
public class AddDoctorAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	boolean connectionSuccess = false;
	DBConnection connection;
	
    public AddDoctorAppointment() {
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
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(connectionSuccess)
    	{
    		String doctorId = request.getParameter("doctorId");
			String clinicId = request.getParameter("clinicId");
			String patientId = request.getParameter("patientId");
			String scheduleId = request.getParameter("scheduleId");
			String day = request.getParameter("date");
			//System.out.println(doctorId + clinicId + day + startHour + endHour);
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "insert into doctor_appointment (doctor_id,patient_id,clinic_id,schedule_id,date_of_appointment"
						+ ") values ('"+doctorId+"','"+patientId+"','"+clinicId+"','"+scheduleId+"',"
						+ "'"+day+"')";
				System.out.println(doctorId + " " + clinicId + " " + patientId + " " + day);
				int result = statement.executeUpdate(sql);
				if(result>0)
				{
					PrintWriter pw = response.getWriter();
					pw.println(result);
				}
				else 
				{
					PrintWriter pw = response.getWriter();
					pw.println("0");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int rows;
    	}
	
	}

}
