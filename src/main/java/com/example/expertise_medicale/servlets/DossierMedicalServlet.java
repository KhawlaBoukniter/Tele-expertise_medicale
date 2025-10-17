package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.DossierMedical;
import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.services.DossierMedicalService;
import com.example.expertise_medicale.services.PatientService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DossierMedicalServlet", value = "/dossierMedical")
public class DossierMedicalServlet extends HttpServlet {

    private DossierMedicalService dossierService = new DossierMedicalService();
    private PatientService patientService = new PatientService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String  patientId = request.getParameter("id");
        String demandeId = request.getParameter("demande_id");
        String userRole =  request.getParameter("user_role");

        Patient patient = patientService.findById(patientId);
        DossierMedical dossier = dossierService.findByPatient(patientId);

        if (dossier == null) {
            dossier = new DossierMedical();
            dossier.setPatient(patient);
        }

        request.setAttribute("patient", patient);
        request.setAttribute("dossier", dossier);
        request.setAttribute("demande", demandeId);
        request.setAttribute("role", userRole);

        request.getRequestDispatcher("dossierMedical.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("patient_id");
        String antecedents = request.getParameter("antecedents");
        String allergies = request.getParameter("allergies");
        String traitements = request.getParameter("traitements");

        Patient patient = patientService.findById(patientId);
        DossierMedical dossier = dossierService.findByPatient(patientId);

        if (dossier == null) dossier = new DossierMedical();
        dossier.setPatient(patient);
        dossier.setAntecedents(antecedents);
        dossier.setAllergies(allergies);
        dossier.setTraitements(traitements);

        dossierService.saveOrUpdate(dossier);
        response.sendRedirect("patient?action=list");
    }
}
