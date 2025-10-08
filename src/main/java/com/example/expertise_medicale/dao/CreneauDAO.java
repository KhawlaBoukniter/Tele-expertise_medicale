package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Creneau;
import jakarta.persistence.EntityManager;

public class CreneauDAO {
        private EntityManager em;

        public void add(Creneau creneau){
            em.persist(creneau);
        }

        public void update(Creneau creneau){
            em.merge(creneau);
        }

        public Creneau find(Creneau creneau){
            return em.find(Creneau.class, creneau.getId());
        }

        public void delete(Creneau creneau){
            em.remove(creneau);
        }


}
