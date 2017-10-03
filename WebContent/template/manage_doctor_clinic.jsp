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
<a href="#!adddoctorclinic"><h1 class="addButton">+</h1></a>
<div ng-init="getClinic(<%=id %>)" style="" class="row" >
	<div id="clinicDeletedMsg" ng-if="isClinicDeleted" class="profile_msg">
		<h5>Clinic has been successfully deleted</h5>	
	</div>
<h2>Manage Here</h2>


<h2 ng-show="noClinicFoundForDoctor">No Clinic Found</h2>

<div ng-hide="noClinicFoundForDoctor" class="row">
	<br><br>
	<div class="col-lg-6">
		<form class="form-inline">
			<div class="form-group" style="width:100%">
    		<label class="control-label" for="searchClinic">Search :</label>
    		<input 	type="text" style="width:80%" class="form-control" 
    				id="searchClinic"
    				ng-model="searchClinic"
    				placeholder="Search by Name,Address"
    				/>
  			</div>
		</form>
	</div>
	<div class="col-lg-6">
		<form class="form-inline">
			<div class="form-group" style="width:100%">
    		<label class="control-label" for="sortClinic">Sort By :</label>
    		<select ng-model="sortClinic" style="width:40%" class="form-control" id="sort">
					<option selected="" disabled="" value="">Sort By</option>
					<option value="clinicName">Hospital/Clinic Name</option>
					<option value="clinicAddress">Hospital/Clinic Address</option>
			</select>
  			</div>
		</form>
	</div>
</div>



<table ng-hide="noClinicFoundForDoctor" class="table table-striped">
	<thead>
		<th>#</th>
		<th>Name</th>
		<th>Address</th>
		<th>Action</th>
	</thead>
	<tbody>
		<tr ng-repeat="clinic in clinicForDoctor | filter : searchClinic | orderBy:sortClinic">
			<td>{{$index+1}}</td>
			<td>{{clinic.clinicName}}</td>
			<td>{{clinic.clinicAddress}}</td>
			<td><a href="" ng-click="">
	 				<i style="cursor:pointer;color:#000000;font-size: 1.5em;" 
	 				class="material-icons">mode_edit</i>
	 			</a>
	 			<i 	data-toggle="modal" 
	 				data-target="#clinicConfirmationModal" 
	 				style="cursor:pointer ; font-size: 1.5em;"
	 				class="material-icons"
	 				ng-click="passClinicId(clinic.id)"
	 				>delete_forever</i>
	 		</div></td>
		</tr>	
	</tbody>
</table>	
</div>

<div id="clinicConfirmationModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

    <!-- Modal content-->
    	<div class="modal-content">
      		<div class="modal-header" style="background:#d35400;color:#ffffff">
        		<button style="color:#ffffff" type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title">Are you sure to delete this chamber?</h4>
      		</div>
      		<div class="modal-body">
        		<center>
        			<h5 style="font-weight:bold">This Operation Will Permanently Delete This Chamber and All Schedule With This Chamber.Do You Want To Proceed?</h5>
        			<a 	style="width:15%; background:#d35400;
        				border:2px solid #d35400" 
        				class="btn btn-primary btn-md"
        				ng-click="deleteClinic()"
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