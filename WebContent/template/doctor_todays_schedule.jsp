<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="res/css/doctor_visiting_schedule_style.css"/>
</head>
<body>
<%
	Integer idObject = (Integer)session.getAttribute("doctor_id");
	int id=-1;
	if(idObject!=null)
		id = idObject.intValue();

%>

<div ng-init="" class="col-lg-12">
	<div  ng-show="noAppointmentFoundForToday" class="noAppointForTodayDiv">
		<h2 class="noAppointForTodayMsg">No Appointmet found for Today </h2>
	</div>
	
	<div ng-hide="noAppointmentFoundForToday" class="row">
		<br><br>
		<h2 class="noAppointForTodayMsg" style="margin-top:0px;">{{todaysAppointmentForDoctor.length}} Appointmets found</h2>
		<h2 ng-show="doneTreatementMsg" class="consultingDoneMsg" style="margin-top:0px;">Consulting with Serial No {{lastSerialDone}} is Done</h2>
		<div class="col-lg-9">
			<form class="form-inline">
				<div class="form-group" style="width:100%">
    			<label class="control-label" for="search">Search :</label>
    			<input 	type="text" style="width:80%" class="form-control" 
    					id="search"
    					ng-model="search"
    					placeholder="Search by Day,Time,Chamber etc"
    					/>
  				</div>
			</form>
		</div>
	</div>
	
	
	
	
	<div class="row root-row" ng-show="appointment.isDone==0"
	ng-repeat="appointment in todaysAppointmentForDoctor | filter : search | orderBy:sort">
	 	<div class="col-lg-1 icon">
	 		<span class="round">
	 			<span class="text">
	 				{{appointment.serialNo}}
	 			</span>
	 		</span>
	 	</div>
	 	<div class="col-lg-10">
	 		<div class="col-lg-10">
		 		<div class="row">
		 			<span class="schedule-day">{{appointment.firstName + " " + appointment.lastName}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">Patient Address : Address will be available soon</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">Date of Birth : {{appointment.dateOfBirth}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">{{appointment.serialNo}}</span>
		 		</div>
	 		</div>
	 		<div class="col-lg-2">
	 			<img ng-click="doneTeatement(appointment.appointmentId)" style="width:35%;cursor:pointer;" src="res/images/treatmentDone.png"/>
	 			<a href="#!makeprescription">
	 				<img style="width:35%" src="res/images/prescription.png"/>
	 			</a>
	 			<img style="width:35%" src="res/images/viewHistory.png"/>
	 		</div>
	 	</div> 
	 </div>

</div> 

</body>
</html>