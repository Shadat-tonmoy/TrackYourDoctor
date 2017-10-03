package docs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PatientDashboard")
public class PatientDashboard extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    public PatientDashboard() {
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
    		int patient_id = -1;
    		if(isNew.equals("1"))
    		{
    			Connection conn = connection.connection;
    			try {
    				String sql = "select patient_id from patient_detail where email='"+email+"'";
					Statement statement = conn.createStatement();
					ResultSet res = statement.executeQuery(sql);
					while(res.next())
					{
						patient_id = res.getInt("patient_id");						
					}
					
	    			HttpSession session = request.getSession();
	    			session.setAttribute("patient_id", patient_id);
	    			session.setAttribute("newPatient", 1);
	    			RequestDispatcher rd = request.getRequestDispatcher("/patientdashboard.jsp");
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
