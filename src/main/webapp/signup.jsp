<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
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
            min-height: 100vh;
            margin: 0;
            padding: 15px;
        }

        .signup-container {
            background-color: var(--card-bg);
            padding: 35px 40px;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.05);
            width: 400px;
            max-width: 100%;
            text-align: center;
            transition: transform 0.2s ease;
        }

        .signup-container:hover {
            transform: translateY(-2px);
        }

        h2 {
            text-align: center;
            color: var(--primary);
            margin-bottom: 25px;
            font-weight: 700;
        }

        label {
            font-weight: 500;
            color: var(--text-main);
            display: block;
            margin-bottom: 6px;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid var(--border);
            border-radius: 8px;
            font-size: 1rem;
            transition: border-color 0.2s ease;
        }

        input:focus, select:focus {
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

        .alert {
            margin-top: 15px;
            padding: 10px;
            border-radius: 8px;
            background-color: #fee2e2;
            color: #b91c1c;
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
<div class="signup-container">
    <h2>Créer un compte</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert">${errorMessage}</div>
    </c:if>

    <form action="signupServlet" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

        <label for="nom">Nom</label>
        <input type="text" name="nom" required>

        <label for="prenom">Prénom</label>
        <input type="text" name="prenom" required>

        <label for="email">Email</label>
        <input type="email" name="email" required>

        <label for="password">Mot de passe</label>
        <input type="password" name="password" required>

        <label for="role">Rôle</label>
        <select name="role" required>
            <option value="GENERALISTE">Généraliste</option>
            <option value="SPECIALISTE">Spécialiste</option>
            <option value="INFIRMIER">Infirmier</option>
        </select>

        <button type="submit">S'inscrire</button>
    </form>
    <a href="signupServlet" class="forgot-password">Créer un nouveau compte</a>
</div>
</body>
</html>
