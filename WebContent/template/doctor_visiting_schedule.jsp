<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="res/css/doctor_visiting_schedule_style.css"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Insert title here</title>

</head>
<body>
<%
	Integer idObject = (Integer)session.getAttribute("doctor_id");
	int id=-1;
	if(idObject!=null)
		id = idObject.intValue();

%>
	
<a href="#!addvisitingschedule"><h1 class="addButton">+</h1></a>
<div class="row" ng-init="getDoctorSchedule(<%= id%>);scrollTo('deletedMsg')">
	<div ng-show="noScheduleFoundForDoctor">
		<h2>No Schedule Found</h2>
	</div> 
	
	
	<div ng-if="isScheduleUpdated" class="profile_msg">
		<h5>Schedule has been successfully updated</h5>	
	</div>
	
	<div id="deletedMsg" ng-if="isScheduleDeleted" class="profile_msg">
		<h5>Schedule has been successfully deleted</h5>	
	</div>
	
	<div ng-if="doctorScheduleAdded" class="profile_msg">
		<h5>Schedule has been successfully added</h5>	
	</div>
	
	

	<div ng-hide="noScheduleFoundForDoctor" class="row">
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
	
	
	 <div class="row root-row" ng-repeat="schedule in scheduleForDoctor | filter : search | orderBy:sort">
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
		 			<span class="schedule-text">Chamber : {{schedule.clinicName}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">Address : {{schedule.clinicAddress}}</span>
		 		</div>
		 		<div class="row">
		 			<span class="schedule-text">From {{schedule.startHour}} To {{schedule.endHour}}</span>
		 		</div>
	 		</div>
	 		<div class="col-lg-2">
	 			<a href="#!updatevisitingschedule" ng-click="getSingleSchedule(schedule.scheduleId,<%=id %>)">
	 				<i style="cursor:pointer;color:#000000;font-size: 2.5em;" 
	 				class="material-icons">mode_edit</i>
	 			</a>
	 			<i 	data-toggle="modal" 
	 				data-target="#confirmationModal" 
	 				style="cursor:pointer ; font-size: 2.5em;"
	 				class="material-icons"
	 				ng-click="passScheduleId(schedule.scheduleId)"
	 				>delete_forever</i>
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
        		<h4 class="modal-title">Are you sure to delete this schedule?</h4>
      		</div>
      		<div class="modal-body">
        		<center>
        			<h5 style="font-weight:bold">This Operation Will Permanently Delete This Schedule.Do You Want To Proceed?</h5>
        			<a 	style="width:15%; background:#d35400;
        				border:2px solid #d35400" 
        				class="btn btn-primary btn-md"
        				ng-click="deleteSchedule()"
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