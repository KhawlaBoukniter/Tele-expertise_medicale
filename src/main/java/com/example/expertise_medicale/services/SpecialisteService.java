package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.SpecialisteDAO;
import com.example.expertise_medicale.models.Creneau;
import com.example.expertise_medicale.models.Specialiste;
import com.example.expertise_medicale.models.enums.Specialite;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialisteService {
    private SpecialisteDAO specialisteDAO = new SpecialisteDAO();

    public void saveOrUpdate(Specialiste specialiste) {
        if (specialiste.getId() == null) {
            specialisteDAO.add(specialiste);
        } else {
            specialisteDAO.update(specialiste);
        }
    }

    public Specialiste findById(Long id) {
        return specialisteDAO.find(String.valueOf(id));
    }

    public List<Specialiste> findAll() {
        return specialisteDAO.findAll();
    }

    public List<Specialiste> filterBySpecialiteEtTarif(Specialite specialite, Double maxTarif) {
        return findAll().stream()
                .filter(s -> s.getSpecialite() == specialite)
                .filter(s -> s.getTarif() != null && s.getTarif() <= maxTarif)
                .collect(Collectors.toList());
    }

    public List<Specialite> getAllSpecialites() {
        return findAll()
                .stream()
                .map(Specialiste::getSpecialite)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Specialiste> findBySpecialite(Specialite specialite) {
        return specialisteDAO.findAll()
                .stream()
                .filter(s -> s.getSpecialite().equals(specialite))
                .collect(Collectors.toList());
    }


}
