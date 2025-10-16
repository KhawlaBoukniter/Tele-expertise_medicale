<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <title>Dossier Médical - ${patient.nom} ${patient.prenom}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        :root {
            --bg-color: #0d1117;
            --card-bg: #161b22;
            --text-color: #e6edf3;
            --accent-color: #1db954;
            --secondary-color: #58a6ff;
            --border-color: #30363d;
            --danger-color: #ff5555;
        }

        body {
            background-color: var(--bg-color);
            font-family: "Segoe UI", Arial, sans-serif;
            color: var(--text-color);
            padding: 30px;
            line-height: 1.6;
        }

        .fiche-container {
            max-width: 900px;
            margin: auto;
            background-color: var(--card-bg);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.4);
            border: 1px solid var(--border-color);
        }

        h2 {
            text-align: center;
            color: var(--accent-color);
            margin-bottom: 25px;
            font-size: 1.8rem;
        }

        .section-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: var(--secondary-color);
            margin-bottom: 8px;
            border-bottom: 1px solid var(--border-color);
            padding-bottom: 4px;
        }

        .section-content {
            background-color: #1c2128;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            white-space: pre-wrap;
            border: 1px solid var(--border-color);
        }

        .info-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 20px;
        }

        .info-card {
            flex: 1 1 45%;
            background-color: #1c2128;
            padding: 15px;
            border-radius: 8px;
            border: 1px solid var(--border-color);
        }

        .info-card label {
            font-weight: 600;
            color: var(--secondary-color);
            display: block;
            margin-bottom: 6px;
        }

        .btn-back {
            display: block;
            width: 100%;
            text-align: center;
            background-color: var(--secondary-color);
            color: #fff;
            text-decoration: none;
            padding: 10px;
            border-radius: 8px;
            font-weight: 600;
            transition: 0.3s ease;
        }

        .btn-back:hover {
            background-color: var(--accent-color);
            transform: scale(1.02);
        }

        em {
            color: #aaa;
        }

        @media (max-width: 600px) {
            .info-card {
                flex: 1 1 100%;
            }
        }
    </style>
</head>
<body>

<div class="fiche-container">
    <h2>🩺 Dossier Médical de ${patient.nom} ${patient.prenom}</h2>

    <div class="section-title">Informations personnelles</div>
    <div class="info-grid">
        <div class="info-card">
            <label>ID Patient :</label>
            <p>${patient.id}</p>
        </div>
        <div class="info-card">
            <label>Date d’arrivée :</label>
            <p>${patient.dateArrivee}</p>
        </div>
        <div class="info-card">
            <label>Mutuelle :</label>
            <p>
                <c:choose>
                    <c:when test="${patient.mutuelle}">Oui</c:when>
                    <c:otherwise>Non</c:otherwise>
                </c:choose>
            </p>
        </div>
        <div class="info-card">
            <label>Coordonnées :</label>
            <p>${patient.coordonnees}</p>
        </div>
    </div>

    <div class="section-title">Antécédents</div>
    <div class="section-content">
        <c:choose>
            <c:when test="${not empty dossier.antecedents}">${dossier.antecedents}</c:when>
            <c:otherwise><em>Aucun antécédent enregistré</em></c:otherwise>
        </c:choose>
    </div>

    <div class="section-title">Allergies</div>
    <div class="section-content">
        <c:choose>
            <c:when test="${not empty dossier.allergies}">${dossier.allergies}</c:when>
            <c:otherwise><em>Aucune allergie signalée</em></c:otherwise>
        </c:choose>
    </div>

    <div class="section-title">Traitements en cours</div>
    <div class="section-content">
        <c:choose>
            <c:when test="${not empty dossier.traitements}">${dossier.traitements}</c:when>
            <c:otherwise><em>Aucun traitement actif</em></c:otherwise>
        </c:choose>
    </div>

    <c:choose>
        <c:when test="${role == 'GENERALISTE'}">
            <a href="generaliste?action=list" class="btn-back mt-3">⬅ Retour à la liste des patients</a>
        </c:when>
        <c:otherwise>
            <a href="demandeExpertise?action=consult&demande_id=${demande}" class="btn-back mt-3">⬅ Retour à la demande d’expertise</a>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
