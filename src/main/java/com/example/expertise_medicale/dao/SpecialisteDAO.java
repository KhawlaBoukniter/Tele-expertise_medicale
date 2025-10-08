package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Specialiste;
import jakarta.persistence.EntityManager;

public class SpecialisteDAO extends UserDAO<Specialiste> {

    public SpecialisteDAO() {
        super(Specialiste.class);
    }
}
