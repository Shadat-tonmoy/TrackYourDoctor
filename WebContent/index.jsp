<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="res/css/index_style.css"/>
<script type="text/javascript" src="res/js/angular.js"></script>
<script type="text/javascript" src="res/js/script.js"></script>
<title>Home</title>
</head>
<body ng-app="mainApp">
<%@ include file="bootstrap.html" %>
<%@ include file="navbar.jsp" %>
<%@ page import="java.util.Date" %>
<br><br><br><br><br>
<div class="counter" ng-controller="counterController">
	
	<div class="row">
		<h3>We are getting ready within...</h3>	
	</div>
	<br><br>

	<div class="row">
		
		<h4>
			{{remainDay}}
		</h4>
		<h4>
			{{remainHour}}
		</h4>
		<h4>
			{{remainMinute}}
		</h4>
		<h4>
			{{remainSecond}}			
		</h4>
	</div>
	
	<div class="row">
		
		<span>Days</span>
		<span>Hours</span>
		<span>Minutes</span>
		<span>Seconds</span>			
	</div>
	
	
	
</div>
</body>
</html>
