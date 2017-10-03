package docs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	static String driver = "com.mysql.jdbc.Driver";
	static String dburl = "jdbc:mysql://localhost/db_project_final";
	static String dbUserName = "root";
	static String dbPassword = "root";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultset = null;
	
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

}
