package com.foodapp.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.foodapp.daoimpl.MenuDaoImpl;
import com.foodapp.model.Menu;

public class ShowMenu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("restaurantId");
        String restaurantName = req.getParameter("restaurantname");

        // ✅ Handle missing or invalid restaurant ID
        if (rid == null || rid.isEmpty()) {
            req.setAttribute("errorMessage", "Invalid restaurant selection.");
            req.getRequestDispatcher("Home.jsp").forward(req, resp);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(rid);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid restaurant ID format.");
            req.getRequestDispatcher("Home.jsp").forward(req, resp);
            return;
        }

        // ✅ Log details for debugging
        System.out.println("This is Restaurant ID: " + id);
        System.out.println("This is Restaurant Name: " + restaurantName);

        // ✅ Store Restaurant details in session
        req.getSession().setAttribute("RestaurantName", restaurantName);
        req.getSession().setAttribute("RestaurantId", id);

        // ✅ Fetch menu based on restaurant ID
        MenuDaoImpl menuDao = new MenuDaoImpl();
        List<Menu> menuList = menuDao.getOnRid(id);

        // ✅ Debugging log
        System.out.println("Menu List Retrieved: " + (menuList != null ? menuList.size() : "null"));

        // ✅ Handle empty menu list scenario
        if (menuList == null || menuList.isEmpty()) {
            System.out.println("No menu found, redirecting to emptycart.jsp");
            resp.sendRedirect("EmptyCart.jsp");
            return;
        }

        // ✅ Store menuList in session and forward to menu.jsp
        req.getSession().setAttribute("menuList", menuList);
        req.getRequestDispatcher("Menu.jsp").forward(req, resp);
    }
}
