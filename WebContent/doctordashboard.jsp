<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="res/css/dashboard_style.css" type="text/css"/>
<script src="res/js/angular.js" type="text/javascript"></script>
<script src="res/js/doctorDashboard.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>



<title>Dashboard</title>

</head>
<body ng-app="dashboardApp">
<%@ include file="bootstrap.html" %>
<div class="row" ng-controller="doctorDashboardController">
	<div class="col-lg-3 left" style="position:fixed">
		<ul>
			<li><a href="#!/"> <span class="glyphicon glyphicon-user"> </span> Dashboard</a></li>
			<li><a href="#!account"> <span class="glyphicon glyphicon-user"> </span> Your Account</a></li>
			<li><a href="#!visitingschedule"> <span class="glyphicon glyphicon-cloud-upload"> </span> Visiting Schedule</a></li>
			<li><a href="#!upcomingschedule"> <span class="glyphicon glyphicon-cloud-upload"> </span> Upcoming Schedule</a></li>
			<li><a href="#!notification"><span class="glyphicon glyphicon-bell"></span> Notifications </a></li>			
			<li><a href="#!managedoctorclinic"><span class="glyphicon glyphicon-folder-open"></span> Manage Chamber</a></li>
			<li><a href="logout.jsp"><span class="glyphicon glyphicon-circle-arrow-left"></span> Logout</a></li>		
		</ul>
	</div>
	
	<div class="col-lg-9 right" ng-view>
	
		
		
	</div>
	

</div>

</body>
</html>