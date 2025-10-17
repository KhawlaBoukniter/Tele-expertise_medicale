<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des demandes d'expertise</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #e8f3f9 0%, #f5fbfc 100%);
            font-family: "Segoe UI", Roboto, sans-serif;
            color: #1e293b;
        }

        h2 {
            font-weight: 600;
            color: #0f4c75;
        }

        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 25px rgba(0, 0, 0, 0.08);
            background-color: #ffffff;
            padding: 2rem;
        }

        .table {
            border-radius: 0.75rem;
            overflow: hidden;
        }

        thead {
            background-color: #0096c7 !important;
            color: white;
            font-weight: 500;
        }

        tbody tr {
            transition: 0.25s ease;
        }

        tbody tr:hover {
            background-color: #f0f9ff;
            transform: scale(1.01);
        }

        .badge {
            font-size: 0.85rem;
            padding: 0.45em 0.75em;
            border-radius: 0.5rem;
            letter-spacing: 0.3px;
        }

        .btn-action {
            display: inline-block;
            padding: 6px 14px;
            border-radius: 8px;
            font-size: 0.85rem;
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .btn-action:hover {
            transform: translateY(-2px);
        }

        .btn-consult {
            background-color: #48cae4;
            color: #fff;
        }

        .btn-consult:hover {
            background-color: #0096c7;
        }

        .btn-detail {
            background-color: #38b000;
            color: white;
        }

        .btn-detail:hover {
            background-color: #2d6a4f;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
        }

        .btn-profile {
            background-color: #00b4d8;
            color: white;
            border-radius: 10px;
            padding: 8px 16px;
            text-decoration: none;
            font-weight: 500;
            transition: 0.3s ease;
        }

        .btn-profile:hover {
            background-color: #0077b6;
        }

        .alert {
            background-color: #e3f2fd;
            border: none;
            color: #0f4c75;
        }
    </style>
</head>

<body>

<div class="container mt-5">
    <jsp:include page="navbar.jsp" />

    <div class="card mt-4 mx-auto" style="max-width: 1000px;">
        <div class="top-bar">
            <h2>📋 Liste des demandes d'expertise</h2>
            <a href="specialiste?action=profile" class="btn-profile">
                <i class="bi bi-person-circle"></i> Mon profil
            </a>
        </div>

        <form action="demandeExpertise" method="get" class="d-flex justify-content-center mb-4 gap-3">
            <input type="hidden" name="action" value="list">

            <div>
                <label for="statut" class="form-label">Statut :</label>
                <select name="statut" id="statut" class="form-select">
                    <option value="">Tous</option>
                    <option value="EN_ATTENTE">En attente</option>
                    <option value="REPONDU">Répondue</option>
                </select>
            </div>

            <div>
                <label for="priorite" class="form-label">Priorité :</label>
                <select name="priorite" id="priorite" class="form-select">
                    <option value="">Toutes</option>
                    <option value="NORMALE">Normale</option>
                    <option value="URGENTE">Urgente</option>
                </select>
            </div>

            <div class="align-self-end">
                <button type="submit" class="btn btn-success">Filtrer</button>
            </div>
        </form>

        <c:if test="${empty demandes}">
            <div class="alert text-center">Aucune demande d'expertise disponible pour le moment.</div>
        </c:if>

        <c:if test="${not empty demandes}">
            <div class="table-responsive">
                <table class="table align-middle text-center mb-0">
                    <thead>
                    <tr>
                        <th>Date de la demande</th>
                        <th>Priorité</th>
                        <th>Statut</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="d" items="${demandes}">
                        <tr>
                            <td>${d.date_demande}</td>
                            <td>
                                <span class="badge
                                    <c:choose>
                                        <c:when test='${d.priorite == "URGENTE"}'>bg-danger</c:when>
                                        <c:when test='${d.priorite == "NORMALE"}'>bg-warning text-dark</c:when>
                                        <c:otherwise>bg-info text-dark</c:otherwise>
                                    </c:choose>">
                                        ${d.priorite}
                                </span>
                            </td>
                            <td>
                                <span class="badge
                                    <c:choose>
                                        <c:when test='${d.statut == "EN_ATTENTE"}'>bg-secondary</c:when>
                                        <c:when test='${d.statut == "REPONDU"}'>bg-success</c:when>
                                        <c:otherwise>bg-light text-dark</c:otherwise>
                                    </c:choose>">
                                        ${d.statut}
                                </span>
                            </td>
                            <td>
                                <a href="demandeExpertise?action=consult&demande_id=${d.id}" class="btn-action btn-consult me-2">
                                    Consulter
                                </a>
                                <a href="demandeExpertise?action=view&id=${d.id}&consultation_id=${d.consultation.id}&generaliste_id=${user.id}" class="btn-action btn-detail">
                                    Détails
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.js"></script>
</body>
</html>
