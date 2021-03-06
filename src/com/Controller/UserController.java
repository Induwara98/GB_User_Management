package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;




public class UserController {
	
	UserAdminController users = new UserAdminController();

	//Database Connection
		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
	 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbuserdb", "root", "root"); 
			} 
			catch (Exception e) 
			{e.printStackTrace();} 
			return con; 
		} 

	//Insert User
		public String userRegister(String fname, String lname, String pnumber,String address,String password, String type) 
		{ 
			String output = ""; 
			
			int type1 = Integer.parseInt(type);
			String userType = null;
			
			if(type1 == 1) {
				userType="Investor";
			}else if(type1 == 2) {
				userType="Researcher";
			}else if(type1 == 3) {
				userType="Customer";
			}else if(type1 == 4) {
				userType="Administrator";
			}
		
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
	 
				// create a prepared statement
				String query = " insert into gbuserdb.user (`userid`,`fname`,`lname`,`pnumber`,`address`,`username`,`password`,`type`)"
						+ " values (?,?,?,?,?,?,?,?)"; 
	 
				PreparedStatement preparedStmt = con.prepareStatement(query); 

				// binding values
				
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, fname); 
				preparedStmt.setString(3, lname); 
				preparedStmt.setString(4, pnumber);
				preparedStmt.setString(5, address);
				preparedStmt.setString(6, fname+"@GB.lk");
				preparedStmt.setString(7, password);
				preparedStmt.setString(8, userType);
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newUsers = users.readUsers(); 
				output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}"; 
				 
			}catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
				System.err.println(e.getMessage()); 
				 
			} 
				 
			return output; 
				 
		}  

	//Update User
		public String updateUser(String userid, String fname, String lname, String pnumber, String address,String password,String type)
		{ 
			 String output = ""; 
			 
				int type1 = Integer.parseInt(type);
				String userType = null;
				
				if(type1 == 1) {
					userType="Investor";
				}else if(type1 == 2) {
					userType="Researcher";
				}else if(type1 == 3) {
					userType="Customer";
				}else if(type1 == 4) {
					userType="Administrator";
				}
			 try
			 { 
				 	Connection con = connect(); 
			 
				 	if (con == null) 
				 	{return "Error while connecting to the database for updating."; } 
			 
				 	// create a prepared statement
				 	String query = "UPDATE user SET fname=?,lname=?,pnumber=?,address=?,username=?,password=?,type=? WHERE userid =?"; 
			 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 	// binding values
				 	preparedStmt.setString(1, fname); 
				 	preparedStmt.setString(2, lname); 
				 	preparedStmt.setString(3, pnumber); 
				 	preparedStmt.setString(4, address); 
				 	preparedStmt.setString(5, fname+"@GB.lk"); 
				 	preparedStmt.setString(6, password); 
				 	preparedStmt.setString(7, userType); 
				 	preparedStmt.setInt(8, Integer.parseInt(userid)); 
				 
				
				 // execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
				 	String newUsers = users.readUsers();
					 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}"; 
			 } catch (Exception e) { 
					 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
					
			 return output; 
					 
		}

	//Delete User
		public String deleteUser(String userid) 
		 { 
			String output = ""; 
		 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for deleting."; } 
		 
				// create a prepared statement
				String query = "delete from user where userid=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				
		 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(userid)); 
		 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
		 
				String newUsers = users.readUsers(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}"; 
			
			}catch (Exception e){ 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				 System.err.println(e.getMessage()); 
			} 
				 
			return output; 
				 
		 }
}
