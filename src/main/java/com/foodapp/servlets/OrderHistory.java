package com.foodapp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.daoimpl.OrderDaoImpl;
import com.foodapp.model.Order;
import com.foodapp.model.User;


/**
 * Servlet implementation class OrderHistoryServlet
 */
//@WebServlet("/OrderHistoryServlet")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderHistory(int orderId, int restaurant_id, int userId, int i, String status) {
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 
    	
    	 User user=(User)req.getSession().getAttribute("loggedInUser");
    	  if (user == null) {
              System.out.println("User is null in OrderHistoryServlet, redirecting to login page.");
              resp.sendRedirect("login.jsp");  // Redirect to login if no user found
              return;
          }
    	  
    	  int userid=user.getU_ID();
    	    try {
    	    	OrderDaoImpl odmpl=new OrderDaoImpl();
    	    	ArrayList<Order>orderlist=odmpl.fetchAllOrdersofUser(userid);
    	    	if (orderlist == null || orderlist.isEmpty()) {
                    System.out.println("No order history found for userId: " + userid);
                   
                   //resp.sendRedirect("NoOrderfound.jsp");
                }
    	    	else {
    	    		 System.out.println("Order history list found for userId: " + userid);
    	                req.setAttribute("orderhistorylist", orderlist);
    	    	}
    	    }
    	    
    	  catch (Exception e) {
    		  e.printStackTrace();
              req.setAttribute("orderHistoryMessage", "An error occurred while fetching order history.");
		}
    	    
    	    req.getRequestDispatcher("orderhistory.jsp").forward(req, resp);
    }

}