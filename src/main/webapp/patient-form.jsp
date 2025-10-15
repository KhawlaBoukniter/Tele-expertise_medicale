<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <title>Gestion des patients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #eef2f7;
            font-family: "Segoe UI", Arial, sans-serif;
            padding: 50px;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 3px 15px rgba(0,0,0,0.1);
        }
        .btn-primary, .btn-success {
            border-radius: 8px;
        }
        .section-title {
            color: #0d6efd;
            text-align: center;
            margin-bottom: 20px;
        }
        .accordion-button:not(.collapsed) {
            background-color: #0d6efd;
            color: white;
        }
        .not-found {
            color: #dc3545;
            text-align: center;
            font-style: italic;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="section-title">🩺 Gestion des patients</h2>

    <div class="card mb-4 p-4">
        <h5>🔍 Rechercher un patient</h5>
        <form action="patient" method="get" class="row g-3">
            <input type="hidden" name="action" value="search"/>
            <div class="col-md-8">
                <label for="cin" class="form-label">CIN du patient</label>
                <input type="text" name="cin" class="form-control" value="${cinSearch}" required>
            </div>
            <div class="col-md-4 align-self-end">
                <button type="submit" class="btn btn-primary w-100">Rechercher</button>
            </div>
        </form>
    </div>

    <c:choose>
        <c:when test="${patientFound != null}">
            <div class="accordion" id="accordionPatient">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#infoPatient" aria-expanded="true">
                            👤 Infos du patient trouvé
                        </button>
                    </h2>
                    <div id="infoPatient" class="accordion-collapse collapse show">
                        <div class="accordion-body">
                            <p><strong>CIN :</strong> ${patientFound.id}</p>
                            <p><strong>Nom :</strong> ${patientFound.nom}</p>
                            <p><strong>Prénom :</strong> ${patientFound.prenom}</p>
                            <p><strong>Mutuelle :</strong>
                                <c:choose>
                                    <c:when test="${patientFound.mutuelle}">Oui</c:when>
                                    <c:otherwise>Non</c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="accordion-item mt-3">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#signesVitaux">
                            ➕ Ajouter des signes vitaux
                        </button>
                    </h2>
                    <div id="signesVitaux" class="accordion-collapse collapse">
                        <div class="accordion-body">
                            <form action="patient" method="post">
                                <input type="hidden" name="action" value="add"/>
                                <input type="hidden" name="cin" value="${patientFound.id}"/>

                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <label>Tension artérielle</label>
                                        <input type="text" name="tension_arterielle" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Température (°C)</label>
                                        <input type="number" step="0.1" name="temperature_corporelle" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Fréquence cardiaque (bpm)</label>
                                        <input type="number" name="frequence_cardiaque" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Fréquence respiratoire (bpm)</label>
                                        <input type="number" name="frequence_respiratoire" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Poids (kg)</label>
                                        <input type="number" name="poids" class="form-control">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Taille (cm)</label>
                                        <input type="number" name="taille" class="form-control">
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-success mt-3 w-100">
                                    💾 Enregistrer les signes vitaux
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <c:if test="${cinSearch != null}">
                <div class="not-found">
                    Aucun patient trouvé avec ce CIN. Vous pouvez l’ajouter ci-dessous.
                </div>
            </c:if>

            <div class="accordion" id="accordionNewPatient">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#nouveauPatient" aria-expanded="true">
                            🆕 Ajouter un nouveau patient
                        </button>
                    </h2>
                    <div id="nouveauPatient" class="accordion-collapse collapse show">
                        <div class="accordion-body">
                            <form action="patient" method="post">
                                <input type="hidden" name="action" value="add"/>

                                <h5>👤 Informations personnelles</h5>
                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <label>CIN</label>
                                        <input type="text" name="cin" class="form-control" value="${cinSearch}" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Nom</label>
                                        <input type="text" name="nom" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Prénom</label>
                                        <input type="text" name="prenom" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Date et heure d’arrivée</label>
                                        <input type="datetime-local" name="dateArrivee" class="form-control" required>
                                    </div>
                                    <div class="">
                                        <label>Mutuelle</label><br>
                                        <input type="checkbox" name="mutuelle"> Oui
                                    </div>
                                </div>

                                <hr>

                                <h5>🩹 Informations médicales</h5>
                                <label>Antécédents</label>
                                <textarea name="antecedents" class="form-control"></textarea>

                                <label>Allergies</label>
                                <textarea name="allergies" class="form-control"></textarea>

                                <label>Traitements</label>
                                <textarea name="traitements" class="form-control"></textarea>

                                <label>Coordonnées</label>
                                <textarea name="coordonnees" class="form-control"></textarea>

                                <label>Numéro de sécurité sociale</label>
                                <input type="text" name="numero_securite_sociale" class="form-control">

                                <hr>

                                <h5> Signes vitaux à l’admission</h5>
                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <label>Tension artérielle</label>
                                        <input type="text" name="tension_arterielle" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Température (°C)</label>
                                        <input type="number" step="0.1" name="temperature_corporelle" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Fréquence cardiaque (bpm)</label>
                                        <input type="number" name="frequence_cardiaque" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Fréquence respiratoire (bpm)</label>
                                        <input type="number" name="frequence_respiratoire" class="form-control" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Poids (kg)</label>
                                        <input type="number" name="poids" class="form-control">
                                    </div>
                                    <div class="col-md-6">
                                        <label>Taille (cm)</label>
                                        <input type="number" name="taille" class="form-control">
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-success mt-3 w-100">
                                    💾 Enregistrer le patient
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
