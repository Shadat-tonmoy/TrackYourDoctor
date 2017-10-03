<%@page import="docs.JDBC" %>
<jsp:useBean id="jdbc" class="docs.JDBC"></jsp:useBean>
<%
	boolean result = false;
	String msg=null;
	if(request.getParameter("userType").equals("1"))
	{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String email  = request.getParameter("email");
		
		boolean connection = jdbc.getConnection();
		if(connection)
		{
			boolean insert = jdbc.insertPublisher(firstName, lastName, password, email, 1);
			if(insert)
			{
				//msg = "Done";
				result = true;
			}				
			else
			{
				//msg = "Error Occured";
				result = false;
			}
		}
	}
	else 
	{
		//msg = request.getParameter("userType");
		result = false;
	}
%>
	
	<%=
		result
	%>