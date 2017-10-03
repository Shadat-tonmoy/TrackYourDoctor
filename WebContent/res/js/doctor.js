var app = angular.module("doctorApp",["ngRoute"]);
app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl:"template/home.jsp"
	})
	.when("/login",{
		templateUrl:"template/login.html"
	})
	.when("/signup",{
		templateUrl:"template/signup.html"
	})
});
app.controller("signupController",function($scope,$http,$timeout){
	$scope.firstNameFocus = false;
	$scope.lastNameFocus = false;
	$scope.userNameFocus = false;
	$scope.passwordFocus = false;
	$scope.passwordAgainFocus = false;
	$scope.emailFocus = false;
	$scope.phoneFocus = false;
	$scope.invalidPhone = false;
	$scope.isRegistered = false;
	$scope.isEmailOk = false;
	
	$scope.checkEmail = function(){
		$http({
			method:"POST",
			url:"checkemail?email="+$scope.email+"&userType=1"
		}).then(function(response){
			console.log(response.data);
			if(response.data.trim()=="ok")
			{
				$scope.isEmailOk = true;				
			}
			else $scope.isEmailOk = false;
		})
	}
	
	
//	$scope.firstName = "shadat";
//	$scope.lastName = "tonmoy";
//	$scope.userName = "shadat_tonmoy";
//	$scope.password= "123456";
//	$scope.passwordAgain = "123456";
//	$scope.email = "shadat.tonmoy@gmail.com";
//	$scope.about = "I am boss";
	$scope.signupSubmit = function(){
		if($scope.isEmailOk)
		{		
		
			$http({
			method:"POST",
			url:"doctorsignup?firstName="+$scope.firstName+"&lastName="+$scope.lastName
			+"&password="+$scope.password+"&email="+$scope.email
			+"&userType=1"
			
			}).then(function(response){
				$scope.msg = response.data;
				if($scope.msg.trim()=="true")
				{
					$scope.isRegistered = true;
					$timeout(function(){
						//alert("Now redirect");
						window.location.replace("./doctordashboard?isNew=1&email="+$scope.email);
					},2000);
					console.log("Success : "+response.data);
				}				
				
			},
				function(error)
				{
					console.log(error);
				}
			)			
		}		
	}	
});

app.controller("loginController",function($scope,$http,$timeout){
	$scope.emailFocus = false;
	$scope.passwordFocus = false;
	$scope.isLoggedIn = false;
	$scope.isIncorrect = false;
	$scope.loginSubmit = function()
	{
		$scope.isLoggedIn = false;
		$scope.isIncorrect = false;		
		$http({
			method:"POST",
			url:"doctorlogin?email="+$scope.email+"&password="+$scope.password			
		}).then(function(response){
			$scope.msg = response.data;
			console.log($scope.msg.trim());
			//alert(response.data);
			if($scope.msg.trim()=="true")
			{
				$scope.isLoggedIn = true;
				$timeout(function(){
					//alert("Now redirect");
					window.location.replace("doctordashboard.jsp");
				},2000);
			}
			else if($scope.msg.trim()=="false")
			{
				$scope.isIncorrect = true;
			}
			
		},
		function(error){
			console.log(error);
		})
	}
	
})
