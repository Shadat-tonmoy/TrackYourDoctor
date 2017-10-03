<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="res/css/patient_dashboard_style.css"/>
<link rel="stylesheet" href="res/css/doctor_list_to_appoint_style.css"/>
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
<div ng-init="getUpcomingAppointment(<%=patientId  %>)" class="noAppointForTodayDiv" ng-show="noUpcomingAppointment">
<span  class="noAppointForTodayMsg">Sorry No Upcoming Appointment Found</span>	

</div>
<span class="appointRequestSent" ng-show="appointmentDeleteSuccess">Appointment has been deleted</span>
<span ng-hide="noUpcomingAppointment" class="doctorFoundMsg" id="scrollArea">
You have {{upcomingAppointment.length}} upcoming appointments
</span>	

<div ng-hide="noUpcomingAppointment" class="doctorList" ng-style="{'margin-top':appointmentListMarginTop}">
<div class="row" ng-init="">
	<div class="row">
		<br><br>
		<div class="col-lg-6">
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
		
		<div class="col-lg-6">
			<form class="form-inline">
				<div class="form-group" style="width:100%">
    			<label class="control-label" for="sort">Sort By :</label>
    			<select ng-model="sort" style="width:40%" class="form-control" id="sort">
						<option selected="" disabled="" value="">Catagory</option>
						<option value="day">Date</option>
						<option value="scheduleNo">Schedule No</option>
						<option value="clinicName">Chamber</option>
						<option value="startHour">Visiting Start Time</option>
						<option value="endHour">Visiting End Time</option>
				</select>
  				</div>
			</form>
		</div>
	</div>
	
	
	 <div class="row root-row" ng-repeat="schedule in upcomingAppointment | filter : search | orderBy:sort">
	 	<div class="col-lg-1 icon">
	 		<span class="round">
	 			<span class="text">
	 				{{$index+1}}
	 			</span>
	 		</span>
	 	</div>
	 	<div class="col-lg-10">
	 		<div class="col-lg-10">
		 		<div class="row">
		 			<span class="schedule-day">{{schedule.day}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">{{schedule.firstName}} {{schedule.lastName}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">{{schedule.degree}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">{{schedule.fieldOfTreatment}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">Chamber : {{schedule.clinicName}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">Address : {{schedule.clinicAddress}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text"> Available From {{schedule.startHour}} To {{schedule.endHour}}</span>
		 		</div>
	 		</div>
	 		<div class="col-lg-2">
	 			<a  data-toggle="modal" 
	 				data-target="#confirmationModal"
	 				ng-click="asignAppointmentToDelete(schedule)">
	 				<i style="cursor:pointer;color:#000000;font-size: 2.5em;"
	 				class="material-icons">delete-forever</i>
	 			</a>
	 			
	 		</div>
	 	</div> 
	 </div>
</div>
</div>

<div id="confirmationModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

    <!-- Modal content-->
    	<div class="modal-content">
      		<div class="modal-header" style="background:#d35400;color:#ffffff">
        		<button style="color:#ffffff" type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title">Are you sure to cancel this appointment?</h4>
      		</div>
      		<div class="modal-body">
        		<center>
        			<h5 style="font-weight:bold">This Operation Will Cancel The Appointment To Your Doctor.Do You Want To Proceed?</h5>
        			<a 	style="width:15%; background:#d35400;
        				border:2px solid #d35400" 
        				class="btn btn-primary btn-md"
        				ng-click="cancelAppointment()"
        				data-dismiss="modal">
        				Yes</a>
        			<a 	style="width:15%; border:2px solid #ecf0f1;" 
        				class="btn btn-default btn-md" 
        				data-dismiss="modal">
        				No</a>
        		</center>
	      	</div>
    	</div>
  </div>
</div>









</body>
</html>