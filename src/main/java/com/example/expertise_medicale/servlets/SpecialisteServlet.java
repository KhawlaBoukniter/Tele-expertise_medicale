package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.Creneau;
import com.example.expertise_medicale.models.DemandeExpertise;
import com.example.expertise_medicale.models.Specialiste;
import com.example.expertise_medicale.models.User;
import com.example.expertise_medicale.models.enums.Specialite;
import com.example.expertise_medicale.services.CreneauService;
import com.example.expertise_medicale.services.DemandeExpertiseService;
import com.example.expertise_medicale.services.SpecialisteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SpecialisteServlet", value = "/specialiste")
public class SpecialisteServlet extends HttpServlet {

    private SpecialisteService specialisteService = new SpecialisteService();
    private CreneauService creneauService = new CreneauService();
    private DemandeExpertiseService demandeExpertiseService = new DemandeExpertiseService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) { action = "specialiste"; }

        if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");

        Specialiste specialiste = specialisteService.findById(user.getId());

        request.setAttribute("specialiste", specialiste);

        if (action.equals("profile")) {
            List<Specialite> specialites = Arrays.asList(Specialite.values());
            request.setAttribute("specialites", specialites);

            List<Creneau> creneaux = creneauService.findBySpecialiste(specialiste.getId());
            request.setAttribute("creneaux", creneaux);


            request.getRequestDispatcher("specialiste.jsp").forward(request, response);
        } else {
            List<DemandeExpertise> demandes = demandeExpertiseService.findBySpecialiste(specialiste.getId());
            request.setAttribute("demandes", demandes);

            request.getRequestDispatcher("listeDemandes.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long specialisteId = Long.parseLong(request.getParameter("specialiste_id"));
        Specialiste specialiste = specialisteService.findById(specialisteId);
        String crenauxJson = request.getParameter("crenaux");

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> slots = mapper.readValue(crenauxJson, new TypeReference<List<Map<String, String>>>() {});

        for (Map<String, String> slot : slots) {
            String startStr = slot.get("start");
            String endStr = slot.get("end");

            Creneau c = new Creneau();
            c.setDate_debut(OffsetDateTime.parse(startStr).toLocalDateTime());
            c.setDate_fin(OffsetDateTime.parse(endStr).toLocalDateTime());
            c.setDisponible(true);
            c.setSpecialiste(specialiste);

            creneauService.add(c);
        }

        specialiste.setTarif(Double.parseDouble(request.getParameter("tarif")));
        specialiste.setSpecialite(Enum.valueOf(Specialite.class, request.getParameter("specialite")));
        specialisteService.saveOrUpdate(specialiste);

        request.getSession().setAttribute("message", "Créneaux enregistrés avec succès !");

        response.sendRedirect("specialiste");
    }
}
