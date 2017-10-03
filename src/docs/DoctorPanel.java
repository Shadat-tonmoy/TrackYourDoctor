package docs;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DoctorPanel extends HttpServlet {
	
    
    public DoctorPanel() {
        super();
    
    }
    public void init(ServletConfig config) throws ServletException {
    	
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter pw = response.getWriter();
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/doctor.jsp");
    	dispatcher.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
