<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>👨‍⚕️ Tableau de bord du médecin généraliste</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        :root {
            --bg-color: #0d1117;
            --card-bg: #161b22;
            --text-color: #e6edf3;
            --accent-color: #1db954;
            --accent-hover: #17a84b;
            --secondary-color: #68a7ef;
            --danger-color: #ff5555;
            --border-color: #30363d;
            --table-hover: #21262d;
        }

        body {
            font-family: "Segoe UI", Arial, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-color);
            margin: 0;
            padding: 0;
        }

        header {
            background-color: var(--card-bg);
            border-bottom: 1px solid var(--border-color);
            padding: 20px 40px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        header h1 {
            color: var(--accent-color);
            font-size: 1.5em;
            margin: 0;
        }

        .btnp
 {
            display: inline-block;
            background-color: var(--accent-color);
            color: #fff;
            padding: 10px 16px;
            border-radius: 6px;
            font-weight: 600;
            text-decoration: none;
            transition: 0.3s ease;
        }

        .btnp
:hover {
            background-color: var(--accent-hover);
            transform: scale(1.05);
        }

        main {
            padding: 30px;
        }

        .section {
            background-color: var(--card-bg);
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.4);
            padding: 25px;
            margin-bottom: 40px;
            border: 1px solid var(--border-color);
        }

        h2 {
            color: var(--secondary-color);
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
            font-size: 1.3em;
        }

        h2 span {
            font-size: 1.2em;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 14px 16px;
            text-align: center;
            border-bottom: 1px solid var(--border-color);
        }

        th {
            background-color: var(--secondary-color);
            color: #fff;
            font-weight: 700;
            letter-spacing: 0.5px;
        }

        tr:nth-child(even) {
            background-color: #1a1f27;
        }

        tr:hover {
            background-color: var(--table-hover);
        }

        .action-link {
            margin: 0 6px;
            text-decoration: none;
            color: var(--secondary-color);
            font-weight: 600;
            transition: color 0.3s ease;
        }

        .action-link:hover {
            color: var(--accent-color);
        }

        .empty-message {
            text-align: center;
            color: #aaa;
            font-style: italic;
            padding: 20px 0;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #777;
            font-size: 0.9em;
            border-top: 1px solid var(--border-color);
        }

        /* Responsive */
        @media (max-width: 768px) {
            table, thead, tbody, th, td, tr {
                display: block;
                width: 100%;
            }
            th {
                display: none;
            }
            tr {
                margin-bottom: 15px;
                background-color: var(--card-bg);
                border-radius: 8px;
                padding: 10px;
            }
            td {
                text-align: left;
                padding: 8px 10px;
                border: none;
                border-bottom: 1px solid var(--border-color);
            }
            td:before {
                content: attr(data-label);
                font-weight: 600;
                display: block;
                color: var(--secondary-color);
            }
        }
    </style>
</head>
<body>


<header>
    <h1>🏥 Espace Médecin Généraliste</h1>
    <a href="demandeExpertise?action=add" class="btnp">➕ Nouvelle demande d’expertise</a>
    <jsp:include page="navbar.jsp" />
</header>

<main>

    <section class="section">
        <h2><span>👨‍⚕️</span> Liste des patients</h2>

        <c:choose>
            <c:when test="${not empty patients}">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Statut</th>
                        <th>Mutuelle</th>
                        <th>Date d’arrivée</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${patients}">
                        <tr>
                            <td data-label="ID">${p.id}</td>
                            <td data-label="Nom">${p.nom}</td>
                            <td data-label="Prénom">${p.prenom}</td>
                            <td data-label="Statut">${p.status}</td>
                            <td data-label="Mutuelle">${p.mutuelle}</td>
                            <td data-label="Date d’arrivée">${p.dateArrivee}</td>
                            <td data-label="Actions">
                                <a href="dossierMedical?id=${p.id}&user_role=${user.role.name()}" class="action-link">📋 Dossier</a>
                                <a href="consultation?action=add&patient_id=${p.id}&generaliste_id=${user.id}" class="action-link">💬 Consultation</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-message">Aucun patient en attente.</div>
            </c:otherwise>
        </c:choose>
    </section>

    <section class="section">
        <h2><span>🩺</span> Demandes d’expertise en cours</h2>

        <c:choose>
            <c:when test="${not empty demandes}">
                <table>
                    <thead>
                    <tr>
                        <th>ID demande</th>
                        <th>ID consultation</th>
                        <th>Date demande</th>
                        <th>Date réponse</th>
                        <th>Priorité</th>
                        <th>Statut</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="d" items="${demandes}">
                        <tr>
                            <td data-label="ID demande">${d.id}</td>
                            <td data-label="ID consultation">${d.consultation.id}</td>
                            <td data-label="Date demande">${d.date_demande}</td>
                            <td data-label="Date réponse">${d.date_reponse != null ? d.date_reponse : '--'}</td>
                            <td data-label="Priorité">${d.priorite}</td>
                            <td data-label="Statut">${d.statut}</td>
                            <td data-label="Actions">
                                <a href="dossierMedical?id=${d.consultation.dossier.patient.id}&demande_id=${d.id}&user_role=${user.role.name()}" class="action-link">📋 Dossier</a>
                                <a href="demandeExpertise?action=view&id=${d.id}&consultation_id=${d.consultation.id}&generaliste_id=${user.id}&user_role=${user.role.name()}" class="action-link">💬 Détails</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-message">Aucune demande d’expertise en attente.</div>
            </c:otherwise>
        </c:choose>
    </section>
</main>

<footer>
    &copy; 2025 TELEEXPERTISE - Application de Télé-Expertise Médicale
</footer>

</body>
</html>
