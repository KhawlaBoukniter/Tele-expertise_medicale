<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        /* Style général du body */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Conteneur du formulaire */
        .login-container {
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            width: 350px;
            text-align: center;
        }

        h3 {
            margin-bottom: 20px;
            color: #333;
        }

        /* Styles des champs */
        input[type="text"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            width: 95%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }

        /* Message d'erreur */
        .error-message {
            color: red;
            margin-bottom: 10px;
        }

        /* Lien de mot de passe oublié */
        .forgot-password {
            font-size: 12px;
            color: #555;
            margin-top: 10px;
            display: block;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h3>Login</h3>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form method="post" action="login">
        <input type="text" name="email" placeholder="Email" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <button type="submit">Login</button>
    </form>

    <a href="#" class="forgot-password">Mot de passe oublié ?</a>
</div>
</body>
</html>
