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
<div ng-show="clinicAdded" style="margin-top:1%" class="row profile_msg" >
<h5>Clinic has been added.</h2>	
</div>


<div class="col-lg-6">
<form ng-submit="addDoctorClinic(<%=id %>,doctor)">
  <div class="form-group">
    <label 	for="name">Clinic Name :</label><br><br>
    <input 	type="text" 
			ng-model="doctor.clinicName"
			class="form-control" 
			placeholder="Enter Clinic Name" 
			name="clinicName"
			required="true"
			id="name"
			minlength="3">
  </div>
  <div class="form-group">
    <label 	for="address">Clinic Address : </label><br><br>
    <input 	type="text" 
			ng-model="doctor.clinicAddress"
			class="form-control" 
			placeholder="Enter Clinic Address" 
			name="clinicAddress"
			required="true"
			minlength="3"
			id="address">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  <a class="btn btn-info" href="#!managedoctorclinic">Back</a>
</form>


</div>




</body>
</html>