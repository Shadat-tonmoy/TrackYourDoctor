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

@WebServlet("/DoctorLogin")
public class DoctorLogin extends HttpServlet {
	
	boolean connectionSuccess = false;
	DBConnection connection;
    public DoctorLogin() {
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
    		String email = request.getParameter("email");
			String password = request.getParameter("password");
			String sql = "select doctor_id from user_info join doctor_detail on user_info.user_id=doctor_detail.user_id where user_info.email='"+email+"' and user_info.password ='"+password+"' and user_type='1'";
			int rows;
			int doctor_id=-1;
			try {
				Connection conn = connection.connection;
				Statement statement = conn.createStatement();
				ResultSet res = connection.resultset;
				res = statement.executeQuery(sql);
				rows = 0;
				while(res.next())
				{
					doctor_id = res.getInt("doctor_id");
					rows++;
				}
				if(rows>0)
				{
					PrintWriter pw = response.getWriter();
					pw.print("true");
					HttpSession session = request.getSession();
					session.setAttribute("doctor_id", doctor_id);	
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
