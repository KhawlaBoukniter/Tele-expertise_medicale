package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.ConsultationDAO;
import com.example.expertise_medicale.dao.PatientDAO;
import com.example.expertise_medicale.dao.SigneVitalDAO;
import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.models.SigneVital;
import com.example.expertise_medicale.models.enums.PatientStatus;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class PatientService {
    private PatientDAO patientDAO = new PatientDAO();
    private SigneVitalDAO signeVitalDAO = new SigneVitalDAO();
    private ConsultationService consultationService = new ConsultationService();

    public void add(Patient patient, SigneVital signeVital) {
        if (findById(patient.getId()) == null) {
            patientDAO.add(patient);
        } else {
            patientDAO.update(patient);
        }
        signeVital.setPatient(patient);
        signeVitalDAO.add(signeVital);
    }

    public void update(Patient patient) {
        patientDAO.update(patient);
    }

    public void delete(Patient patient){ patientDAO.delete(patient);}

    public List<Patient> findAll(){ return patientDAO.findAll(); }

    public List<Patient> findByToday() {
        return findAll().stream()
                .filter(p -> p.getDateArrivee().toLocalDate().equals(LocalDate.now()))
                .filter(p -> p.getStatus().equals(PatientStatus.EN_ATTENTE))
                .sorted(Comparator.comparing(Patient::getDateArrivee))
                .toList();
    }

    public Patient findById(String id) {
        return findAll().stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
    }
}
