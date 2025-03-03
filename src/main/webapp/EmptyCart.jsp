<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empty Cart</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap');

        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .empty-cart-container {
            text-align: center;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
            animation: fadeIn 1s ease-in-out;
        }

        .empty-cart-container img {
            width: 200px;
            margin-bottom: 20px;
            animation: bounce 1.2s infinite alternate;
        }

        .empty-cart-container h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
            opacity: 0;
            animation: fadeInUp 1s ease-in-out forwards 0.5s;
        }

        .empty-cart-container p {
            font-size: 16px;
            color: #666;
            margin-bottom: 20px;
            opacity: 0;
            animation: fadeInUp 1s ease-in-out forwards 0.8s;
        }

        .empty-cart-container a {
            display: inline-block;
            padding: 12px 24px;
            background-color: #4CAF50;
            color: #ffffff;
            text-decoration: none;
            border-radius: 8px;
            transition: 0.3s ease-in-out;
            opacity: 0;
            animation: fadeInUp 1s ease-in-out forwards 1s;
            font-weight: 600;
        }

        .empty-cart-container a:hover {
            background-color: #45a049;
            box-shadow: 0px 4px 12px rgba(76, 175, 80, 0.3);
        }

        /* Animations */
        @keyframes fadeIn {
            from { opacity: 0; transform: scale(0.95); }
            to { opacity: 1; transform: scale(1); }
        }

        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        @keyframes bounce {
            from { transform: translateY(0px); }
            to { transform: translateY(-8px); }
        }
    </style>
</head>
<body>
    <div class="empty-cart-container">
        <img src="https://krosfitsports.com/public/empty-cart.gif" alt="Empty Cart Icon">
        <h1>Your Cart is Empty</h1>
        <p>Looks like you haven’t added anything to your cart yet.</p>
        <a href="Home.jsp">Start Ordering</a>
    </div>
</body>
</html>
