package docs;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateDoctorDetail")
public class UpdateDoctorDetail extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    
    public UpdateDoctorDetail() {
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
			String id = request.getParameter("id");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String degree = request.getParameter("degree");
			String fieldOfTreatment = request.getParameter("fieldOfTreatment");
			String contactNo = request.getParameter("contactNo");
			String chamber = request.getParameter("chamber");
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "update doctor_detail set first_name='"+firstName+"', "
						+ "last_name='"+lastName+"' , address='"+address+"', "
						+ "degree='"+degree+"', field_of_treatment='"+fieldOfTreatment+"', "
						+ "contact_number='"+contactNo+"', chamber='"+chamber+"'"
						+ " where doctor_id = '"+id+"'";
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
