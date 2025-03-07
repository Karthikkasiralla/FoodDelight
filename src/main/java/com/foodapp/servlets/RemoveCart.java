package com.foodapp.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.daoimpl.CartDaoImpl;
import com.foodapp.model.CartItem;




public class RemoveCart extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the cart from the session
        @SuppressWarnings("unchecked")
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");

        // If the cart exists, remove the item from it
        if (cart != null && !cart.isEmpty()) {
            int itemId = Integer.parseInt(req.getParameter("itemId"));

            // Create a CartDAOImpl object and call removeItem
            CartDaoImpl cartDao = new CartDaoImpl();
            cartDao.removeItem(cart, itemId);  // Pass session cart to remove the item

            // Save the updated cart back into the session
            req.getSession().setAttribute("cart", cart);

            // Redirect to the cart page to reflect the updated cart
            resp.sendRedirect("Cart.jsp");
        } else {
            // Handle the case where cart is empty or doesn't exist
            resp.sendRedirect("Cart.jsp");
        }
    }
}

