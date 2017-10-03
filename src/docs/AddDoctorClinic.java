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


@WebServlet("/AddDoctorClinic")
public class AddDoctorClinic extends HttpServlet {
	
       
	boolean connectionSuccess = false;
	DBConnection connection;
    public AddDoctorClinic() {
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
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			System.out.println(id+" "+name+" "+address);
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				String sql = "insert into clinic (clinic_name,clinic_address,"
						+ "doctor_id) values ('"+name+"','"+address+"','"+id+"')";
				int result = statement.executeUpdate(sql);
				PrintWriter pw = response.getWriter();
				pw.println(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}
	}

}
