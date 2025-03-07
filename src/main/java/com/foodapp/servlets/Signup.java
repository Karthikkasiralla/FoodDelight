package com.foodapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.daoimpl.UserDaoImpl;
import com.foodapp.model.User;



public class Signup extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Name=req.getParameter("username");
		String Email=req.getParameter("email");
		int MobileNumber=Integer.parseInt(req.getParameter("mobile"));
		String password=req.getParameter("password");
		User u=new User(Name,Email,password,MobileNumber);
		
		UserDaoImpl up=new UserDaoImpl();
		status=up.Insert(u);
		if(status==1) {
			resp.sendRedirect("Login.jsp");
		}
		else {
			resp.sendRedirect("failure.jsp");
		}
	}

}