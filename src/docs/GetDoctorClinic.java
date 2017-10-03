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
import org.json.*;
import org.json.simple.*;


@WebServlet("/GetDoctorClinic")
public class GetDoctorClinic extends HttpServlet {
	
	boolean connectionSuccess = false;
	DBConnection connection;
    
    public GetDoctorClinic() {
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
			Connection conn = connection.connection;
			try {
				Statement statement = conn.createStatement();
				ResultSet resultset = connection.resultset;
				String sql = "select * from clinic where doctor_id='"+id+"'";
				resultset = statement.executeQuery(sql);
				int size = 0;
				JSONArray jsonArray = new JSONArray();
				while(resultset.next())
				{
					String clinicId = resultset.getString("clinic_id");
					String clinicName = resultset.getString("clinic_name");
					String clinicAddress = resultset.getString("clinic_address");
					size++;
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", clinicId);
					jsonObject.put("clinicName", clinicName);
					jsonObject.put("clinicAddress", clinicAddress);
					jsonArray.add(jsonObject);
				}
				if(size==0)
				{
					PrintWriter pw = response.getWriter();
					pw.println("0");					
				}
				else 
				{
					PrintWriter pw = response.getWriter();
					pw.println(jsonArray);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
