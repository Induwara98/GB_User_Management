package com.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller.UserController;



/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/UsersAPI")
public class UsersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserController userController = new UserController();
		
				
		String output = userController.userRegister(request.getParameter("fname"), 
				 request.getParameter("lname"), 
				 request.getParameter("pnumber"),
				 request.getParameter("address"), 
				 request.getParameter("password"),
				 request.getParameter("type"));
		
				response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
			try
				{ 
					Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
					String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : ""; 
					scanner.close(); 
					String[] params = queryString.split("&"); 
					
					for (String param : params) 
						{
							String[] p = param.split("=");
							map.put(p[0], p[1]); 
						} 
				} catch (Exception e) { 
				
			 } 
			return map; 
			}


		 
		 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserController userController = new UserController();
		Map paras = getParasMap(request); 
		 
		String output = userController.updateUser(paras.get("hidUserIDSave").toString(), 
												  paras.get("fname").toString(), 
												  paras.get("lname").toString(), 
												  paras.get("pnumber").toString(), 
												  paras.get("address").toString(),
												  paras.get("password").toString(),
												  paras.get("type").toString()); 
		
		response.getWriter().write(output);
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserController userController = new UserController();
		Map paras = getParasMap(request); 
		String output = userController.deleteUser(paras.get("userid").toString());
		response.getWriter().write(output);
	}

}
