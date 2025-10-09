package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.models.SigneVital;
import com.example.expertise_medicale.services.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/patient")
public class PatientServlet extends HttpServlet {
    private PatientService patientService = new PatientService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                request.getRequestDispatcher("patient-form.jsp").forward(request, response);
                break;
            case "list":
                List<Patient> patients = patientService.findAll();
                request.setAttribute("patients", patients);
                request.getRequestDispatcher("patients.jsp").forward(request, response);
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                Patient patient = new Patient();
                patient.setId(request.getParameter("cin"));
                patient.setNom(request.getParameter("nom"));
                patient.setPrenom(request.getParameter("prenom"));
                patient.setAntecedents(request.getParameter("antecedents"));
                patient.setAllergies(request.getParameter("allergies"));
                patient.setTraitements(request.getParameter("traitements"));
                patient.setCoordonnees(request.getParameter("coordonnees"));
                patient.setMutuelle("on".equals(request.getParameter("mutuelle")));
                patient.setNumero_securite_sociale(request.getParameter("numero_securite_sociale"));
                patient.setDateArrivee(LocalDateTime.now());


                SigneVital signeVital = new SigneVital();
                signeVital.setDate_mesure(LocalDateTime.now());
                signeVital.setTension_arterielle(request.getParameter("tension_arterielle"));
                signeVital.setTemperature_corporelle(Double.parseDouble(request.getParameter("temperature_corporelle")));
                signeVital.setFrequence_cardiaque(Integer.parseInt(request.getParameter("frequence_cardiaque")));
                signeVital.setFrequence_respiratoire(Double.parseDouble(request.getParameter("frequence_respiratoire")));
                signeVital.setPoids(Double.parseDouble(request.getParameter("poids")));
                signeVital.setTaille(Double.parseDouble(request.getParameter("taille")));
                signeVital.setPatient(patient);

                patientService.add(patient, signeVital);

                response.sendRedirect("patient?action=list");
                break;
            case "update":
                break;
        }
    }
}
