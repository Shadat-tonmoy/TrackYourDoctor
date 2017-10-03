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


@WebServlet("/PatientSignUp")
public class PatientSignUp extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    public PatientSignUp() {
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");
		System.out.println(email+password+firstName+lastName+dateOfBirth+address);
		String sql = "insert into user_info (email,password,user_type) values ('" + email + "','" + password + "'," + 2 + ")";
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
				String sql2 = "insert into patient_detail (user_id,first_name,last_name,email,date_of_birth,address,contact_no) values ('" + user_id + "','"+firstName + "','" + lastName + "','" + email + "','"+dateOfBirth+"','"+address+",'"+contactNo+"')"; 
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
