<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nouvelle Consultation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Ajouter une Consultation Médicale</h2>

    <form action="consultation" method="post" class="card p-4 shadow-sm">

        <div class="row mb-3">
            <input type="hidden" name="generaliste_id" value="${user.id}">
            <input type="hidden" name="patient_id" value="${patient_id}">
        </div>

        <div class="mb-3">
            <label for="symptomes" class="form-label">Symptômes :</label>
            <textarea class="form-control" id="symptomes" name="symptomes" rows="3" required></textarea>
        </div>

        <div class="mb-3">
            <label for="diagnostic" class="form-label">Diagnostic :</label>
            <textarea class="form-control" id="diagnostic" name="diagnostic" rows="3" required></textarea>
        </div>

        <div class="mb-3">
            <label for="traitement" class="form-label">Traitement Prescrit :</label>
            <textarea class="form-control" id="traitement" name="traitement_prescrit" rows="3"></textarea>
        </div>

        <div class="mb-3">
            <label for="observations" class="form-label">Observations :</label>
            <textarea class="form-control" id="observations" name="observations" rows="3"></textarea>
        </div>

        <div class="row mb-3">
            <div class="col-md-4">
                <label for="cout" class="form-label">Coût :</label>
                <input type="number" class="form-control" id="cout" name="cout" step="0.01" required>
            </div>

            <div class="col-md-3">
                <label for="status" class="form-label">Statut :</label>
                <select class="form-select" id="status" name="status">
                    <option value="EN_ATTENTE_AVIS_SPECIALISTE">En attente avis spécialiste</option>
                    <option value="EN_ATTENTE">En attente</option>
                    <option value="TERMINEE">Terminée</option>
                </select>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">Actes Médicaux :</label>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="RADIOGRAPHIE" id="radio">
                <label class="form-check-label" for="radio">Radiographie</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ECHOGRAPHIE" id="echo">
                <label class="form-check-label" for="echo">Échographie</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="IRM" id="irm">
                <label class="form-check-label" for="irm">IRM</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="Électrocardiogramme" id="ecg">
                <label class="form-check-label" for="ecg">Électrocardiogramme</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="DERMATOLOGIQUES" id="derm">
                <label class="form-check-label" for="derm">Dermatologiques</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="FOND_OEIL" id="oeil">
                <label class="form-check-label" for="oeil">Fond d’œil</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ANALYSE_SANG" id="sang">
                <label class="form-check-label" for="sang">Analyse de sang</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="actesMedicaux" value="ANALYSE_URINE" id="urine">
                <label class="form-check-label" for="urine">Analyse d’urine</label>
            </div>
        </div>


        <div class="text-center">
            <button type="submit" class="btn btn-primary px-4">Enregistrer</button>
<%--            <a href="listeConsultations.jsp" class="btn btn-secondary px-4 ms-2">Annuler</a>--%>
        </div>
    </form>
</div>

</body>
</html>
