package com.foodapp.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodapp.dao.OrderDAO;
import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.dao.OrderItemDAO;
import com.foodapp.daoimpl.OrderDaoImpl;
import com.foodapp.daoimpl.OrderHistoryDaoImpl;
import com.foodapp.daoimpl.OrderItemDaoImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;
import com.foodapp.model.User;

public class CheckOut extends HttpServlet {
    private static final long serialVersionUID = 1L;

     OrderDAO orderDAO = new OrderDaoImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDaoImpl();
    private OrderHistoryDAO orderHistoryDAO = new OrderHistoryDaoImpl();

    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Don't create a new session if none exists

        if (session == null) {
            System.out.println("Session is null. Redirecting to login page...");
            resp.sendRedirect("Login.jsp");
            return;
        }

        // Retrieve cart
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty. Redirecting to cart.jsp...");
            resp.sendRedirect("cart.jsp"); // Redirect user to cart page
            return;
        }

        // Retrieve logged-in user
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            System.out.println("User not logged in. Redirecting to Login.jsp...");
            resp.sendRedirect("Login.jsp");
            return;
        }

        // Retrieve menu ID safely
        Object menuIdObj = session.getAttribute("MenuIds");
        int menuId = (menuIdObj instanceof Integer) ? (Integer) menuIdObj : -1;

        if (menuId == -1) {
            System.out.println("Menu ID is missing. Redirecting to menu.jsp...");
            resp.sendRedirect("Menu.jsp");
            return;
        }

        // Extract payment mode
        String modeOfPayment = req.getParameter("paymentMode");
        if (modeOfPayment == null || modeOfPayment.isEmpty()) {
            System.out.println("Payment mode is missing. Redirecting to checkout.jsp...");
            resp.sendRedirect("Checkout.jsp");
            return;
        }

        String status = "Pending";
        int userId = user.getU_ID();
        int totalOrderAmount = 0;

        // Calculate total amount safely
        for (CartItem item : cart.values()) {
            totalOrderAmount += item.getQuantity() * item.getPrice();
        }

        try {
            // Process each item in the cart
            for (CartItem item : cart.values()) {
                Order order = new Order(
                    userId,
                    item.getRestaurant_id(),
                    menuId,
                    item.getQuantity(),
                    item.getQuantity() * item.getPrice(),
                    modeOfPayment,
                    status
                );

                int orderId = orderDAO.insertOrder(order);
                if (orderId == -1) {
                    throw new Exception("Failed to create order for user: " + userId);
                }

                // Insert order item
                OrderItem orderItem = new OrderItem(
                    orderId,
                    menuId,
                    item.getQuantity(),
                    item.getQuantity() * item.getPrice()
                );
                orderItemDAO.insertOrderItem(orderItem);
            }

            // Set attributes for confirmation page
            req.setAttribute("cart", cart);
            req.setAttribute("totalOrderAmount", totalOrderAmount);

            // Clear cart after checkout
            session.removeAttribute("cart");

            // Redirect to order confirmation page
            resp.sendRedirect("OrderConfirm.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An error occurred while processing your order. Please try again.");
            req.getRequestDispatcher("Checkout.jsp").forward(req, resp);
        }
    }

}

