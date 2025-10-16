<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <title>Créer une demande d'expertise</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        :root {
            --bg-color: #0d1117;
            --card-bg: #161b22;
            --text-color: #e6edf3;
            --accent-color: #1db954;
            --accent-hover: #17a84b;
            --secondary-color: #58a6ff;
            --border-color: #30363d;
            --danger-color: #ff5555;
        }

        body {
            font-family: "Segoe UI", Arial, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-color);
            margin: 30px;
            line-height: 1.6;
        }

        h2 {
            color: var(--accent-color);
            text-align: center;
            margin-bottom: 25px;
        }

        .section {
            background-color: var(--card-bg);
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
            border: 1px solid var(--border-color);
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
        }

        label {
            font-weight: 600;
            color: var(--secondary-color);
            display: block;
            margin-bottom: 8px;
        }

        select, textarea, input {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid var(--border-color);
            background-color: #0d1117;
            color: var(--text-color);
            margin-bottom: 10px;
        }

        select:focus, textarea:focus, input:focus {
            border-color: var(--accent-color);
            outline: none;
        }

        button {
            display: block;
            margin: 0 auto;
            padding: 10px 20px;
            background-color: var(--accent-color);
            color: #fff;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        button:hover {
            background-color: var(--accent-hover);
            transform: scale(1.03);
        }

        .info {
            text-align: center;
            color: #aaa;
            margin-bottom: 20px;
        }

        @media (max-width: 600px) {
            .section { padding: 15px; }
            button { width: 100%; }
        }
    </style>
</head>
<body>

<h2>📄 Nouvelle Demande d'Expertise</h2>

<form method="get" action="demandeExpertise">
    <div class="section">
        <label for="specialite">1️⃣ Choisir une spécialité :</label>
        <select name="specialite" id="specialite" onchange="this.form.submit()">
            <option value="">-- Sélectionner une spécialité --</option>
            <c:forEach var="spec" items="${specialites}">
                <option value="${spec}" <c:if test="${spec == param.specialite}">selected</c:if>>${spec}</option>
            </c:forEach>
        </select>
    </div>
</form>

<c:if test="${not empty specialistes}">
    <form method="get" action="demandeExpertise">
        <input type="hidden" name="specialite" value="${param.specialite}">
        <div class="section">
            <label for="specialisteId">2️⃣ Choisir un spécialiste :</label>
            <select name="specialisteId" id="specialisteId" onchange="this.form.submit()">
                <option value="">-- Sélectionner un spécialiste --</option>
                <c:forEach var="sp" items="${specialistes}">
                    <option value="${sp.id}" <c:if test="${sp.id == param.specialisteId}">selected</c:if>>${sp.nom} ${sp.prenom}</option>
                </c:forEach>
            </select>
        </div>
    </form>
</c:if>

<c:if test="${not empty creneaux or not empty consultations}">
    <form method="post" action="demandeExpertise?action=submit">
        <input type="hidden" name="specialiste_id" value="${param.specialisteId}">

        <div class="section">
            <label for="creneau">3️⃣ Choisir un créneau :</label>
            <select name="creneau" id="creneau">
                <option value="">-- Sélectionner un créneau --</option>
                <c:forEach var="c" items="${creneaux}">
                    <option value="${c.id}">${c.date_debut} → ${c.date_fin}</option>
                </c:forEach>
            </select>
        </div>

        <div class="section">
            <label for="consultation_id">4️⃣ Choisir une consultation :</label>
            <select name="consultation_id" id="consultation_id">
                <option value="">-- Sélectionner une consultation --</option>
                <c:forEach var="cons" items="${consultations}">
                    <option value="${cons.id}">
                            ${cons.dossier.patient.nom} ${cons.dossier.patient.prenom} - ${cons.date_consultation}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="section">
            <label for="question">5️⃣ Question :</label>
            <textarea name="question" id="question" rows="3" placeholder="Rédiger la question..."></textarea>
        </div>

        <div class="section">
            <label for="priorite">6️⃣ Priorité :</label>
            <select name="priorite" id="priorite">
                <option value="">-- Sélectionner --</option>
                <option value="URGENTE">🚨 Urgente</option>
                <option value="NORMALE">⚖️ Normale</option>
                <option value="NON_URGENTE">🕐 Non urgente</option>
            </select>
        </div>

        <button type="submit">📤 Envoyer la demande</button>
    </form>
</c:if>

</body>
</html>
