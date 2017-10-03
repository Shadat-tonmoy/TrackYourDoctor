<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Doctor Profile</title>
</head>
<body>
<%
	Integer idObject = (Integer)session.getAttribute("doctor_id");
	int id = idObject.intValue();

%>
<h2 ng-init="doctorId=<%=id %>">* Are required</h2><h2>{{doctorId}}</h2>
<div class="row" ng-init="getDoctor(<%=id%>)">
	<div class="col-lg-8 user_detail">
	<form ng-submit="updateDoctorInfo(doctorDetail[0],<%=id%>)">
			<h4>First Name : </h4>	
			<h3><input type="text" ng-model="doctorDetail[0].firstName"/></h3>		
			<br><br>
			<h4>Last Name : </h4>
			<h3><input type="text" ng-model="doctorDetail[0].lastName"/></h3>
			<br><br>		
			<h4>Address : </h4>
			<h3><textarea cols="5" rows="2" ng-model="doctorDetail[0].address"></textarea></h3>			
			<br><br>
			<h4>Degree : </h4>
			<h3><textarea cols="5" rows="2" ng-model="doctorDetail[0].degree"></textarea></h3>
			<br><br>
			<h4>Field of Treatment : </h4>
			<h3><textarea cols="5" rows="2" ng-model="doctorDetail[0].fieldOfTreatment"></textarea></h3>
			<br><br>
			<h4>Chamber : </h4>
			<h3><textarea cols="5" rows="2" ng-model="doctorDetail[0].chamber"></textarea></h3>
			<br><br>
			<h4>Contact Number : </h4>
			<h3><input type="text" ng-model="doctorDetail[0].contact"/></h3>
			<br><br>
			<button>Submit</button>
			<button>Back</button>
	</form>

	</div>
</div>

</body>
</html>