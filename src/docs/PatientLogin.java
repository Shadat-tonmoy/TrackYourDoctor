package docs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PatientLogin")
public class PatientLogin extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
	public PatientLogin() {
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
			String sql = "select patient_id from user_info join patient_detail on user_info.user_id=patient_detail.user_id where user_info.email='"+email+"' and user_info.password ='"+password+"' and user_type='2'";
			System.out.println(email+password);
			int rows;
			int patient_id=-1;
			try {
				Connection conn = connection.connection;
				Statement statement = conn.createStatement();
				ResultSet res = connection.resultset;
				res = statement.executeQuery(sql);
				rows = 0;
				while(res.next())
				{
					patient_id = res.getInt("patient_id");
					rows++;
				}
				if(rows>0)
				{
					PrintWriter pw = response.getWriter();
					pw.print("true");
					HttpSession session = request.getSession();
					session.setAttribute("patient_id", patient_id);	
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
