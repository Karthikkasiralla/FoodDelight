<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Food delight</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: url('https://res.cloudinary.com/dbquljdpi/image/upload/v1740050558/cld-sample-4.jpg') no-repeat center center;
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .signup-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }
        .signup-container h1 {
            color: #388e3c;
            font-size: 28px;
            margin-bottom: 20px;
        }
        .input-group {
            margin: 15px 0;
            display: flex;
            align-items: center;
            border: 1px solid #81c784;
            border-radius: 8px;
            padding: 10px;
            background: #c8e6c9;
        }
        .input-group i {
            margin-right: 10px;
            color: #2e7d32;
        }
        .input-group input {
            width: 100%;
            border: none;
            outline: none;
            font-size: 14px;
            background: transparent;
            color: #1b5e20;
        }
        .btn {
            margin-top: 20px;
            background-color: #43a047;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s;
        }
        .btn:hover {
            background-color: #2e7d32;
        }
        a {
            text-decoration: none;
            color: #1b5e20;
            display: block;
            margin-top: 15px;
        }
        a:hover {
            color: #004d40;
        }
        .error-message {
            color: #d32f2f;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h1>Create an Account</h1>
        <form action="Signup" method="post">
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" name="username" placeholder="Enter your username" required>
            </div>
            <div class="input-group">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" name="password" placeholder="Enter your password" required>
            </div>
            <div class="input-group">
                <i class="fas fa-phone"></i>
                <input type="tel" name="mobile" placeholder="Enter your mobile number" required>
            </div>
            <button type="submit" class="btn">Sign Up</button>
            <a href="login.jsp">Already have an account? Login</a>
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
