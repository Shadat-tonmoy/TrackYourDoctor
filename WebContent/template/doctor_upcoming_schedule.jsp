<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="res/css/doctor_visiting_schedule_style.css"/>
<title>Insert title here</title>
</head>
<body>
<%
	Integer idObject = (Integer)session.getAttribute("doctor_id");
	int id=-1;
	if(idObject!=null)
		id = idObject.intValue();

%>

<div ng-init="getDateForUpcomingSchedule(<%=id %>);" class="col-lg-12">
	<h2 ng-show="allScheduleDeleteForDateSuccess" class="noAppointForTodayMsg" style="margin-top:0px;background:#27ae60">
		All Schedule is deleted for {{dateForCancelAllSchedule}}
	</h2>

	<table class="table table-striped">
		<thead>
			<th>Date</th>
			<th>Day</th>
			<th>Actions</th>
		</thead>
		
		<tbody>
			<tr ng-repeat="date in upcomingDateArray">
				<td>
					{{date.date}},{{date.month}} {{date.year}}
				</td>
				<td>
					{{date.day}}
				</td>
				<td>
					<a href="#!schedulefordate"><button ng-click="getScheduleForDate(date);noAppointmentFound = false;" class="btn btn-xs btn-primary">View All Schedule</button></a>
					<button 
						class="btn btn-xs btn-danger"
						data-toggle="modal" 
	 					data-target="#confirmationModal"
	 					ng-click="setDateForCancelAllSchedule(date)">Cancel All Schedule</button>
				</td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>

<div id="confirmationModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

    <!-- Modal content-->
    	<div class="modal-content">
      		<div class="modal-header" style="background:#d35400;color:#ffffff">
        		<button style="color:#ffffff" type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title">Are you sure to cancel all schedule for {{dateForCancelAllSchedule}}?</h4>
      		</div>
      		<div class="modal-body">
        		<center>
        			<h5 style="font-weight:bold">This Operation Will Permanently Cancel all the schedule for <b style="color:red">{{dateForCancelAllSchedule}}</b> .Do You Want To Proceed?</h5>
        			<a 	style="width:15%; background:#d35400;
        				border:2px solid #d35400" 
        				class="btn btn-primary btn-md"
        				ng-click="deleteAllScheduleForDate()"
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