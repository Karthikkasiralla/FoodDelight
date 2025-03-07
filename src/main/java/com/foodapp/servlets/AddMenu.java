package com.foodapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.daoimpl.MenuDaoImpl;
import com.foodapp.model.Menu;
import com.foodapp.model.Restaurant;


public class AddMenu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Set content type and encoding
            resp.setContentType("text/html;charset=UTF-8");
            req.setCharacterEncoding("UTF-8");

            // Retrieve restaurant info from session
            Restaurant restaurant = (Restaurant) req.getSession().getAttribute("Restaurant");
            if (restaurant == null) {
                // Redirect to home if session data is missing
                resp.sendRedirect("Home.jsp?error=missingRestaurant");
                return;
            }

            int restaurantId = restaurant.getR_ID();

            // Retrieve form parameters
            String menuName = req.getParameter("name");
            String description = req.getParameter("description");
            String priceParam = req.getParameter("price");
            String ratingParam = req.getParameter("rating");
            String available = req.getParameter("isAvailable");
            String image = req.getParameter("image");

            // Validate non-numeric fields
            if (menuName == null || menuName.trim().isEmpty() || 
                description == null || description.trim().isEmpty() || 
                available == null || available.trim().isEmpty()) {
                resp.sendRedirect("AddMenu.jsp?error=invalidFields");
                return;
            }

            // Parse and validate numeric fields
            int price;
            int rating;
            try {
                price = Integer.parseInt(priceParam.trim());
                rating = Integer.parseInt(ratingParam.trim());
            } catch (NumberFormatException e) {
                resp.sendRedirect("AddMenu.jsp?error=invalidNumericFields");
                return;
            }

            // Create Menu object
            Menu menu = new Menu(restaurantId, menuName.trim(), description.trim(), price, rating,  image != null ? image.trim() : "",available.trim());

            // Save menu to the database
            MenuDaoImpl menuDAO = new MenuDaoImpl();
            int status = menuDAO.insert(menu);

            // Redirect based on status
            if (status == 1) {
                resp.sendRedirect("RestaurantAdmin.jsp?success=menuAdded");
            } else {
                resp.sendRedirect("AddMenu.jsp?error=insertFailed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("ErrorPage.jsp?message=" + e.getMessage());
        }
    }
}