package docs;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteDoctorSchedule")
public class DeleteDoctorSchedule extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    public DeleteDoctorSchedule() {
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
			String scheduleId = request.getParameter("scheduleId");
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "delete from doctor_schedule where schedule_id='"+scheduleId+"'";
				int rows = statement.executeUpdate(sql);
				if(rows>0)
				{
					PrintWriter pw = response.getWriter();
	        		pw.print("true");
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
