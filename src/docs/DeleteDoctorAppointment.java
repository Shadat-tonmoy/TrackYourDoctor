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


@WebServlet("/DeleteDoctorAppointment")
public class DeleteDoctorAppointment extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    public DeleteDoctorAppointment() {
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
			String appointmentId = request.getParameter("appointmentId");
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "delete from doctor_appointment where appointment_id='"+appointmentId+"'";
				int rows = statement.executeUpdate(sql);
				if(rows>0)
				{
					PrintWriter pw = response.getWriter();
	        		pw.print("1");
				}
				else
				{
					PrintWriter pw = response.getWriter();
	        		pw.print("0");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	}

}
