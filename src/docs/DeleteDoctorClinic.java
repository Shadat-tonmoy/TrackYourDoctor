package docs;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteDoctorClinic")
public class DeleteDoctorClinic extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    public DeleteDoctorClinic() {
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
			String clinicId = request.getParameter("clinicId");
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String deleteClinic = "delete from clinic where clinic_id ="
						+ "'"+clinicId+"'";
				String deleteSchedule = "delete from doctor_schedule where clinic_id ="
						+ "'"+clinicId+"'";
				int rows = statement.executeUpdate(deleteClinic);
				if(rows>0)
				{
					Statement statement2 = conn.createStatement();
					rows = statement2.executeUpdate(deleteSchedule);
					if(rows>0)
					{
						PrintWriter pw = response.getWriter();
		        		pw.print("true2");						
					}
					else 
					{
						PrintWriter pw = response.getWriter();
		        		pw.print("true");	
		        		
					}					
				}
				else
				{
					PrintWriter pw = response.getWriter();
	        		pw.print("false");
	        		
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
