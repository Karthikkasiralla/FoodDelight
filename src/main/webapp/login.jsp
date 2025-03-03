<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodDelight</title>
    
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://img.freepik.com/free-vector/fast-food-pattern_1108-194.jpg?t=st=1740048370~exp=1740051970~hmac=2cd54e3bff529ffdd28d1333a833360884154504c4e2f887fed3d795a64cf203&w=826') no-repeat center center;
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }
        .login-container h1 {
            color: #000;
            font-size: 28px;
            margin-bottom: 20px;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(255, 255, 255, 0.7);
        }
        .login-container .input-group {
            margin: 15px 0;
            display: flex;
            align-items: center;
            border: 1px solid #1e88e5;
            border-radius: 8px;
            padding: 10px;
            background: #e3f2fd;
        }
        .login-container .input-group i {
            margin-right: 10px;
            color: #1e88e5;
        }
        .login-container input {
            width: 100%;
            border: none;
            outline: none;
            font-size: 14px;
            background: transparent;
            color: #333;
        }
        .login-container .btn {
            margin-top: 20px;
            background-color: #1e88e5;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s;
        }
        .login-container .btn:hover {
            background-color: #1565c0;
        }
        .login-container a {
            text-decoration: none;
            color: #1e88e5;
            display: block;
            margin-top: 15px;
        }
        .login-container a:hover {
            color: #1565c0;
        }
        .error-message {
            color: #d32f2f;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>Welcome to Food Delight</h1>
        <form action="Login" method="post">
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="input-group">
                <i class="fas fa-key"></i>
                <input type="password" name="password" placeholder="Enter your password" required>
            </div>
            <button type="submit" class="btn">Login</button>
            <a href="register.jsp">Don't have an account? Create an account</a>
        </form>
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
    </div>
</body>
</html>
