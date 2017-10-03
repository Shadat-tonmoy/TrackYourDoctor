
<%@page import="docs.JDBC"%>
<jsp:useBean id="jdbc" class="docs.JDBC"></jsp:useBean>
<%
	boolean connection = jdbc.getConnection();
	boolean loggedIn=false;
	int id = -1;
	if(request.getParameter("userName")!=null)
	{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		loggedIn = jdbc.loginPublisher(userName, password);
		if(loggedIn)
		{
			id = jdbc.id;
			session.setAttribute("id",id);
		}
	}
	
%>

<%=
	loggedIn
	
	
%>