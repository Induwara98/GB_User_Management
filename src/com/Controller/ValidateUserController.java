package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ValidateUserController {
	
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
	
	//Validate User when login
	public String validateUser(String username,String password) {
		
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
			 	 
			 	
			 	
			 	// create a prepared statement
			 	String query = "SELECT * FROM USER WHERE username='"+username+"'AND password ='"+password+"'"; 
			 	
		 
			 	 Statement stmt = con.createStatement();
			 	 
				 ResultSet rs = stmt.executeQuery(query); 
					 
				 // iterate through the rows in the result set
				 if (rs.next()==true) 
				 { 
					 String fname = rs.getString("fname");
					 String lname = rs.getString("lname"); 

					 output = "Welcome "+fname+ " " +lname ;
				 } else {
					 output+="Incorrect Login Details";
				 } 
				con.close(); 
					 }
			catch (Exception e) 
			{ 
				 output = "Error while reading the items."; 
				 System.err.println(e.getMessage()); 
			} 
				 return output; 
		} 
	
	//Validate User By Type
	public String getUserByType(String username,String password) {
		
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
			 	 
			 	
			 	
			 	// create a prepared statement
			 	String query = "SELECT * FROM USER WHERE username='"+username+"'AND password ='"+password+"'"; 
			 	
		 
			 	 Statement stmt = con.createStatement();
			 	 
				 ResultSet rs = stmt.executeQuery(query); 
					 
				 if (rs.next()==true)  
				 {
					 String type = rs.getString("type");
					 System.out.println(type);
					 output+=""+type;
				 }else {
					 output+="No user matching type";
				 }
			con.close(); 
	
				 }
		catch (Exception e) 
		{ 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		} 
		 
		 
			 return output; 
	}
}
