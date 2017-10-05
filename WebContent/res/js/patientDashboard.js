var app = angular.module("patientDashboardApp",["ngRoute"]);
app.controller("patientDashboardController",function($scope,$rootScope,$http,$timeout,$interval,$location,$anchorScroll,$routeParams){
	this.myDate = new Date();
	this.isOpen = false;
	$scope.isUpdate = false;
	$scope.isUpdated = false;
	
	/*method to update doctor detail using ajax 
	 * */
	$scope.updateFormSubmit = function(){
		alert($scope.firstName);
		$http({
			method:"POST",
			url:"updatedoctordetail?firstName="+$scope.firstName+"&lastName="+$scope.lastName
			+"&password="+$scope.password+"&address="+$scope.address
			+"&chamber="+$scope.chamber+"&degree="+$scope.degree+"&fieldOfTreatment="+$scope.fieldOfTreatment
			+"&contactNo="+$scope.contactNo+"&id="+$scope.id
			
			}).then(function(response){
				if(response.data.trim()=="true")
				{
//					$scope.isRegistered = true;
//					$timeout(function(){
//						//alert("Now redirect");
//						window.location.replace("./doctordashboard?isNew=1&email="+$scope.email);
//					},2000);
					console.log("Success : "+response.data);
				}				
				
			},
				function(error)
				{
					console.log(error);
				}
			)	
	}
	
	
	/*method to get doctor detail information using ajax 
	 * */
	$scope.getPatient = function(id){
		
		$http({
			method:"GET",
			url:"getpatientdetail?id="+id
		}).then(function(response){
			$scope.patientDetail = response.data;
			var months = ["January","February","March","April",
				"May","June","July","August","September","October",
				"November","December"];
			var dateOfBirth =new Date($scope.patientDetail[0].dateOfBirth);
			var date = dateOfBirth.getDate();
			var month = dateOfBirth.getMonth();
			var year = dateOfBirth.getFullYear();
			$scope.date = date;
			$scope.month = months[month];
			$scope.year = year;
			console.log(response.data);
		})
	}
	
	
	/*method to update doctor detail information using ajax
	 * */
	$scope.updateDoctorInfo = function(doctor,id){
		var firstName = doctor.firstName;
		var lastName = doctor.lastName;
		var email = doctor.email;
		var password = doctor.password;
		var address = doctor.address;
		var degree = doctor.degree;
		var fieldOfTreatment = doctor.fieldOfTreatment;
		var chamber = doctor.chamber;
		var contact = doctor.contact;		
		$http({
			method:"POST",
			url:"updatedoctordetail?id="+id+"&firstName="+firstName+"&lastName="+lastName+"" +
					"&password="+password+"&address="+address+"&degree="+degree+"" +
					"&fieldOfTreatment="+fieldOfTreatment+"&contactNo="+contact+"" +
							"&chamber="+chamber+""
			
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="true")
			{
				$scope.isUpdated = true;
				window.location.href = "#!doctordashboard/updated"
				
			}
		})
	}
	
	/*method to scroll up 
	 * */
	
	$scope.scrollTo = function(scrollLocation){
		$location.hash(scrollLocation);
		$anchorScroll();
	}
	
	
	
	/*
	 * method to get available clinic
	 * */
	
	$scope.getClinic = function(id){
		$scope.noClinicFoundForDoctor = false;
		$http({
			method:"GET",
			url:"getdoctorclinic?id="+id
		}).then(function(response){
			console.log(response.data);
			var isObject = angular.isObject(response.data)
			if(isObject)
			{
				$scope.clinicForDoctor = response.data;
			}
			else 
			{
				if(response.data.trim()=="0")
				{
					$scope.noClinicFoundForDoctor = true;
					$scope.clinicForDoctor = null;
				}
			}
		})
	}
	
	
	
	/*
	 * method to get doctor for appointment
	 * */
	$scope.getDoctorForAppointmet = function(appointment,patientId){
		var days = ["Sunday","Monday","Tuesday","Wedensday",
			"Thrusday","Friday","Saturday"];
		var months = ["January","February","March","April",
			"May","June","July","August","September","October",
			"November","December"];	
		var date = appointment.date;
		var district = appointment.district;
		var dateToAppoint = new Date(date);
		var dayToAppoint = days[dateToAppoint.getDay()];
		var monthToAppoint = dateToAppoint.getMonth();
		var yearToAppoint = dateToAppoint.getFullYear();
		var spDateToAppoint = dateToAppoint.getDate();
		var appointmentDate = yearToAppoint+"-"+(monthToAppoint+1)+"-"+spDateToAppoint;
		$http({
			method:"GET",
			url:"getdoctortoappoint?patientId="+patientId+"&dayToAppoint="+dayToAppoint
			+"&monthToAppoint="+monthToAppoint+"&yearToAppoint="+
			yearToAppoint+"&dateToAppoint="+spDateToAppoint
		}).then(function(response){
			console.log(response.data);
			var isObject = angular.isObject(response.data)
			if(isObject)
			{
				$scope.doctorToAppoint= response.data;
				for(var i=0;i<$scope.doctorToAppoint.length;i++)
				{
					$scope.doctorToAppoint[i].date = appointmentDate;
				}
				window.location.href = "#!doctortoappoint";
			}
			else 
			{
				if(response.data.trim()=="0")
				{
					$scope.noDoctorFoundToAppoint = true;
					$scope.doctorToAppoint = null;
				}
			}
		})
	}
	$scope.assignScheduleToDoctor = function(schedule){
		$scope.scheduleToAppoint = schedule;
		console.log("Assigned");
	}
	
	
	/*
	 * method to appoint doctor
	 * */
	
	$scope.doctorListMarginTop = '5%';
	
	$scope.appointDoctor = function(doctor,patientId){
		var days = ["Sunday","Monday","Tuesday","Wedensday",
			"Thrusday","Friday","Saturday"];
		var months = ["January","February","March","April",
			"May","June","July","August","September","October",
			"November","December"];	
		var date = doctor.date;
		var doctorId = doctor.doctorId;
		var clinicId = doctor.clinicId;
		var scheduleId = doctor.scheduleId;
//		var dayToAppoint = dateToAppoint.getDay();
//		var monthToAppoint = dateToAppoint.getMonth();
//		var yearToAppoint = dateToAppoint.getFullYear();
		console.log("Doctor ID : " + doctorId + " clinicId : "+clinicId
				+ "Date " + date); //+ "-"+monthToAppoint+ "-"
//				+ dayToAppoint);
		$http({
			method:"POST",
			url:"adddoctorappointment?patientId="+patientId+"&doctorId="+
			doctorId+"&clinicId="+clinicId+"&date="+date+"&scheduleId="+scheduleId
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="1")
			{
				$rootScope.doctorAppointSuccess = true;
				$scope.doctorListMarginTop = '13%';
				$timeout(function(){
					//alert("Will hide");
					$rootScope.doctorAppointSuccess = false;
					$scope.doctorListMarginTop = '5%';
					
				},3000)
				
			}
			else 
			{
				
			}
		})
	}
	
	
	
	/*
	 * method to get upcoming appointment
	 * */
	
	$scope.getUpcomingAppointment = function(patientId){
		var days = ["Sunday","Monday","Tuesday","Wedensday",
			"Thrusday","Friday","Saturday"];
		var months = ["January","February","March","April",
			"May","June","July","August","September","October",
			"November","December"];

		var dateToParse = new Date();
		var year = dateToParse.getFullYear();
		var month = dateToParse.getMonth() + 1;
		var day = days[dateToParse.getDay()];
		var date = dateToParse.getDate();
		var finalDate = year + "-" + month + "-" + date;
		var finalDate2 = 2017 + "-" + 8 + "-" + 21;
		$http({
			method:"GET",
			url:"getupcomingappointmentforpatient?patientId="+patientId
			+ "&date="+finalDate
		}).then(function(response){
			console.log(response.data);
			var isObject = angular.isObject(response.data)
			if(isObject)
			{
				$scope.upcomingAppointment= response.data;
				$scope.noUpcomingAppointment = false;
				for(var i=0;i<$scope.upcomingAppointment.length;i++)
				{
					var dateToParse = $scope.upcomingAppointment[i].day;
					var dateToModify = new Date(dateToParse);
					var year = dateToModify.getFullYear();
					var month = months[dateToModify.getMonth()];
					var day = days[dateToModify.getDay()];
					var date = dateToModify.getDate();
					var finalDate = day + " | " + date +" "+month+" , "+year;
					$scope.upcomingAppointment[i].day = finalDate;
					//console.log(date + " Year : "+year+" Month : "+month+" day : "+day);
				}
//				window.location.href = "#!doctortoappoint";
			}
			else 
			{
				if(response.data.trim()=="0")
				{
					$scope.noUpcomingAppointment = true;
					$scope.upcomingAppointment = null;
				}
			}
		})
	}
	
	$scope.asignAppointmentToDelete = function(appointment)
	{
		$scope.appointmentToDelete = appointment;
//		console.log($scope.appointmentToDelete);
//		console.log(" assigned ");
	}
	
	/*
	 * method to cancel an appointment
	 * */
	
	
	$scope.appointmentListMarginTop = '5%';
	
	$scope.cancelAppointment = function(){
		
		var appointmentId = $scope.appointmentToDelete.appointmentId;
		console.log("Will delete "+appointmentId);
		$http({
			method:"POST",
			url:"deletedoctorappointment?appointmentId="+appointmentId
			
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="1")
			{
				$rootScope.appointmentDeleteSuccess = true;
				$scope.appointmentListMarginTop  = '13%';
				window.location.href = "#!upcomingappointment";
				$scope.scrollTo("#scrollArea");
				$timeout(function(){
					//alert("Will hide");
					$rootScope.appointmentDeleteSuccess = false;
					$scope.appointmentListMarginTop  = '5%';
					
				},3000)
				
			}
			else 
			{
				
			}
		})
	}
	
	/*
	 * method to get today's schedule
	 * */
	$scope.getTodaysAppointment = function(patientId){
		var days = ["Sunday","Monday","Tuesday","Wedensday",
			"Thrusday","Friday","Saturday"];
		var months = ["January","February","March","April",
			"May","June","July","August","September","October",
			"November","December"];	
		var dateToParse = new Date();
		var year = dateToParse.getFullYear();
		var month = dateToParse.getMonth() + 1;
		var day = days[dateToParse.getDay()];
		var date = dateToParse.getDate();
		var finalDate = year + "-" + month + "-" + date;
		var finalDate2 = 2017 + "-" + 10 + "-" + 7;
		console.log("Final Date : "+finalDate2);
		
		$http({
			method:"GET",
			url:"gettodaysappointmentforpatient?patientId="+patientId
			+ "&date="+finalDate2
		}).then(function(response){
			console.log(response.data);
			var isObject = angular.isObject(response.data)
			if(isObject)
			{
				$scope.todaysAppointment= response.data;
				$scope.noAppointmentForToday = false;
//				for(var i=0;i<$scope.upcomingAppointment.length;i++)
//				{
//					var dateToParse = $scope.upcomingAppointment[i].day;
//					var dateToModify = new Date(dateToParse);
//					var year = dateToModify.getFullYear();
//					var month = months[dateToModify.getMonth()];
//					var day = days[dateToModify.getDay()];
//					var date = dateToModify.getDate();
//					var finalDate = day + " | " + date +" "+month+" , "+year;
//					$scope.upcomingAppointment[i].day = finalDate;
//					//console.log(date + " Year : "+year+" Month : "+month+" day : "+day);
//				}
//				window.location.href = "#!doctortoappoint";
			}
			else 
			{
				if(response.data.trim()=="0")
				{
					$scope.noAppointmentForToday = true;
					$scope.todaysAppointment = null;
				}
			}
		})
	}
	
	
	
	/*
	 * method to get single schedule
	 * */
	$scope.getSingleSchedule = function(scheduleId,doctorId){
		//var id = $routeParams.id;
		//alert(scheduleId+" dc "+doctorId);
		$http({
			method:"GET",
			url:"getsingleschedule?scheduleId="+scheduleId+"&doctorId="+doctorId
		}).then(function(response){
			console.log(response.data);
			$rootScope.singleSchedule = response.data;
			
		})
		
	}
	
		
	/*
	 * method to update schedule
	 * */
	$scope.updateSchedule = function(singleSchedule){
		var scheduleId = singleSchedule.scheduleId;
		var clinicId = singleSchedule.clinicId;
		var day = singleSchedule.day;
		var startHour = singleSchedule.startHour;
		var endHour = singleSchedule.endHour;
		
		$http({
			method:"POST",
			url:"updatesingleschedule?scheduleId="+scheduleId+
			"&clinicId="+clinicId+"&day="+day+"&startHour="+startHour+
			"&endHour="+endHour
			
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="true")
			{
				$rootScope.isScheduleUpdated = true;				
				window.location.href = "#!visitingschedule";
				$timeout(function(){
					//alert("Will hide");
					$rootScope.isScheduleUpdated = false;
				},3000)

					
				
			}
		})
	}
	$scope.scheduleIdToDelete = -1;
	$scope.passScheduleId = function(id){		
		$rootScope.scheduleIdToDelete=id;
	}
	
	$scope.deleteSchedule = function(){
		var id = $rootScope.scheduleIdToDelete;
		$http({
			method:"POST",
			url:"deletesingleschedule?scheduleId="+id
			
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="true")
			{
				
				$rootScope.isScheduleDeleted = true;				
				window.location.href = "#!visitingschedule";
				$timeout(function(){
					//alert("Will hide");
					$rootScope.isScheduleDeleted = false;
				},3000)
			}
		})		
	}
	
	
	$scope.clinicIdToDelete = -1;
	$scope.passClinicId = function(id){		
		$rootScope.clinicIdToDelete=id;
	}
	
	$scope.deleteClinic = function(){
		var id = $rootScope.clinicIdToDelete;
		//alert(id);
		$http({
			method:"POST",
			url:"deletedoctorclinic?clinicId="+id
			
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="true" || response.data.trim()=="true2")
			{
				
				$rootScope.isClinicDeleted = true;				
				window.location.href = "#!managedoctorclinic";
				$timeout(function(){
					//alert("Will hide");
					$rootScope.isClinicDeleted = false;
				},3000)
			}
		})		
	}
	
	
	$scope.getDateForUpcomingSchedule = function(id){
		//$scope.getDoctorSchedule(id);
		//alert("func2");
		console.log("ans is "+$rootScope.bal);
		var days = ["Sunday","Monday","Tuesday","Wedensday",
			"Thrusday","Friday","Saturday"];
		var months = ["January","February","March","April",
			"May","June","July","August","September","October",
			"November","December"];		
		var fullDate = new Date();
		var currentFullDate = new Date();
		var currentDate = currentFullDate.getDate();
		var currentYear = currentFullDate.getFullYear();
		var currentMonth = currentFullDate.getMonth();
		var lastFullDate = new Date("31 December,"+currentYear); 
		console.log(currentFullDate);
		console.log(lastFullDate);
		$scope.date = fullDate.getDate();
		$scope.day = days[fullDate.getDay()];
		$scope.month = fullDate.getMonth();
		$scope.year = fullDate.getFullYear();
		
		var dateArray = new Array();
		for(var imonth=currentMonth;imonth<12;imonth++)
		{
			var startDate=-1;
			if(imonth==currentMonth)
				startDate = currentDate;
			else startDate = 1;
			for(var idate=startDate;idate<=31;idate++)
			{
				
				var nextFullDate = new Date(idate+" "+months[imonth]+ ",2017");
				var date = nextFullDate.getDate();
				var day = days[nextFullDate.getDay()];
				var month = months[nextFullDate.getMonth()];
				var year = nextFullDate.getFullYear();
				var dateObject = {"date":date,"day":day,"month":month,
						"year":year};
				dateArray.push(dateObject);
			}
		}
		$scope.upcomingDateArray = dateArray;
	}
	$scope.lastConsulted = 0;
	$scope.getLiveSerial = function(patientId,doctorId,clinicId)
	{
		var dateToParse = new Date();
		var year = dateToParse.getFullYear();
		var month = dateToParse.getMonth() + 1;
		var date = dateToParse.getDate();
		var finalDate = year + "-" + month + "-" + date;
		var finalDate2 = 2017 + "-" + 10 + "-" + 7;
		var sendRequest = function(){
			//alert("sending....");
			$http({
				method:"GET",
				url:"getliveserial?doctor_id="+doctorId+"&clinic_id="+
				clinicId+"&date="+finalDate2
			}).then(function(response){
				console.log(response.data);
				var isObject = angular.isObject(response.data);
				if(isObject)
				{
					$scope.liveSerial = response.data;
					for(var i=0;i<$scope.liveSerial.length;i++)
					{
						$scope.liveSerial[i].serialNo = 
							$scope.liveSerial[i].appointmentId - 
							$scope.liveSerial[0].appointmentId + 1
						if($scope.liveSerial[i].isDone == 1)
							$scope.lastConsulted = $scope.liveSerial[i].serialNo;
						
							
					}
					
				}
				else if(response.data.trim()=="0")
				{
					
					$scope.liveSerial = null;
				}
			})
		}
		sendRequest();
		
		$interval(sendRequest,5000)
		
	}
	
	
	
});

/*angular js routing configuration
 * */
app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl:"template/patient_dashboard.jsp"
	})
	.when("/account",{
		templateUrl:"template/patient_account.jsp"
	})
	.when("/todaysappointment",{
		templateUrl:"template/patient_today_appointment.jsp"
	})
	.when("/addvisitingschedule",{
		templateUrl:"template/add_doctor_visiting_schedule.jsp"
	})
	.when("/updatevisitingschedule",{
		templateUrl:"template/update_doctor_visiting_schedule.jsp"
	})
	.when("/adddoctorclinic",{
		templateUrl:"template/add_doctor_clinic.jsp"
	})	
	.when("/upcomingappointment",{
		templateUrl:"template/patient_upcoming_appointment.jsp"
	})
	.when("/notification",{
		templateUrl:"template/doctor_notification.jsp"
	})
	.when("/doctortoappoint",{
		templateUrl:"template/doctor_list_to_appoint.jsp"
	})
	.when("/doctordashboard/updated",{
		templateUrl:"template/doctor_account.jsp"
	})
	.when("/viewliveserials",{
		templateUrl:"template/view_live_serial.jsp"
	})
	.when("/update",{
		templateUrl:"template/update_doctor_profile.jsp"
	})
	.when("/upload",{
		templateUrl:"template/upload.jsp"
	})	
});
