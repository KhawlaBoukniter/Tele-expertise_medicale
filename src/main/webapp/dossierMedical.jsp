<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <title>Dossier Médical - ${patient.nom} ${patient.prenom}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Garde ton CSS existant */
        :root { --bg-color: #0d1117; --card-bg: #161b22; --text-color: #e6edf3; --accent-color: #1db954; --secondary-color: #58a6ff; --border-color: #30363d; --danger-color: #ff5555; }
        body { background-color: var(--bg-color); font-family: "Segoe UI", Arial, sans-serif; color: var(--text-color); padding: 30px; line-height: 1.6; }
        .fiche-container { max-width: 900px; margin: auto; background-color: var(--card-bg); padding: 30px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.4); border:1px solid var(--border-color);}
        h2 { text-align:center; color:var(--accent-color); margin-bottom:25px; font-size:1.8rem; }
        .section-title { font-size:1.1rem; font-weight:600; color:var(--secondary-color); margin-bottom:8px; border-bottom:1px solid var(--border-color); padding-bottom:4px; }
        .section-content { background-color:#1c2128; padding:15px; border-radius:8px; margin-bottom:20px; border:1px solid var(--border-color); }
        .info-grid { display:flex; flex-wrap:wrap; gap:15px; margin-bottom:20px; }
        .info-card { flex:1 1 45%; background-color:#1c2128; padding:15px; border-radius:8px; border:1px solid var(--border-color); }
        .info-card label { font-weight:600; color:var(--secondary-color); display:block; margin-bottom:6px; }
        .btn-back, .btn-submit { text-align:center; padding:10px; border-radius:8px; font-weight:600; text-decoration:none; transition:0.3s ease; }
        .btn-back { background-color:var(--secondary-color); color:#fff; margin-top:10px;}
        .btn-back:hover { background-color:var(--accent-color); transform:scale(1.02);}
        .btn-submit { background-color:var(--accent-color); color:#fff; border:none; cursor:pointer; margin-top:15px;}
        .btn-submit:hover { background-color:var(--secondary-color);}
        textarea, input[type=text], input[type=date] { width:100%; padding:8px; border-radius:5px; border:1px solid var(--border-color); background-color:#0d1117; color:var(--text-color);}
        em { color:#aaa; }
        @media (max-width:600px){ .info-card { flex:1 1 100%; } }
    </style>
</head>
<body>

<div class="fiche-container">
    <h2>🩺 Dossier Médical de ${patient.nom} ${patient.prenom}</h2>

    <form action="dossierMedical" method="post">
        <input type="hidden" name="patient_id" value="${patient.id}"/>

        <div class="section-title">Informations personnelles</div>
        <div class="info-grid">
            <div class="info-card">
                <label>ID Patient :</label>
                <p>${patient.id}</p>
            </div>
            <div class="info-card">
                <label>Date d’arrivée :</label>
                ${patient.dateArrivee}
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
                ${patient.coordonnees}
            </div>
        </div>

        <div class="section-title">Antécédents</div>
        <div class="section-content">
            <textarea name="antecedents" rows="4" placeholder="Aucun antécédent enregistré">${dossier.antecedents}</textarea>
        </div>

        <div class="section-title">Allergies</div>
        <div class="section-content">
            <textarea name="allergies" rows="3" placeholder="Aucune allergie signalée">${dossier.allergies}</textarea>
        </div>

        <div class="section-title">Traitements en cours</div>
        <div class="section-content">
            <textarea name="traitements" rows="3" placeholder="Aucun traitement actif">${dossier.traitements}</textarea>
        </div>

        <button type="submit" class="btn-submit">💾 Enregistrer les modifications</button>
    </form>

    <a href="javascript:history.back()" class="btn-back">⬅ Retour</a>
</div>

</body>
</html>
