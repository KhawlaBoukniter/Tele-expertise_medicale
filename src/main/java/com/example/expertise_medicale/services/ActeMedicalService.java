package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.ActeMedicalDAO;
import com.example.expertise_medicale.models.ActeMedical;

import java.util.List;

public class ActeMedicalService {
    private ActeMedicalDAO acteMedicalDAO = new ActeMedicalDAO();

    public void add(ActeMedical acte) {
        acteMedicalDAO.add(acte);
    }

    public List<ActeMedical> getActesByConsultation(Long id) {
        return acteMedicalDAO.findAll().stream().filter(a -> a.getConsultation().getId().equals(id)).toList();
    }
}
