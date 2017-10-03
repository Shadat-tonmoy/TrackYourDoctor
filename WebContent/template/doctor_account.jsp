<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./res/js/doctorInfoUpdate.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<%@page import="docs.JDBC" %>
<%@page import="docs.Doctor" %>

<jsp:useBean id="jdbc" class="docs.JDBC"></jsp:useBean>
<jsp:useBean id="myDoctor" class="docs.Doctor"></jsp:useBean>
<%
	boolean connected = jdbc.getConnection();
	char a='B',b='D'; 
	String firstName=null,lastName=null,email=null,password=null,address=null,fieldOfTreatment=null,degree=null,chamber=null,contactNumber=null;
	if(connected)
	{
		Integer idObject = (Integer)session.getAttribute("doctor_id");
		Integer newDoctor = (Integer)session.getAttribute("newDoctor");
		if(idObject!=null)
		{
			int id = idObject.intValue();
			//out.print(id);
			myDoctor = jdbc.getDoctor(id);
			firstName = myDoctor.getFirstName();
			lastName = myDoctor.getLastName();
			email = myDoctor.getEmail();
			password = myDoctor.getPassword();
			address = myDoctor.getAddress();
			fieldOfTreatment = myDoctor.getFieldOfTreatment();
			degree = myDoctor.getDegree();
			contactNumber = myDoctor.getContactNumber();
			chamber = myDoctor.getChamber();

			a = firstName.charAt(0);
			b = lastName.charAt(0);	
			
			if(newDoctor!=null)
			{
%>
	<div class="profile_msg">
		<h5>Welcome..As a new registered doctor you are requested to complete your profile.<span style="cursor:pointer" ng-click="isUpdate = true">Click Here To Update</span></h5>	
	</div>	
<%
			} 			
%>

			
	<div ng-init="scrollTo('update_success_msg')" id="update_success_msg" ng-show="isUpdated" class="profile_msg">
		<h5>Your Profile has been successfully updated</h5>	
	</div>
	
<div class="row" ng-init="id=<%= id %>; isUpdated=false; getDoctor(<%=id %>)">
	<div class="row">
		<div class="col-lg-8 user_detail">
			
			<h4>First Name : </h4>
			<h3>
				<span id="doctorFirstName" ng-hide="isUpdate">{{doctorDetail[0].firstName}}</span>
				
			</h3>
			<br><br>
			<h4>Last Name : </h4>
			<h3>
				<span ng-hide="isUpdate">{{doctorDetail[0].lastName}}</span>
						
			</h3>
			<br><br>
			<h4>Password : </h4>
			<h3>
				<span ng-hide="isUpdate">{{doctorDetail[0].password}}</span>
							
			</h3>
			<br><br>
			<h4>Email : </h4>
			<h3>
				<span>{{doctorDetail[0].email}}</span>		
			</h3>
			<br><br>
			<h4>Address : </h4>
			<h3>
				
				<span ng-if="doctorDetail[0].address != '' ">{{doctorDetail[0].address}}</span>
				<span ng-if="doctorDetail[0].address == '' ">Not Specified</span>
			</h3>
			
			<br><br>
			<h4>Degree : </h4>
			<h3>
				<span ng-if="doctorDetail[0].degree != '' ">{{doctorDetail[0].degree}}</span>
				<span ng-if="doctorDetail[0].degree == '' ">Not Specified</span>
						
			</h3>
			<br><br>
			<h4>Field of Treatment : </h4>
			<h3>
				
				<span ng-if="doctorDetail[0].fieldOfTreatment != '' ">{{doctorDetail[0].fieldOfTreatment}}</span>
				<span ng-if="doctorDetail[0].fieldOfTreatment == '' ">Not Specified</span>
			</h3>
			<br><br>
			<h4>Chamber : </h4>
			<h3>
				<span ng-if="doctorDetail[0].chamber != '' ">{{doctorDetail[0].chamber}}</span>
				<span ng-if="doctorDetail[0].chamber == '' ">Not Specified</span>
			</h3>
			<br><br>
			<h4>Contact Number : </h4>
			<h3>
				<span ng-if="doctorDetail[0].contact != '' ">{{doctorDetail[0].contact}}</span>
				<span ng-if="doctorDetail[0].contact == '' ">Not Specified</span>
			</h3>
			<br>
			<button><a href="#!update">Update Account</a></button>
			
			<button ng-click="isUpdate = false; updateFormSubmit(); " ng-show="isUpdate">Submit</button>
		
		</div>
		
		<div class="col-lg-4 pull-right">
			<div class="row">
				<h4 class="publisher_logo">
					<%= a + " " + b   %>	
				</h4>
			</div>
			<div class="row">
				<h4 class="welcome_msg">
					Welcome <%= firstName + " " + lastName %>				
				</h4>
				<hr>			
			</div>		
		</div>	
	</div>
</div>

<%
		}
		else
		{
%>
		<h2>You are not logged in.Plese Log in</h2>


<%
		}
	}
%>


</body>
</html>