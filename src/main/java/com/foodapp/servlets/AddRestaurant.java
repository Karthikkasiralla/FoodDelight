package com.foodapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.daoimpl.RestaurantDaoImpl;
import com.foodapp.model.Restaurant;

public class AddRestaurant extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		String restaurantName = req.getParameter("rName");
		String cuisineType = req.getParameter("cusineType");
		String restaurantAddress = req.getParameter("address");

		// Parse rating
		String ratingParam = req.getParameter("rating");
		int rating = 0; // Default value for rating
		if (ratingParam != null && !ratingParam.trim().isEmpty()) {
			try {
				rating = Integer.parseInt(ratingParam);
			} catch (NumberFormatException e) {
				System.out.println("Invalid rating input: " + ratingParam);
			}
		}

		String available = req.getParameter("available");
		String image = req.getParameter("rImage");
		String ownerName = req.getParameter("restaurantOwner");

		// Create Restaurant object
		Restaurant restaurant = new Restaurant(
				restaurantName,
				cuisineType,
				restaurantAddress,
				rating,
				available,
				image
				
		);

		// Insert into database
		RestaurantDaoImpl restaurantImp = new RestaurantDaoImpl();
		int status = restaurantImp.insert(restaurant);

		// Redirect based on status
		if (status == 1) {
			resp.sendRedirect("SuperAdmin.jsp");
		} else {
			resp.getWriter().println("Error: Unable to add restaurant. Please try again.");
		}
	}

}