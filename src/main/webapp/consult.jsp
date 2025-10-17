<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Détails de la demande d'expertise</title>
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
            --success: #16a34a;
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
            max-width: 900px;
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

        .section-title {
            font-weight: 600;
            color: var(--primary);
            margin-bottom: 10px;
        }

        .border-box {
            border: 1px solid var(--border);
            border-radius: 10px;
            background-color: #ffffff;
            padding: 15px;
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 0.5rem;
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

        .btn-success {
            background-color: var(--success);
        }

        .btn-success:hover {
            background-color: #15803d;
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

        .alert-success {
            border-radius: 8px;
            background-color: #dcfce7;
            color: #166534;
            padding: 10px 15px;
        }
        .btn-back { text-align:center; padding:10px; border-radius:8px; font-weight:600; text-decoration:none; transition:0.3s ease; }
        .btn-back { background-color:var(--secondary-color); color:#fff; margin-top:10px;}
        .btn-back:hover { background-color:var(--accent-color); transform:scale(1.02);}

        @media (max-width: 768px) {
            .card {
                padding: 20px;
            }
        }
    </style>
</head>

<body>

<div class="container">
    <jsp:include page="navbar.jsp" />
    <a href="javascript:history.back()" class="btnp">
        ⬅ Retour
    </a>

    <div class="card">
        <div class="card-header">🩺 Détails de la demande d'expertise</div>
        <div class="card-body">

            <div class="row mb-4">
                <div class="col-md-6">
                    <p><strong>ID :</strong> ${demande.id}</p>
                    <p><strong>Date de demande :</strong> ${demande.date_demande}</p>
                    <p><strong>Priorité :</strong>
                        <span class="badge
                            <c:choose>
                                <c:when test='${demande.priorite == "URGENTE"}'>bg-danger</c:when>
                                <c:when test='${demande.priorite == "NORMALE"}'>bg-warning text-dark</c:when>
                                <c:otherwise>bg-success</c:otherwise>
                            </c:choose>">
                            ${demande.priorite}
                        </span>
                    </p>
                </div>
                <div class="col-md-6">
                    <p><strong>Statut :</strong>
                        <span class="badge
                            <c:choose>
                                <c:when test='${demande.statut == "EN_ATTENTE"}'>bg-secondary</c:when>
                                <c:when test='${demande.statut == "REPONDU"}'>bg-success</c:when>
                            </c:choose>">
                            ${demande.statut}
                        </span>
                    </p>
                    <c:if test="${not empty demande.date_reponse}">
                        <p><strong>Date de réponse :</strong> ${demande.date_reponse}</p>
                    </c:if>
                </div>
            </div>

            <div>
                <h5 class="section-title">❓ Question du médecin</h5>
                <div class="border-box">
                    <p class="mb-0">${demande.question}</p>
                </div>
            </div>

            <div>
                <h5 class="section-title">📋 Informations sur la consultation</h5>
                <div class="border-box">
                    <p><strong>ID Consultation :</strong> ${demande.consultation.id}</p>
                    <p><strong>Date consultation :</strong> ${demande.consultation.date_consultation}</p>
                    <p><strong>Dossier médical :</strong>
                        <a href="dossierMedical?demande_id=${demande.id}&id=${demande.consultation.dossier.patient.id}">
                            Voir dossier médical
                        </a>
                    </p>
                    <p><strong>Patient :</strong> ${demande.consultation.dossier.patient.nom} ${demande.consultation.dossier.patient.prenom}</p>
                </div>
            </div>

            <div>
                <h5 class="section-title">🧠 Avis du spécialiste</h5>
                <c:choose>
                    <c:when test="${empty demande.avis}">
                        <form action="demandeExpertise?action=consult" method="post" class="mt-3">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

                            <input type="hidden" name="demande_id" value="${demande.id}">
                            <div class="mb-3">
                                <label for="avis">Votre avis :</label>
                                <textarea name="avis" id="avis" placeholder="Saisissez ici votre avis médical..." required></textarea>
                            </div>
                            <button type="submit" class="btnp btn-success">✅ Enregistrer l’avis</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="alert-success mt-2">
                            <strong>Avis enregistré :</strong>
                            <p class="mb-0 mt-2">${demande.avis}</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</div>
</body>
</html>
