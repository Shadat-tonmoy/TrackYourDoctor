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


@WebServlet("/AddDoctorSchedule")
public class AddDoctorSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	boolean connectionSuccess = false;
	DBConnection connection;
    public AddDoctorSchedule() {
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
			String day = request.getParameter("day");
			String startHour = request.getParameter("startHour");
			String endHour = request.getParameter("endHour");
			//System.out.println(doctorId + clinicId + day + startHour + endHour);
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "insert into doctor_schedule (doctor_id,clinic_id,"
						+ "day,hour_start,hour_end) values ('"+doctorId+"','"+clinicId+"',"
						+ "'"+day+"','"+startHour+"','"+endHour+"')";
				int result = statement.executeUpdate(sql);
				if(result>0)
				{
					PrintWriter pw = response.getWriter();
					pw.println(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int rows;
    	}
	}

}
