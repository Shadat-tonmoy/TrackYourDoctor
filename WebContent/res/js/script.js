var app = angular.module("mainApp",[]);
app.controller("counterController",function($scope,$interval){
	
	$scope.deadLine = new Date(2017,7,1,12,0,0,0);	
	$interval(function(){
		$scope.currentTime = new Date();
		$scope.currentDate = $scope.currentTime.getDate();
		$scope.currentMonth = $scope.currentTime.getMonth();
		$scope.currentHour = $scope.currentTime.getHours();
		$scope.currentMinute = $scope.currentTime.getMinutes();
		$scope.currentSecond = $scope.currentTime.getSeconds();
		
		$scope.remain = new Date($scope.deadLine - $scope.currentTime);
		$scope.remainDay = $scope.remain.getDate();
		$scope.remainMonth = $scope.remain.getMonth()-1;
		$scope.remainHour = $scope.remain.getHours();
		$scope.remainMinute = $scope.remain.getMinutes();
		$scope.remainSecond = $scope.remain.getSeconds();		
	},1000);
	
	
	
	
})
