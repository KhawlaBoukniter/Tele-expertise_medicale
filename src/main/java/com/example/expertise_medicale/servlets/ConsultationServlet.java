package com.example.expertise_medicale.servlets;

import com.example.expertise_medicale.models.*;
import com.example.expertise_medicale.models.enums.PatientStatus;
import com.example.expertise_medicale.models.enums.StatutConsultation;
import com.example.expertise_medicale.models.enums.TypeActeMedical;
import com.example.expertise_medicale.services.ActeMedicalService;
import com.example.expertise_medicale.services.ConsultationService;
import com.example.expertise_medicale.services.GeneralisteService;
import com.example.expertise_medicale.services.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/consultation")
public class ConsultationServlet extends HttpServlet {
    private ConsultationService consultationService = new ConsultationService();
    private GeneralisteService generalisteService = new GeneralisteService();
    private PatientService patientService = new PatientService();
    private ActeMedicalService acteMedicalService = new ActeMedicalService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("patient_id");
        String generalisteId = request.getParameter("generaliste_id");
        String action = request.getParameter("action");

        if (action == null) action = "add";

        request.setAttribute("user", request.getSession().getAttribute("user"));

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("user", user);

        if (patientId != null) {
            request.setAttribute("patient_id", patientId);
        }
        if (generalisteId != null) {
            request.setAttribute("generaliste_id", generalisteId);
        }

        if (action.equals("edit")) {
            String consultationId = request.getParameter("id");
            Consultation consultation = consultationService.findById(Long.valueOf(consultationId));
            request.setAttribute("consultation", consultation);
            request.setAttribute("actes", acteMedicalService.getActesByConsultation(Long.valueOf(consultationId)).stream().map(a -> a.getActeMedical().name()).toList());
            request.setAttribute("action", "edit");
            request.getRequestDispatcher("consultation.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.setAttribute("action", "add");
            request.getRequestDispatcher("consultation.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String diagnostic = request.getParameter("diagnostic");
        String symptomes =  request.getParameter("symptomes");
        String observations = request.getParameter("observations");
        String traitementPrescrit = request.getParameter("traitement_prescrit");
        Double cout =  Double.parseDouble(request.getParameter("cout"));
        StatutConsultation statutConsultation = StatutConsultation.valueOf(request.getParameter("status"));
        Generaliste generaliste = generalisteService.findById(Long.valueOf(request.getParameter("generaliste_id")));

        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setPatient(patientService.findById(request.getParameter("patient_id")));
        dossierMedical.setDate_creation(LocalDateTime.now());

        Consultation consultation = new Consultation();
        consultation.setDiagnostic(diagnostic);
        consultation.setSymptomes(symptomes);
        consultation.setObservations(observations);
        consultation.setCout(cout);
        consultation.setDate_consultation(LocalDateTime.now());
        consultation.setTraitement_prescrit(traitementPrescrit);
        consultation.setGeneraliste(generaliste);
        consultation.setStatus(statutConsultation);

        consultationService.add(consultation, dossierMedical);
        Patient patient = patientService.findById(request.getParameter("patient_id"));
        patient.setStatus(PatientStatus.TERMINEE);
        patientService.update(patient);

        String[] actesMedicaux = request.getParameterValues("actesMedicaux");
        if (actesMedicaux != null) {
            for (String acteStr : actesMedicaux) {
                TypeActeMedical type = TypeActeMedical.valueOf(acteStr);
                ActeMedical acte = new ActeMedical();
                acte.setActeMedical(type);
                acte.setConsultation(consultation);
                acte.setCout(200.0);
                acteMedicalService.add(acte);

                consultation.setCout(consultation.getCout() + acte.getCout());
                consultationService.update(consultation);
            }
        }

        response.sendRedirect("generaliste?action=list");
    }

}
