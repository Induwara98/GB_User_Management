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
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidUserIDSave").val($(this).data("userid")); 
 $("#fname").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#lname").val($(this).closest("tr").find('td:eq(2)').text());
 $("#address").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#pnumber").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#password").val($(this).closest("tr").find('td:eq(6)').text()); 
 $("#type").val(2);

});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "UsersAPI", 
		 type : "DELETE", 
		 data : "userid=" + $(this).data("userid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onUserDeleteComplete(response.responseText, status); 
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
return true; 
}

function onUserSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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


function onUserDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 


}
