package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.DemandeExpertiseDAO;
import com.example.expertise_medicale.models.DemandeExpertise;
import com.example.expertise_medicale.models.Specialiste;

import java.util.List;

public class DemandeExpertiseService {
    private DemandeExpertiseDAO demandeExpertiseDAO = new DemandeExpertiseDAO();

    public void add(DemandeExpertise demandeExpertise) {
        demandeExpertiseDAO.add(demandeExpertise);
    }

    public void update(DemandeExpertise demandeExpertise) {
        demandeExpertiseDAO.update(demandeExpertise);
    }

    public List<DemandeExpertise> findAll() {
        return demandeExpertiseDAO.findAll();
    }

    public List<DemandeExpertise> findBySpecialiste(Specialiste specialiste) {
        return findAll().stream().filter(s -> s.getId().equals(specialiste.getId())).toList();
    }

    public DemandeExpertise findById(Long id) {
        return demandeExpertiseDAO.find(id);
    }
}
