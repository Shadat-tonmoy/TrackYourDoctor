var app = angular.module("adminApp",[]);


app.controller("loginController",function($scope,$http,$timeout){
	$scope.userNameFocus = false;
	$scope.passwordFocus = false;
	$scope.isLoggedIn = false;
	$scope.isIncorrect = false;
	$scope.correctUserName = "shadat_tonmoy";
	$scope.correctPassword = "123456";
	$scope.loginSubmit = function()
	{
		if($scope.userName == "shadat_tonmoy" && $scope.password == "123456")
		{
			window.location.replace("admin_dashboard.jsp");
		}
		else
		{
			$scope.isIncorrect = true;			
		}
//		$scope.isLoggedIn = false;
//		$scope.isIncorrect = false;		
//		$http({
//			method:"POST",
//			url:"login.jsp?userName="+$scope.userName+"&password="+$scope.password			
//		}).then(function(response){
//			$scope.msg = response.data;
//			console.log($scope.msg.trim());
//			//alert(response.data);
//			if($scope.msg.trim()=="true")
//			{
//				$scope.isLoggedIn = true;
//				$timeout(function(){
//					//alert("Now redirect");
//					window.location.replace("dashboard.jsp");
//				},2000);
//			}
//			else if($scope.msg.trim()=="false")
//			{
//				$scope.isIncorrect = true;
//			}
//			
//		},
//		function(error){
//			console.log(error);
//		})
	}
	
})