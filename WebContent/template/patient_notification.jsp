<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	
.notification_box{
	margin-top:20px;
    width: 100%;
    background: rgba(236, 240, 241,1.0);
    border-left: 5px solid red;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
h4,h6{
	margin-left : 20px;

}

</style>
</head>
<body>
<div ng-init="resetNotificationCount()" class="notification_box" ng-repeat="notification in notificationArray">
	<h4>{{notification.msg}}</h4>
	<h6>{{notification.time}}</h6>
</div>
</body>
</html>