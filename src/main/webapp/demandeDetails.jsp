<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Détails de la Demande d'Expertise</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        :root {
            --primary: #0ea5e9;
            --secondary: #10b981;
            --light-bg: #f8fafc;
            --card-bg: #ffffff;
            --text-main: #1e293b;
            --text-muted: #475569;
            --border: #e2e8f0;
        }

        body {
            background-color: var(--light-bg);
            font-family: "Segoe UI", Roboto, sans-serif;
            margin: 0;
            padding: 30px 15px;
            color: var(--text-main);
        }

        h2 {
            text-align: center;
            color: var(--primary);
            margin-bottom: 30px;
            font-weight: 700;
        }

        .card {
            background-color: var(--card-bg);
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.05);
            margin: 25px auto;
            padding: 25px 30px;
            max-width: 950px;
            border: 1px solid var(--border);
            transition: transform 0.2s ease;
        }

        .card:hover {
            transform: translateY(-2px);
        }

        .card-header {
            font-size: 1.2rem;
            font-weight: 600;
            color: var(--secondary);
            margin-bottom: 15px;
            border-bottom: 2px solid var(--secondary);
            padding-bottom: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            text-align: left;
            padding: 10px 8px;
            border-bottom: 1px solid var(--border);
            vertical-align: top;
        }

        th {
            color: var(--text-muted);
            text-transform: uppercase;
            font-size: 0.85rem;
            width: 30%;
        }

        tr:hover td {
            background-color: #f1f5f9;
        }

        .btnp {
            display: inline-block;
            padding: 10px 18px;
            background-color: var(--primary);
            color: #fff;
            border-radius: 8px;
            font-weight: 600;
            text-decoration: none;
            transition: 0.3s ease;
        }

        .btnp:hover {
            background-color: #0284c7;
        }

        .submit-btn {
            background-color: var(--secondary);
            margin-top: 10px;
        }

        .submit-btn:hover {
            background-color: #059669;
        }

        textarea {
            width: 100%;
            min-height: 100px;
            border: 1px solid var(--border);
            border-radius: 8px;
            padding: 10px;
            resize: vertical;
            font-size: 1rem;
            color: var(--text-main);
            background-color: #f9fafb;
            transition: border-color 0.2s ease;
        }

        textarea:focus {
            border-color: var(--primary);
            outline: none;
            background-color: #fff;
        }

        label {
            font-weight: 500;
            color: var(--text-muted);
            display: block;
            margin-bottom: 8px;
        }

        @media (max-width: 768px) {
            .card {
                padding: 20px;
            }
            th {
                display: none;
            }
            td {
                display: block;
                width: 100%;
                border: none;
                border-bottom: 1px solid var(--border);
                padding: 8px 0;
            }
            td::before {
                content: attr(data-label);
                font-weight: 600;
                color: var(--secondary);
                display: block;
                margin-bottom: 4px;
            }
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<h2>🩺 Détails de la Demande d’Expertise</h2>

<div class="card">

    <div class="card-header">📋 Informations sur la Demande</div>
    <table>
        <tr><th>ID Demande</th><td data-label="ID Demande">${demande.id}</td></tr>
        <tr><th>Date de la demande</th><td data-label="Date">${demande.date_demande}</td></tr>
        <tr><th>Date de réponse</th><td data-label="Date réponse"><c:out value="${demande.date_reponse != null ? demande.date_reponse : '--'}"/></td></tr>
        <tr><th>Question</th><td data-label="Question">${demande.question}</td></tr>
        <tr><th>Priorité</th><td data-label="Priorité">${demande.priorite}</td></tr>
        <tr><th>Statut</th><td data-label="Statut">${demande.statut}</td></tr>
        <tr><th>Spécialiste</th><td data-label="Spécialiste">${demande.specialiste.nom} ${demande.specialiste.prenom}</td></tr>
        <tr><th>Créneau</th>
            <td data-label="Créneau">
                <c:if test="${not empty demande.creneau}">
                    ${demande.creneau.date_debut} → ${demande.creneau.date_fin}
                </c:if>
                <c:if test="${empty demande.creneau}">Non défini</c:if>
            </td>
        </tr>
        <tr><th>Avis du Spécialiste</th><td data-label="Avis">${demande.avis != null ? demande.avis : 'Aucun avis pour le moment'}</td></tr>
    </table>

    <c:if test="${user.role.name() == 'SPECIALISTE'}">
        <div class="avis-section mt-3">
            <form action="demandeExpertise" method="post">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

                <input type="hidden" name="action" value="updateAvis"/>
                <input type="hidden" name="id" value="${demande.id}"/>
                <label for="avis">🩺 Donner / Modifier votre avis :</label>
                <textarea name="avis" id="avis" placeholder="Écrire votre avis ici...">${demande.avis}</textarea>
                <button type="submit" class="btnp submit-btn">💾 Enregistrer l'avis</button>
            </form>
        </div>
    </c:if>
</div>

<div class="card">
    <div class="card-header">🧠 Détails de la Consultation</div>
    <table>
        <tr><th>ID Consultation</th><td data-label="ID Consultation">${demande.consultation.id}</td></tr>
        <tr><th>Patient</th><td data-label="Patient">${demande.consultation.dossier.patient.nom} ${demande.consultation.dossier.patient.prenom}</td></tr>
        <tr><th>Date Consultation</th><td data-label="Date Consultation">${demande.consultation.date_consultation}</td></tr>
        <tr><th>Motif</th><td data-label="Motif">${demande.consultation.observations}</td></tr>
        <tr><th>Diagnostic</th><td data-label="Diagnostic">${demande.consultation.diagnostic}</td></tr>
        <tr><th>Traitement</th><td data-label="Traitement">${demande.consultation.traitement_prescrit}</td></tr>
    </table>
</div>

<div style="text-align:center; margin-top:20px;">
    <a href="javascript:history.back()" class="btnp">⬅ Retour</a>
</div>

</body>
</html>
