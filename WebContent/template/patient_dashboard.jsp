<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="res/css/patient_dashboard_style.css"/>
</head>
<body>
<%
	Integer idObject = (Integer)session.getAttribute("patient_id");
	int patientId = -1;
	if(idObject!=null)
	{
		patientId = idObject.intValue();
	}

%>
<div class="row">
<span ng-show="noDoctorFoundToAppoint" class="noDoctorFoundMsg">Sorry No Doctor Found For Specific Date and Region. Please try with a different one</span>	

</div>
<div class="row ">
<span class="patientDateSelect">Choose Date and Disctrict for Doctor</span>
	<div class="col-lg-12 root">
		<div class="col-lg-6">
			<form class="form-inline">
				<div class="form-group" style="width:100%">
		    		<label class="control-label" for="appointmentDate">Date of Appointment : </label>
		    		<input 	type="date" style="width:60%" class="form-control" 
		    				id="appointmentDate"
		    				ng-model="appointment.date"
		    				placeholder="Search by Name,Address"
		    				/>
	  			</div>
			</form>
		</div>
		<div class="col-lg-6">
			<form class="form-inline">
				<div class="form-group" style="width:100%">
		    		<label class="control-label" for="district">District :</label>
		    		<select ng-model="appointment.district" style="width:40%" class="form-control" id="sort">
							<option selected="" disabled="" value="">Select District</option>
							<option value="Sylhet">Sylhet</option>
							<option value="Dhaka">Dhaka</option>
					</select>
	  			</div>
			</form>
		</div>
		<button type="button" ng-click="getDoctorForAppointmet(appointment,<%= patientId %>)">
          <span style="top:5px;" class="glyphicon glyphicon-search"></span> Search 
        </button>
	
	</div>
</div>
</body>
</html>