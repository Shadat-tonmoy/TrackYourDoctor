package docs;
import java.sql.*;

public class JDBC {
	
	static String driver = "com.mysql.jdbc.Driver";
	static String dburl = "jdbc:mysql://localhost/db_project_final";
	static String dbUserName = "root";
	static String dbPassword = "root";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultset = null;
	static boolean moreThenOne = false;
	public static int id;
	public static boolean duplicate = false;
	public static Doctor doctor;
	public static Patient patient;
	public static boolean getConnection()
	{	
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			
			connection = DriverManager.getConnection(dburl,dbUserName,dbPassword);
			if(connection!=null)
				return true;
			else return false;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Doctor getDoctor(int id){
		
		try {
			statement = connection.createStatement();
			String sql = "select * from user_info join doctor_detail on user_info.user_id = doctor_detail.user_id where doctor_detail.doctor_id='"+id+"'";
			resultset = statement.executeQuery(sql);
			doctor = new Doctor();
			while(resultset.next())
			{
				int idDB = resultset.getInt("user_id");
				String firstName = resultset.getString("first_name");
				String lastName = resultset.getString("last_name");
				String password= resultset.getString("password");
				String email = resultset.getString("email");
				String address = resultset.getString("address");
				String degree = resultset.getString("degree");
				String fieldOfTreatment = resultset.getString("field_of_treatment");
				String chamber = resultset.getString("chamber");
				String contactNumber = resultset.getString("contact_number");
				doctor.setId(id);
				doctor.setFirstName(firstName);
				doctor.setLastName(lastName);
				doctor.setEmail(email);
				doctor.setPassword(password);
				doctor.setAddress(address);
				doctor.setDegree(degree);
				doctor.setFieldOfTreatment(fieldOfTreatment);
				doctor.setContactNumber(contactNumber);
				doctor.setChamber(chamber);
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return doctor;	
	}
	
public Patient getPatient(int id){
		
		try {
			statement = connection.createStatement();
			String sql = "select * from user_info join patient_detail on user_info.user_id = patient_detail.user_id where patient_detail.patient_id='"+id+"'";
			resultset = statement.executeQuery(sql);
			patient = new Patient();
			while(resultset.next())
			{
				int idDB = resultset.getInt("user_id");
				String firstName = resultset.getString("first_name");
				String lastName = resultset.getString("last_name");
				String password= resultset.getString("password");
				String email = resultset.getString("email");
				String address = resultset.getString("address");
				String contactNo = resultset.getString("contact_no");
				String dateOfBirth = resultset.getString("date_of_birth");
				patient.setId(id);
				patient.setFirstName(firstName);
				patient.setLastName(lastName);
				patient.setEmail(email);
				patient.setPassword(password);
				patient.setAddress(address);
				patient.setContactNo(contactNo);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return patient;	
	}
	
	

}
