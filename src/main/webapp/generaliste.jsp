<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des patients</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            margin: 30px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        a.add-patient {
            display: inline-block;
            margin: 15px 0;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }

        a.add-patient:hover {
            color: #388E3C;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #d1e7dd;
        }

        /* Actions */
        .action-link {
            margin: 0 5px;
            text-decoration: none;
            color: #1976D2;
            font-weight: bold;
        }

        .action-link:hover {
            color: #0D47A1;
        }

        /* Message si la liste est vide */
        .empty-message {
            text-align: center;
            color: #888;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h2>Liste des patients</h2>

<a href="patient?action=add" class="add-patient">➕ Ajouter un patient</a>

<c:choose>
    <c:when test="${not empty patients}">
        <table>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Mutuelle</th>
                <th>Date d’arrivée</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="p" items="${patients}">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.nom}</td>
                    <td>${p.prenom}</td>
                    <td>${p.mutuelle}</td>
                    <td>${p.dateArrivee}</td>
                    <td>
                        <a href="dossierMedical?action=view&id=${p.id}" class="action-link">Voir dossier médical</a>
                        <a href="consultation?patient_id=${p.id}&generaliste=${user.id}" class="action-link">Ouvrir consultation</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div class="empty-message">Aucun patient n’est enregistré.</div>
    </c:otherwise>
</c:choose>

</body>
</html>
