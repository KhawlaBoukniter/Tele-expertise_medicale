package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.DossierMedicalDAO;
import com.example.expertise_medicale.models.DossierMedical;
import com.example.expertise_medicale.models.Patient;

public class DossierMedicalService {
    private DossierMedicalDAO dossierDAO = new DossierMedicalDAO();

    public void add(DossierMedical dossierMedical){ dossierDAO.add(dossierMedical); }

    public DossierMedical findByPatient(String id) {
        return dossierDAO.findAll().stream().filter(d -> d.getPatient().getId().equals(id)).findFirst().orElse(null);
    }

    public void saveOrUpdate(DossierMedical dossier) {
        if  (dossier.getId() == null) {
            dossierDAO.add(dossier);
        } else  {
            dossierDAO.update(dossier);
        }
    }
}
