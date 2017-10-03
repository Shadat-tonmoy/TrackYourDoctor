<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="res/js/angular.js" type="text/javascript"></script>
<script src="res/js/patient.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="res/css/publisher_style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body body ng-app="patientApp">
	
<%@ include file="bootstrap.html" %>

<div class="row">
	<div class="col-lg-3 left" style="position:fixed">
		<ul>
			<li><a href="#!/"> <span class="glyphicon glyphicon-home"> </span> Home</a></li>
			<%
				Integer idObject = (Integer)session.getAttribute("patient_id");
				if(idObject==null)
				{
			%>
					<li><a href="#!login"> <span class="glyphicon glyphicon-user"> </span> Login</a></li>
					<li><a href="#!signup"><span class="glyphicon glyphicon-plus"></span> Sign Up</a></li>		
			<%	
				}
				else 
				{
			%>
					<li><a href=""> <span class="glyphicon glyphicon-user"> </span> Your Account</a></li>
					<li><a href=""><span class="glyphicon glyphicon-circle-arrow-left"></span> Log Out</a></li>
			
			<%
				}
			
			%>
			
			<li><a href="#!test"><span class="glyphicon glyphicon-book"></span> Test</a></li>		
		</ul>
	</div>
	
	
	<div class="col-lg-9 right" ng-view >
	
		
		
	</div>
	

</div>
</body>
</html>