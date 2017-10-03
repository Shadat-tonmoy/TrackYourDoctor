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
<jsp:useBean id="myPatient" class="docs.Patient"></jsp:useBean>
<%
	boolean connected = jdbc.getConnection();
	char a='B',b='D'; 
	String firstName=null,lastName=null,email=null,password=null,address=null,fieldOfTreatment=null,degree=null,chamber=null,contactNo=null,dateOfBirth=null;
	if(connected)
	{
		Integer idObject = (Integer)session.getAttribute("patient_id");
		Integer newPatient = (Integer)session.getAttribute("newPatient");
		if(idObject!=null)
		{
			int id = idObject.intValue();
			//out.print(id);
			myPatient = jdbc.getPatient(id);
			firstName = myPatient.getFirstName();
			lastName = myPatient.getLastName();
			email = myPatient.getEmail();
			password = myPatient.getPassword();
			address = myPatient.getAddress();
			contactNo = myPatient.getContactNo();
			dateOfBirth = myPatient.getDateOfBirth();
			a = firstName.charAt(0);
			b = lastName.charAt(0);	
			
			if(newPatient!=null)
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
	
<div class="row" ng-init="id=<%= id %>; isUpdated=false; getPatient(<%=id %>)">
	<div class="row">
		<div class="col-lg-8 user_detail">
			
			<h4>First Name : </h4>
			<h3>
				<span id="patientFirstName" ng-hide="isUpdate">{{patientDetail[0].firstName}}</span>
				
			</h3>
			<br><br>
			<h4>Last Name : </h4>
			<h3>
				<span ng-hide="isUpdate">{{patientDetail[0].lastName}}</span>
						
			</h3>
			<br><br>
			<h4>Password : </h4>
			<h3>
				<span ng-hide="isUpdate">{{patientDetail[0].password}}</span>
							
			</h3>
			<br><br>
			<h4>Email : </h4>
			<h3>
				<span>{{patientDetail[0].email}}</span>		
			</h3>
			<br><br>
			<h4>Address : </h4>
			<h3>
				
				<span ng-if="patientDetail[0].address != '' ">{{patientDetail[0].address}}</span>
				<span ng-if="patientDetail[0].address == '' ">Not Specified</span>
			</h3>
			
			<br><br>
			<h4>Contact Number : </h4>
			<h3>
				<span ng-if="patientDetail[0].contact != '' ">{{patientDetail[0].contact}}</span>
				<span ng-if="patientDetail[0].contact == '' ">Not Specified</span>
			</h3>
			
			<br><br>
			<h4>Date of Birth : </h4>
			<h3>
				<span ng-if="patientDetail[0].dateOfBirth != '' ">{{date}} {{month}},{{year}}</span>
				<span ng-if="patientDetail[0].dateOfBirth == '' ">Not Specified</span>
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