package docs;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DoctorSignUp")
public class DoctorSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean connectionSuccess = false;
	DBConnection connection;
    
    public DoctorSignUp() {
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
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(connectionSuccess)
		{			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String sql = "insert into user_info (email,password,user_type) values ('" + email + "','" + password + "'," + 1 + ")";
			int rows;
			try {
				Connection conn = connection.connection;
				Statement statement = conn.createStatement();
				ResultSet res = connection.resultset;
				rows = statement.executeUpdate(sql);
				if(rows>0)
				{
					String user_id_fetch = "select user_id from user_info where email='"+email+"'";
					res = statement.executeQuery(user_id_fetch);
					int user_id=-1;
					while(res.next())
					{
						
						user_id = res.getInt("user_id");						
					}
					String sql2 = "insert into doctor_detail (user_id,first_name,last_name,email) values ('" + user_id + "','"+firstName + "','" + lastName + "','" + email + "')"; 
					int rows2 = statement.executeUpdate(sql2);
					if(rows2>0)
					{
						PrintWriter pw = response.getWriter();
						pw.print("true");
					}
					else
					{
						PrintWriter pw = response.getWriter();
						pw.print("false");					
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
						
		}
		
	}

}
