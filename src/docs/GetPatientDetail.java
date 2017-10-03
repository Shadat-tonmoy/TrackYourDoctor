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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/GetPatientDetail")
public class GetPatientDetail extends HttpServlet {
	boolean connectionSuccess = false;
	DBConnection connection;
    public GetPatientDetail() {
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
    		String id = request.getParameter("id");
    		String sql = "select * from patient_detail join user_info on patient_detail.user_id = user_info.user_id where patient_detail.patient_id='"+id+"'";
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
					String contact = resultset.getString("contact_no");
					String dateOfBirth = resultset.getString("date_of_birth");
					if(address==null)
						address="";
					if(contact==null)
						contact="";
					//System.out.println(address + " "+ degree+ " "+fieldOfTreatment+" "+chamber + " "+contact);;
						
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("firstName", firstName);
					jsonObject.put("lastName", lastName);
					jsonObject.put("email", email);
					jsonObject.put("address", address);
					jsonObject.put("contact", contact);
					jsonObject.put("password", password);
					jsonObject.put("dateOfBirth", dateOfBirth);
					
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
