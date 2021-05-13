<%@page import="com.Controller.UserAdminController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/users.js"></script>
</head>
<body> 
	<div class="container">
	<div class="row"><div class="col-6"> 
	<h1>User Profile Management</h1>
		<form id="formUser" name="formUser">
 			First Name: <input id=fname name="fname" type="text" class="form-control form-control-sm"><br> 
 			Last Name: <input id="lname" name="lname" type="text" class="form-control form-control-sm"><br> 
 			Address: <input id="address" name="address" type="text" class="form-control form-control-sm"><br> 
 			Phone Number: <input id="pnumber" name="pnumber" type="text" class="form-control form-control-sm"><br>
 			
 			<div class="input-group input-group-sm mb-3">
 				<div class="input-group-prepend">
 					<span class="input-group-text" id="lblName">User Type: </span>
 				</div>
 					<select id="type" name="type">
 						<option value="0">--Select User Type--</option>
 						<option value="1">Investor</option>
 						<option value="2">Researcher</option>
 						<option value="3">Customer</option>
 						<option value="4">Administrator</option>
 					</select>
 				</div>
 	 		<br> 
 	 		Password: <input id="password" name="password" type="password" class="form-control form-control-sm" readonly ><br>
 
 			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
		</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
		<%
 			UserAdminController userAdmin = new UserAdminController(); 
 			out.print(userAdmin.readUsers()); 
 		%>
 	<br>
	</div>
	</div> 
</div> 
</div> 
</body>
</html>