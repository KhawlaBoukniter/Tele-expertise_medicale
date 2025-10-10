package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.ConsultationDAO;
import com.example.expertise_medicale.dao.DossierMedicalDAO;
import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.DossierMedical;

import java.util.List;

public class ConsultationService {
    private ConsultationDAO consultationDAO = new ConsultationDAO();
    private DossierMedicalDAO dossierMedicalDAO = new DossierMedicalDAO();

    public void add(Consultation consultation, DossierMedical dossierMedical) {
        dossierMedicalDAO.add(dossierMedical);
        consultation.setDossier(dossierMedical);
        consultationDAO.add(consultation);
    }

    public void update(Consultation consultation) {
        consultationDAO.update(consultation);
    }

    public void delete(Consultation consultation){ consultationDAO.delete(consultation);}

    public List<Consultation> findAll(){ return consultationDAO.findAll(); }


}
