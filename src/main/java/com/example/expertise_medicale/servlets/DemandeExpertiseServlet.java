package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.*;
import com.example.expertise_medicale.models.enums.Priorite;
import com.example.expertise_medicale.models.enums.Specialite;
import com.example.expertise_medicale.models.enums.StatutConsultation;
import com.example.expertise_medicale.models.enums.StatutDemande;
import com.example.expertise_medicale.services.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/demandeExpertise")
public class DemandeExpertiseServlet extends HttpServlet {

    private SpecialisteService specialisteService = new SpecialisteService();
    private ConsultationService consultationService = new ConsultationService();
    private DemandeExpertiseService demandeService = new DemandeExpertiseService();
    private GeneralisteService generalisteService = new GeneralisteService();
    private CreneauService creneauService = new CreneauService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Specialite> specialites = specialisteService.getAllSpecialites();
        request.setAttribute("specialites", specialites);
        String action = request.getParameter("action");

        String specialite = request.getParameter("specialite");
        if (specialite != null) {
            List<Specialiste> specialistes = specialisteService.findBySpecialite(Specialite.valueOf(specialite));
            request.setAttribute("specialistes", specialistes);
        }

        String specialisteId = request.getParameter("specialisteId");
        if (specialisteId != null) {
            List<Creneau> creneaux = creneauService.getCreneauxDisponibles(Long.parseLong(specialisteId));
            request.setAttribute("creneaux", creneaux);

            List<Consultation> consultations = consultationService.findEnAttente();
            request.setAttribute("consultations", consultations);
        }

        List<DemandeExpertise> demandes = demandeService.findAll();
        request.setAttribute("demandes", demandes);

        if (action == null) {
            request.getRequestDispatcher("demandeExpertise.jsp").forward(request, response);
        }

        if (action.equals("consult")) {
            DemandeExpertise demandeExpertise = demandeService.findById(Long.parseLong(request.getParameter("demande_id")));

            request.setAttribute("demande", demandeExpertise);

            request.getRequestDispatcher("/consult.jsp").forward(request, response);
        } else if (action.equals("view")) {
            String demandeId = request.getParameter("id");
            DemandeExpertise demandeExpertise = demandeService.findById(Long.parseLong(demandeId));
            request.setAttribute("demande", demandeExpertise);

            String consultationId = request.getParameter("consultation_id");
            Consultation consultation = consultationService.findById(Long.parseLong(consultationId));
            request.setAttribute("consultation", consultation);

            String id = request.getParameter("generaliste_id");
            User user = generalisteService.findById(Long.parseLong(id));
            request.setAttribute("user", user);


            request.getRequestDispatcher("/demandeDetails.jsp").forward(request, response);
        } else if (action.equals("list")) {
            request.getRequestDispatcher("/listeDemandes.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("demandeExpertise.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equals("consult")) {
            String demandeId = request.getParameter("demande_id");

            DemandeExpertise demande = demandeService.findById(Long.parseLong(demandeId));
            demande.setAvis(request.getParameter("avis"));
            demande.setDate_reponse(LocalDateTime.now());
            demande.setStatut(StatutDemande.REPONDU);

            demandeService.update(demande);

            response.sendRedirect("specialiste");

        }
        else {
            String consultationId = request.getParameter("consultation_id");
            String specialisteId = request.getParameter("specialiste_id");
            String question = request.getParameter("question");
            Priorite priorite = Priorite.valueOf(request.getParameter("priorite"));
            Creneau creneau = creneauService.getCreneauById(Long.parseLong(request.getParameter("creneau")));

            DemandeExpertise demande = new DemandeExpertise();
            demande.setDate_demande(LocalDateTime.now());
            demande.setQuestion(question);
            demande.setPriorite(priorite);
            demande.setStatut(StatutDemande.EN_ATTENTE);
            demande.setCreneau(creneau);
            demande.setConsultation(consultationService.findById(Long.parseLong(consultationId)));
            demande.setSpecialiste(specialisteService.findById(Long.parseLong(specialisteId)));

            demandeService.add(demande);
            creneau.setDisponible(false);
            creneauService.update(creneau);

            response.sendRedirect("generaliste?action=list");
        }


    }
}
