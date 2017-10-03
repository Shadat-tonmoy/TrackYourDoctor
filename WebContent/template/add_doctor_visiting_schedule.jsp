<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="res/css/doctor_visiting_schedule_style.css"/>
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
<div style="" class="row" ng-init="getClinic(<%=id %>);">
	
	<div ng-show="noClinicFoundForDoctor">
		<h2>No clinic is added <a href="#!adddoctorclinic">Click here to add clinic</a></h2>
	</div>
	<h2 ng-hide="noClinicFoundForDoctor">Add Visiting Schedule</h2>

<h2 ng-show="doctorScheduleAdded" ng-init="schedule.startHour='';
			schedule.endHour='';schedule.clinicId='';schedule.day='';">Schedule has been added. 
	
	<a 	href="#!visitingschedule"> 
	Back To Your Schedule
	</a>
</h2>
	
<form ng-hide="noClinicFoundForDoctor" class="form-horizontal" ng-submit="addDoctorSchedule(<%=id%>,schedule)">
  	<div class="form-group">
    	<label class="control-label col-sm-2" for="clinic">Clinic :</label>
    	<div class="col-lg-4">
      		<select class="form-control" 
      				id="clinic" 
      				ng-model="schedule.clinicId"
      				required="true">
      			<option disabled=""
      					selected=""
      					value="">
      					Select Your Hospital/Clinic
      			</option>
				<option ng-repeat="clinic in clinicForDoctor"
						value="{{clinic.id}}">
					{{clinic.clinicName}}
				</option>
			</select>	
    	</div>
  	</div>
  	
  	<div class="form-group">
    	<label class="control-label col-sm-2" for="day">Day :</label>
    	<div class="col-lg-4">
      		<select class="form-control" 
      				id="day" 
      				ng-model="schedule.day"
      				required="true">
      				<option disabled=""
      					selected=""
      					value="">
      					Select Day
      			</option>
					<option ng-repeat="day in days" 
							value="{{day}}">
						{{day}}
					</option>
			</select>	
    	</div>
  	</div>
  	
	<div class="form-group">
    	<label class="control-label col-sm-2" for="hour_start">Starting Hour :</label>
    	<div class="col-lg-4">
    		<input 	type="text" 
    				id ="hour_start" 
    				class="form-control" 
    				placeholder="XX:YY AM/PM"
    				ng-model="schedule.startHour"
    				required="true"/>      		
    	</div>
  	</div>
  	
  	<div class="form-group">
    	<label class="control-label col-sm-2" for="hour_end">Ending Hour :</label>
    	<div class="col-lg-4">
    		<input 	type="text" 
    				class="form-control" 
    				id="hour_end" 
    				placeholder="XX:YY AM/PM"
    				ng-model="schedule.endHour"
    				required="true"	/>      		
    	</div>
  	</div>
  	
  	<div class="form-group"> 
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-primary">Submit</button>
	    </div>
  	</div>
</form>
	
	
	
	
	
</div>




</body>
</html>