<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
					<a href="#!schedulefordate"><button ng-click="getScheduleForDate(date)" class="btn btn-xs btn-primary">View All Schedule</button></a>
					<button class="btn btn-xs btn-danger">Cancel All Schedule</button>
				</td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	
	
	</table>

</div>

</body>
</html>