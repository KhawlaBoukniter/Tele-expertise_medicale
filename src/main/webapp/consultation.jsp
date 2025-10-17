<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouvelle Consultation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body {
            background-color: #f0f4f8;
            font-family: 'Segoe UI', Arial, sans-serif;
        }
        h2 {
            color: #0d6efd;
            font-weight: 700;
            margin-bottom: 30px;
        }
        .card {
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.08);
            padding: 30px;
            background-color: #ffffff;
        }
        .form-label {
            font-weight: 600;
            color: #495057;
        }
        textarea.form-control, input.form-control, select.form-select {
            background-color: #f8f9fa;
            border-radius: 10px;
            border: 1px solid #dee2e6;
        }
        textarea.form-control:focus, input.form-control:focus, select.form-select:focus {
            border-color: #0d6efd;
            box-shadow: 0 0 0 0.2rem rgba(13,110,253,.25);
        }
        .form-check-label {
            margin-left: 0.5rem;
            font-weight: 500;
        }
        .actes-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 20px;
        }
        .form-check {
            background-color: #f1f5f9;
            padding: 8px 10px;
            border-radius: 8px;
            border: 1px solid #dee2e6;
            display: flex;
            align-items: center;
        }
        .form-check-input:checked {
            background-color: #0d6efd;
            border-color: #0d6efd;
        }
        .btn-primary {
            background: linear-gradient(90deg, #0d6efd, #0a58ca);
            border: none;
            font-weight: 600;
            padding: 10px 40px;
            border-radius: 10px;
            transition: all 0.3s ease;
        }
        .btn-primary:hover {
            background: linear-gradient(90deg, #0a58ca, #084298);
            transform: translateY(-2px);
        }
        .section-title {
            font-size: 1.1rem;
            font-weight: 700;
            color: #0d6efd;
            margin-top: 20px;
            margin-bottom: 10px;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 5px;
        }
        .textarea-container {
            position: relative;
        }
        @media (max-width:768px) {
            .actes-container {
                grid-template-columns: 1fr 1fr;
            }
        }
        @media (max-width:500px) {
            .actes-container {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

<div class="container mt-5 mb-5">
    <c:choose>
        <c:when test="${action == 'edit'}">
            <h2 class="text-center">Modifier la Consultation Médicale</h2>
        </c:when>
        <c:otherwise>
            <h2 class="text-center">Ajouter une Consultation Médicale</h2>
        </c:otherwise>
    </c:choose>

    <form action="consultation" method="post" class="card">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${action == 'edit'}">
            <input type="hidden" name="id" value="${consultation.id}">
        </c:if>
        <input type="hidden" name="generaliste_id" value="${user.id}">
        <input type="hidden" name="patient_id" value="${patient_id}">

        <div class="section-title">Symptômes</div>
        <div class="mb-3 textarea-container">
            <textarea class="form-control" id="symptomes" name="symptomes" rows="3"><c:out value="${consultation != null ? consultation.symptomes : ''}"/></textarea>
        </div>

        <div class="section-title">Diagnostic</div>
        <div class="mb-3 textarea-container">
            <textarea class="form-control" id="diagnostic" name="diagnostic" rows="3"><c:out value="${consultation != null ? consultation.diagnostic : ''}"/></textarea>
        </div>

        <div class="section-title">Traitement Prescrit</div>
        <div class="mb-3 textarea-container">
            <textarea class="form-control" id="traitement" name="traitement_prescrit" rows="3"><c:out value="${consultation != null ? consultation.traitement_prescrit : ''}"/></textarea>
        </div>

        <div class="section-title">Observations</div>
        <div class="mb-3 textarea-container">
            <textarea class="form-control" id="observations" name="observations" rows="3"><c:out value="${consultation != null ? consultation.observations : ''}"/></textarea>
        </div>

        <div class="row mb-3">
            <div class="col-md-4 mb-3 mb-md-0">
                <label for="cout" class="form-label">Coût</label>
                <input type="number" class="form-control" id="cout" name="cout" step="0.01" required value="${consultation != null ? consultation.cout : ''}">
            </div>
            <div class="col-md-4">
                <label for="status" class="form-label">Statut</label>
                <select class="form-select" id="status" name="status">
                    <option value="EN_ATTENTE" <c:if test="${consultation != null && consultation.status.name() == 'EN_ATTENTE'}">selected</c:if>>En attente</option>
                    <option value="EN_ATTENTE_AVIS_SPECIALISTE" <c:if test="${consultation != null && consultation.status.name() == 'EN_ATTENTE_AVIS_SPECIALISTE'}">selected</c:if>>En attente avis spécialiste</option>
                    <option value="TERMINEE" <c:if test="${consultation != null && consultation.status.name() == 'TERMINEE'}">selected</c:if>>Terminée</option>
                </select>
            </div>
        </div>

        <div class="section-title">Actes Médicaux</div>
        <div class="actes-container mb-4">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="RADIOGRAPHIE" id="radio" <c:if test="${actes != null && actes.contains('RADIOGRAPHIE')}">checked</c:if>>
                <label class="form-check-label" for="radio"><i class="bi bi-camera"></i> Radiographie</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ECHOGRAPHIE" id="echo" <c:if test="${actes != null && actes.contains('ECHOGRAPHIE')}">checked</c:if>>
                <label class="form-check-label" for="echo"><i class="bi bi-easel"></i> Échographie</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="IRM" id="irm" <c:if test="${actes != null && actes.contains('IRM')}">checked</c:if>>
                <label class="form-check-label" for="irm"><i class="bi bi-activity"></i> IRM</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="Électrocardiogramme" id="ecg" <c:if test="${actes != null && actes.contains('Électrocardiogramme')}">checked</c:if>>
                <label class="form-check-label" for="ecg"><i class="bi bi-heart"></i> ECG</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="DERMATOLOGIQUES" id="derm" <c:if test="${actes != null && actes.contains('DERMATOLOGIQUES')}">checked</c:if>>
                <label class="form-check-label" for="derm"><i class="bi bi-droplet-half"></i> Dermatologiques</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="FOND_OEIL" id="oeil" <c:if test="${actes != null && actes.contains('FOND_OEIL')}">checked</c:if>>
                <label class="form-check-label" for="oeil"><i class="bi bi-eye"></i> Fond d’œil</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ANALYSE_SANG" id="sang" <c:if test="${actes != null && actes.contains('ANALYSE_SANG')}">checked</c:if>>
                <label class="form-check-label" for="sang"><i class="bi bi-droplet"></i> Analyse de sang</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ANALYSE_URINE" id="urine" <c:if test="${actes != null && actes.contains('ANALYSE_URINE')}">checked</c:if>>
                <label class="form-check-label" for="urine"><i class="bi bi-droplet-fill"></i> Analyse d’urine</label>
            </div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <c:choose>
                    <c:when test="${action == 'edit'}">Modifier</c:when>
                    <c:otherwise>Enregistrer</c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>
</div>

</body>
</html>
