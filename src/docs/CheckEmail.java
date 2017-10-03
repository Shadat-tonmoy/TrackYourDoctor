package docs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class CheckEmail
 */
@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	public CheckEmail() {
        super();
        
    }

	public void init(ServletConfig config) throws ServletException {
		String driver = "com.mysql.jdbc.Driver";
		String db = "jdbc:mysql://localhost/db_project_final";
		String dbusername = "root";
    	String dbpassword = "root";
    	try {
    		Class.forName(driver);
    		conn = DriverManager.getConnection(db,dbusername,dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String userType = request.getParameter("userType");
		System.out.println(userType);
		String sql = "select user_id from user_info where email='"+email+"' and user_type='"+userType+"'";
		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);
			int found = 0;
			while(res.next())
			{
				found++;
			}
			if(found>0)
			{
				PrintWriter pw = response.getWriter();
				pw.print("duplicate");
			}
			else 
			{
				PrintWriter pw = response.getWriter();
				pw.print("ok");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		doGet(request, response);
	}

}
