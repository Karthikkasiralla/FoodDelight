<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Menu" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Menu List</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background: #e3f2fd;
            color: #333;
        }

        header {
            background: #0d47a1;
            padding: 15px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            color: white;
        }

        header .logo {
            display: flex;
            align-items: center;
        }

        header .logo img {
            border-radius: 50%;
            height: 50px;
            width: 50px;
            margin-right: 10px;
        }

        .app-name {
            font-size: 24px;
            font-weight: bold;
        }

        header h1 {
            font-size: 22px;
            margin: 0;
            text-align: center;
            flex-grow: 1;
        }

        .container {
            padding: 20px;
            max-width: 1000px;
            margin: 0 auto;
        }

        .card {
            display: flex;
            align-items: center;
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin-bottom: 20px;
            padding: 15px;
            transition: 0.3s;
        }

        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
        }

        .card img {
            width: 150px;
            height: 150px;
            border-radius: 10px;
            object-fit: cover;
            margin-right: 20px;
        }

        .card-content {
            flex-grow: 1;
            text-align: left;
        }

        .card-content h3 {
            font-size: 20px;
            color: #0d47a1;
            margin-bottom: 8px;
        }

        .card-content p {
            margin: 5px 0;
            color: #555;
        }

        .rating {
            display: flex;
            align-items: center;
            font-size: 16px;
            color: #ffab00;
        }

        .rating i {
            margin-right: 5px;
        }

        .order-btn {
            padding: 10px 15px;
            background: #0d47a1;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        .order-btn:hover {
            background: #1565c0;
        }

        .back-button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background: #0d47a1;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        .back-button:hover {
            background: #1565c0;
        }
    </style>
</head>
<body>
    <header>
        <div class="logo">
            <img src="https://th.bing.com/th/id/OIP.GQ30tYzrobb44Z1dL0VLPQHaHa?w=165&h=180" alt="Logo">
            <div class="app-name">FoodDelight</div>
        </div>
        <h1>
            <% String restaurantName = (String) session.getAttribute("RestaurantName"); %>
            <%= restaurantName != null ? restaurantName : "Restaurant" %>'s Menu
        </h1>
    </header>

    <div class="container">
        <% List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
           if (menuList != null && !menuList.isEmpty()) {
               for (Menu menu : menuList) { %>
        <div class="card">
            <img src="<%= menu.getImage() %>" alt="<%= menu.getName() %>">
            <div class="card-content">
                <h3><%= menu.getName() %></h3>
                <p><strong>Description:</strong> <%= menu.getDescription() %></p>
                <p><strong>Price:₹</strong> <%= menu.getPrice() %></p>
                <div class="rating">
                    <i class="fa-solid fa-star"></i>
                    <span><%= menu.getRating() %>/5</span>
                </div>
                <p><strong>Available:</strong> <%= menu.isAvailable().equals("Yes") ? "Yes" : "No" %></p>
                <form action="AddToCartController" method="POST">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                    <input type="hidden" name="restaurantid" value="<%= menu.getR_ID() %>">
                    <input type="hidden" name="rimage" value="<%= menu.getImage() %>">
                    <input type="number" name="quantity" min="1" value="1">
                    <button type="submit" class="order-btn">Add to Cart</button>
                </form>
            </div>
        </div>
        <% } } else { %>
        <p style="text-align: center; color: #555; font-size: 18px;">No menu items available.</p>
        <% } %>
    </div>

    <a href="Home.jsp" class="back-button">Back to Home</a>
</body>
</html>
