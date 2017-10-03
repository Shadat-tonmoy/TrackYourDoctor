<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>





<style>
	.inputfile {
	width: 0.1px;
	height: 0.1px;
	opacity: 0;
	overflow: hidden;
	position: absolute;
	z-index: -1;
	}
	.inputfile + label {
	font-size:1.2em;
    width: 20%;
    cursor: pointer;
    padding: 15px;
    border-radius: 8px;
    color: #d35400;
    background: white;
    border: 2px solid #d35400;
    text-align: center;
	}

.inputfile:focus + label,
.inputfile + label:hover {
	background-color:#d35400;
	color:white;
	transition:background-color 1.5s;
}
.inputfile + label {
	cursor: pointer; /* "hand" cursor */
}
.submitButton{
    display: block;
    margin-top: 2%;
    width: 20%;
    border-radius: 9px;
    background: transparent;
    border: 2px solid #d35400;
    padding: 15px;
    color: #d35400;
    font-size: 1.1em;
    font-weight: bold;
}
.submitButton:hover{
	color:white;
	background:#d35400;
	transition:background 1.5s;

}

</style>
</head>
<body>


	<div>
		<span 
			style="
		    font-size: 10em;
		    text-align: center;
		    overflow: hidden;
		    color: #d35400;
		    display: inherit;
		    margin-top: 5%;"
		    class="glyphicon glyphicon-upload"> 
		</span>
		
		<h3 style="padding:10px; border-left:3px solid #d35400;">Choose File To Upload</h3>
		<br>
		<form id="myForm" method="post" action="./fileUpload" enctype="multipart/form-data">
			<input type="file" name="file" id="file" class="inputfile" ng-model="fileInput" />
			<label for="file">Choose a file</label>
			<input type="submit" class="submitButton" />	
		</form>
	</div>
	
	<div id="progress">
        <div id="bar"></div>
        <div id="percent">0%</div >
	</div>
	<br/>
 
	<div id="message"></div>
	
	
	
	
</body>
</html>