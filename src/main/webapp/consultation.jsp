<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nouvelle Consultation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <c:choose>
        <c:when test="${action == 'edit'}">
            <h2 class="text-center mb-4">Modifier la Consultation Médicale</h2>
        </c:when>
        <c:otherwise>
            <h2 class="text-center mb-4">Ajouter une Consultation Médicale</h2>
        </c:otherwise>
    </c:choose>

    <form action="consultation" method="post" class="card p-4 shadow-sm">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${action == 'edit'}">
            <input type="hidden" name="id" value="${consultation.id}">
        </c:if>
        <div class="row mb-3">
            <input type="hidden" name="generaliste_id" value="${user.id}">
            <input type="hidden" name="patient_id" value="${patient_id}">
        </div>

        <div class="mb-3">
            <label for="symptomes" class="form-label">Symptômes :</label>
            <textarea class="form-control" id="symptomes" name="symptomes" rows="3">
                <c:out value="${consultation != null ? consultation.symptomes : ''}"/>
            </textarea>
        </div>

        <div class="mb-3">
            <label for="diagnostic" class="form-label">Diagnostic :</label>
            <textarea class="form-control" id="diagnostic" name="diagnostic" rows="3">
                <c:out value="${consultation != null ? consultation.diagnostic : ''}"/>
            </textarea>
        </div>

        <div class="mb-3">
            <label for="traitement" class="form-label">Traitement Prescrit :</label>
            <textarea class="form-control" id="traitement" name="traitement_prescrit" rows="3">
                <c:out value="${consultation != null ? consultation.traitement_prescrit : ''}"/>
            </textarea>
        </div>

        <div class="mb-3">
            <label for="observations" class="form-label">Observations :</label>
            <textarea class="form-control" id="observations" name="observations" rows="3">
                <c:out value="${consultation != null ? consultation.observations : ''}"/>
            </textarea>
        </div>

        <div class="row mb-3">
            <div class="col-md-4">
                <label for="cout" class="form-label">Coût :</label>
                <input type="number" class="form-control" id="cout" name="cout" step="0.01" required value="${consultation != null ? consultation.cout : ''}">
            </div>

            <div class="col-md-3">
                <label for="status" class="form-label">Statut :</label>
                <select class="form-select" id="status" name="status">
                    <option value="EN_ATTENTE" <c:if test="${consultation != null && consultation.status.name() == 'EN_ATTENTE'}">selected</c:if>>En attente</option>
                    <option value="EN_ATTENTE_AVIS_SPECIALISTE" <c:if test="${consultation != null && consultation.status.name() == 'EN_ATTENTE_AVIS_SPECIALISTE'}">selected</c:if>>En attente avis spécialiste</option>
                    <option value="TERMINEE" <c:if test="${consultation != null && consultation.status.name() == 'TERMINEE'}">selected</c:if>>Terminée</option>
                </select>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">Actes Médicaux :</label>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="RADIOGRAPHIE" id="radio" <c:if test="${actes != null && actes.contains('RADIOGRAPHIE')}">checked</c:if>>
                <label class="form-check-label" for="radio">Radiographie</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ECHOGRAPHIE" id="echo" <c:if test="${actes != null && actes.contains('ECHOGRAPHIE')}">checked</c:if>>
                <label class="form-check-label" for="echo">Échographie</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="IRM" id="irm" <c:if test="${actes != null && actes.contains('IRM')}">checked</c:if>>
                <label class="form-check-label" for="irm">IRM</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="Électrocardiogramme" id="ecg" <c:if test="${actes != null && actes.contains('Électrocardiogramme')}">checked</c:if>>
                <label class="form-check-label" for="ecg">Électrocardiogramme</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="DERMATOLOGIQUES" id="derm" <c:if test="${actes != null && actes.contains('DERMATOLOGIQUES')}">checked</c:if>>
                <label class="form-check-label" for="derm">Dermatologiques</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="FOND_OEIL" id="oeil" <c:if test="${actes != null && actes.contains('FOND_OEIL')}">checked</c:if>>
                <label class="form-check-label" for="oeil">Fond d’œil</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ANALYSE_SANG" id="sang" <c:if test="${actes != null && actes.contains('ANALYSE_SANG')}">checked</c:if>>
                <label class="form-check-label" for="sang">Analyse de sang</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ANALYSE_URINE" id="urine" <c:if test="${actes != null && actes.contains('ANALYSE_URINE')}">checked</c:if>>
                <label class="form-check-label" for="urine">Analyse d’urine</label>
            </div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary px-4">
                <c:choose>
                    <c:when test="${action == 'edit'}">Modifier</c:when>
                    <c:otherwise>Enregistrer</c:otherwise>
                </c:choose>
            </button>
        <%--            <a href="listeConsultations.jsp" class="btn btn-secondary px-4 ms-2">Annuler</a>--%>
        </div>
    </form>
</div>

</body>
</html>
