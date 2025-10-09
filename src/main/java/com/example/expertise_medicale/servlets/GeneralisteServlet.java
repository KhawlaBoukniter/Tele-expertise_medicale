package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.models.enums.Role;
import com.example.expertise_medicale.services.GeneralisteService;
import com.example.expertise_medicale.services.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "GeneralisteServlet", value = "/generaliste")
public class GeneralisteServlet extends HttpServlet {
    private GeneralisteService generalisteService  = new GeneralisteService();
    private PatientService patientService  = new PatientService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) { action = "list"; }

        switch (action) {
            case "list":
                List<Patient> patients = patientService.findByToday();
                request.setAttribute("patients", patients);
                request.getRequestDispatcher("generaliste.jsp").forward(request, response);
                break;
            default:

                request.getRequestDispatcher("generaliste.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action =  request.getParameter("action");

        switch (action) {
            case "add":
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                Generaliste generaliste = new Generaliste();
                generaliste.setNom(nom);
                generaliste.setPrenom(prenom);
                generaliste.setEmail(email);
                generaliste.setPassword(password);
                generaliste.setRole(Role.GENERALISTE);
                generaliste.setCreated_at(LocalDateTime.now());

                generalisteService.add(generaliste);

                response.sendRedirect("generaliste?action=list");
        }
    }
}
