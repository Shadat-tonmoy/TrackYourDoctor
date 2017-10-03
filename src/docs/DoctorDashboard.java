package docs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DoctorDashboard")
public class DoctorDashboard extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    
    public DoctorDashboard() {
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
	
    	if(connectionSuccess)
    	{   		
    		
    		String isNew = request.getParameter("isNew");
    		String email = request.getParameter("email");
    		int doctor_id = -1;
    		if(isNew.equals("1"))
    		{
    			Connection conn = connection.connection;
    			try {
    				String sql = "select doctor_id from doctor_detail where email='"+email+"'";
					Statement statement = conn.createStatement();
					ResultSet res = statement.executeQuery(sql);
					while(res.next())
					{
						doctor_id = res.getInt("doctor_id");						
					}
					
	    			HttpSession session = request.getSession();
	    			session.setAttribute("doctor_id", doctor_id);
	    			session.setAttribute("newDoctor", 1);
	    			RequestDispatcher rd = request.getRequestDispatcher("/doctordashboard.jsp");
	    			rd.forward(request, response);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
    		}
    		else 
    		{
    			PrintWriter pw = response.getWriter();
        		pw.print("Problem with isNew");
    			
    		}
    	}
	
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    
	
	}

}
