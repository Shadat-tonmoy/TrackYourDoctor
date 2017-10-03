package docs;

import java.io.*;

import java.sql.*;
import org.json.*;
import org.json.simple.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/GetDoctorDetail")
public class GetDoctorDetail extends HttpServlet {
	
	boolean connectionSuccess = false;
	DBConnection connection;
	
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
    		String id = request.getParameter("id");
    		String sql = "select * from doctor_detail join user_info on doctor_detail.user_id = user_info.user_id where doctor_detail.doctor_id='"+id+"'";
    		//System.out.println("id : "+id);
    		
    		try {
    			Connection conn = (Connection) connection.connection;
				Statement statement = conn.createStatement();
				ResultSet resultset = statement.executeQuery(sql);
				JSONArray jsonArray = new JSONArray();
				while(resultset.next())
				{
					String firstName = resultset.getString("first_name");
					String lastName = resultset.getString("last_name");
					String email = resultset.getString("email");
					String password = resultset.getString("password");
					String address = resultset.getString("address");
					String degree = resultset.getString("degree");
					String fieldOfTreatment= resultset.getString("field_of_treatment");
					String chamber = resultset.getString("chamber");
					String contact = resultset.getString("contact_number");
					if(address==null)
						address="";
					if(degree==null)
						degree="";
					if(fieldOfTreatment==null)
						fieldOfTreatment="";
					if(chamber==null)
						chamber="";
					if(contact==null)
						contact="";
					//System.out.println(address + " "+ degree+ " "+fieldOfTreatment+" "+chamber + " "+contact);;
						
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("firstName", firstName);
					jsonObject.put("lastName", lastName);
					jsonObject.put("email", email);
					jsonObject.put("address", address);
					jsonObject.put("degree", degree);
					jsonObject.put("fieldOfTreatment", fieldOfTreatment);
					jsonObject.put("chamber", chamber);
					jsonObject.put("contact", contact);
					jsonObject.put("password", password);
					
					jsonArray.add(jsonObject);
				}
				//System.out.println(jsonArray);
				PrintWriter pw = response.getWriter();
				pw.println(jsonArray);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	
	}

}
