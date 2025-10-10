package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.ActeMedicalDAO;
import com.example.expertise_medicale.models.ActeMedical;

public class ActeMedicalService {
    private ActeMedicalDAO acteMedicalDAO = new ActeMedicalDAO();

    public void add(ActeMedical acte) {
        acteMedicalDAO.add(acte);
    }
}
