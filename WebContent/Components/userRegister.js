$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateUserForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
$(document).ajaxSuccess(function(){
	var message = $("#fname").val()+ "@GB.lk"; 
	  alert("Successfully Registered! Your Username is "+message);
	 
	});
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "UsersAPI", 
type : type, 
data : $("#formUser").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onUserSaveComplete(response.responseText, status); 
} 
}); 
}); 





//CLIENT-MODEL================================================================
function validateUserForm() 
{ 
// FNAME
if ($("#fname").val().trim() == "") 
 { 
 return "Insert First Name."; 
 } 
// LNAME
if ($("#lname").val().trim() == "") 
 { 
 return "Insert Last Name."; 
 } 
//ADDRESS
if ($("#address").val().trim() == "") 
 { 
 return "Insert Address."; 
 } 
//PHONE NUMBER
if ($("#pnumber").val().trim() == "") 
 { 
 return "Insert Phone Number."; 
 } 
//USER TYPE
if ($("#type").val() == "0") 
{ 
	return "Select User Type."; 
} 
// PASSWORD
if ($("#password").val().trim() == "") 
 { 
 return "Insert Password."; 
 } 

if ($("#password").val().trim() != $("#password2").val().trim()) 
{ 
return "Password do not match."; 
} 
return true; 
}

function onUserSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
	var mess=$("#fname").val();
 
 $("#alertSuccess").text("User Registered Successfully. Your Username is : " +mess+"@GB.lk"); 
 $("#alertSuccess").show(); 
 document.location = "login.jsp";

 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidUserIDSave").val(""); 
$("#formUser")[0].reset(); 
}
