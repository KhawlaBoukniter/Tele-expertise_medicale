<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8"/>
    <title>Créer un spécialiste</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- FullCalendar -->
    <link href='https://cdn.jsdelivr.net/npm/@fullcalendar/core@6.1.19/index.global.min.css' rel='stylesheet' />
    <link href='https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@6.1.19/index.global.min.css' rel='stylesheet' />
    <link href='https://cdn.jsdelivr.net/npm/@fullcalendar/timegrid@6.1.19/index.global.min.css' rel='stylesheet' />

    <script src='https://cdn.jsdelivr.net/npm/@fullcalendar/core@6.1.19/index.global.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/@fullcalendar/daygrid@6.1.19/index.global.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/@fullcalendar/timegrid@6.1.19/index.global.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/@fullcalendar/interaction@6.1.19/index.global.min.js'></script>

    <style>
        body {
            background: linear-gradient(135deg, #e8f3f9 0%, #f4fcfb 100%);
            font-family: "Segoe UI", Roboto, sans-serif;
            color: #1e293b;
        }

        h2 {
            font-weight: 600;
            color: #0f4c75;
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 6px 25px rgba(0, 0, 0, 0.08);
            background-color: #ffffff;
            padding: 2rem;
        }

        .form-label {
            font-weight: 500;
            color: #334155;
        }

        select, input {
            border-radius: 0.5rem !important;
        }

        #calendrier {
            background: #ffffff;
            border-radius: 1rem;
            padding: 1rem;
            box-shadow: 0 3px 15px rgba(0, 0, 0, 0.07);
            transition: all 0.3s ease;
        }

        #calendrier:hover {
            transform: scale(1.01);
        }

        .btn-success {
            background-color: #2dd4bf;
            border-color: #2dd4bf;
            color: white;
            font-weight: 500;
            border-radius: 0.6rem;
            box-shadow: 0 3px 8px rgba(45, 212, 191, 0.3);
            transition: all 0.3s ease;
        }

        .btn-success:hover {
            background-color: #0d9488;
            transform: translateY(-1px);
        }

        .alert {
            background-color: #dcfce7;
            color: #14532d;
            border: none;
            border-radius: 0.75rem;
            font-weight: 500;
        }

        .form-select:focus, .form-control:focus {
            border-color: #38bdf8;
            box-shadow: 0 0 0 0.15rem rgba(56, 189, 248, 0.25);
        }

        /* FullCalendar style override */
        .fc-toolbar-title {
            color: #0f4c75;
            font-weight: 600;
        }

        .fc-button-primary {
            background-color: #0096c7 !important;
            border: none !important;
            border-radius: 0.5rem !important;
        }

        .fc-button-primary:hover {
            background-color: #0077b6 !important;
        }

        .fc-timegrid-slot {
            height: 2.5em !important;
        }

        .fc-event {
            background-color: #3b82f6 !important;
            border: none !important;
            border-radius: 6px;
            font-size: 0.85rem;
        }

        .fc-event:hover {
            background-color: #2563eb !important;
        }
    </style>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendrier');
            var slots = [];

            var existingSlots = [
                <c:forEach var="c" items="${creneaux}" varStatus="loop">
                {
                    start: '${c.date_debut}',
                    end: '${c.date_fin}',
                    title: 'Créneau existant'
                }<c:if test="${!loop.last}">,</c:if>
                </c:forEach>
            ];

            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'timeGridWeek',
                selectable: true,
                height: '600px',
                allDaySlot: false,
                slotMinTime: "08:00:00",
                slotMaxTime: "20:00:00",
                events: existingSlots,
                select: function(info) {
                    calendar.addEvent({
                        title: 'Créneau',
                        start: info.start,
                        end: info.end,
                        allDay: false
                    });
                    slots.push({ start: info.startStr, end: info.endStr });
                    document.getElementById('crenauxInput').value = JSON.stringify(slots);
                    calendar.unselect();
                },
                eventClick: function(info) {
                    if(confirm("Supprimer ce créneau ?")) {
                        slots = slots.filter(slot => slot.start !== info.event.startStr || slot.end !== info.event.endStr);
                        info.event.remove();
                        document.getElementById('crenauxInput').value = JSON.stringify(slots);
                    }
                }
            });

            calendar.render();
        });
    </script>
</head>

<body>

<div class="container py-5">
    <jsp:include page="navbar.jsp" />

    <div class="card mt-4 mx-auto" style="max-width: 1000px;">
        <h2>👨‍⚕️ Créer un spécialiste</h2>

        <form id="specialisteForm" action="specialiste" method="post" class="needs-validation" novalidate>
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="specialiste_id" value="${user.id}">

            <c:if test="${not empty sessionScope.message}">
                <div class="alert text-center mb-4">${sessionScope.message}</div>
                <c:remove var="message" scope="session"/>
            </c:if>

            <div class="row g-4 mb-4">
                <div class="col-md-6">
                    <label class="form-label">Spécialité</label>
                    <select name="specialite" class="form-select" required>
                        <option value="${specialiste.specialite.name() != null ? specialiste.specialite.name() : ''}">
                            ${specialiste.specialite.name() != null ? specialiste.specialite.name() : '-- choisir --'}
                        </option>
                        <c:forEach var="s" items="${specialites}">
                            <option value="${s}">${s}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Tarif (MAD)</label>
                    <input name="tarif" class="form-control" type="number" step="0.01"
                           value="${specialiste.tarif != null ? specialiste.tarif : ''}" required/>
                </div>
            </div>

            <h5 class="text-center mb-3" style="color:#0f4c75;">🗓️ Planification de vos créneaux</h5>
            <div id="calendrier" class="mb-4"></div>
            <input type="hidden" id="crenauxInput" name="crenaux" />

            <div class="text-center">
                <button type="submit" class="btn btn-success px-5 py-2">
                    <i class="bi bi-person-plus-fill"></i> Mettre à jour profile
                </button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.js"></script>
</body>
</html>
