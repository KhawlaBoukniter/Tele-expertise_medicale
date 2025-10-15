<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        :root {
            --primary: #0ea5e9; /* bleu médical doux */
            --secondary: #10b981; /* vert santé */
            --light-bg: #f8fafc;
            --card-bg: #ffffff;
            --text-main: #1e293b;
            --text-muted: #475569;
            --border: #e2e8f0;
        }

        body {
            font-family: "Segoe UI", Roboto, sans-serif;
            background-color: var(--light-bg);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: var(--card-bg);
            padding: 35px 40px;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.05);
            width: 360px;
            text-align: center;
            transition: transform 0.2s ease;
        }

        .login-container:hover {
            transform: translateY(-2px);
        }

        h3 {
            margin-bottom: 25px;
            color: var(--primary);
            font-weight: 700;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid var(--border);
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 1rem;
            transition: border-color 0.2s ease;
        }

        input:focus {
            border-color: var(--primary);
            outline: none;
            background-color: #fff;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: var(--primary);
            color: #fff;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0284c7;
        }

        .error-message {
            color: #dc2626;
            margin-bottom: 10px;
            font-weight: 500;
        }

        .forgot-password {
            font-size: 12px;
            color: var(--text-muted);
            margin-top: 12px;
            display: block;
        }

        .forgot-password:hover {
            color: var(--primary);
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h3>Connexion</h3>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form method="post" action="login">
        <input type="text" name="email" placeholder="Email" required><br>
        <input type="password" name="password" placeholder="Mot de passe" required><br>
        <button type="submit">Se connecter</button>
    </form>

    <a href="signupServlet" class="forgot-password">Créer un nouveau compte</a>
</div>
</body>
</html>
