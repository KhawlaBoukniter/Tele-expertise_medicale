package com.example.expertise_medicale.services;

import com.example.expertise_medicale.dao.CreneauDAO;
import com.example.expertise_medicale.models.Creneau;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class CreneauService {
    private CreneauDAO creneauDAO = new CreneauDAO();

    public void add(Creneau creneau) {
        creneauDAO.add(creneau);
    }

    public void update(Creneau creneau) {
        creneauDAO.update(creneau);
    }

    public List<Creneau> getAllCreneau(){
        return creneauDAO.findAll();
    }

    public List<Creneau> findBySpecialiste(Long specialisteId){
        return getAllCreneau().stream().filter(c -> c.getSpecialiste().getId().equals(specialisteId)).toList();
    }

    public List<Creneau> getCreneauxDisponibles(Long specialisteId) {
        return findBySpecialiste(specialisteId)
                .stream()
                .filter(Creneau::getDisponible)
                .collect(toList());
    }

    public  Creneau getCreneauById(Long id){
        return getAllCreneau().stream().filter(c -> c.getId().equals(id)).findFirst().get();
    }
}
