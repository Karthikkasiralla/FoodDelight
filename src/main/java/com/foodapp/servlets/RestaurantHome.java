package com.foodapp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.daoimpl.RestaurantDaoImpl;
import com.foodapp.model.Restaurant;



public class RestaurantHome extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestaurantDaoImpl rimp = new RestaurantDaoImpl();
         
        // Fetch all restaurants from the database
        ArrayList<Restaurant> restaurantList = rimp.fetchAll();

        // Check if the list is null or empty
        if (restaurantList != null && !restaurantList.isEmpty()) {
            // Set the restaurant list in the session for access in Home1.jsp
            req.getSession().setAttribute("RList", restaurantList); 
        } else {
            // If no restaurants found, set an error message
            req.setAttribute("errorMessage", "No restaurants found.");
        }

        // Forward the request to Home1.jsp
        req.getRequestDispatcher("RestaurantHome.jsp").forward(req, resp);
    }
}