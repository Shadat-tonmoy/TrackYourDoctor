package docs;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateSingleSchedule")
public class UpdateSingleSchedule extends HttpServlet {
	
	boolean connectionSuccess = false;
	DBConnection connection;
    public UpdateSingleSchedule() {
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
			String clinicId = request.getParameter("clinicId");
			String day = request.getParameter("day");
			String startHour = request.getParameter("startHour");
			String endHour = request.getParameter("endHour");
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "update doctor_schedule set clinic_id='"+clinicId+"', "
						+ "day='"+day+"' , hour_start='"+startHour+"', "
						+ "hour_end='"+endHour+"' where schedule_id = '"+scheduleId+"'";
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
