package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.DemandeExpertise;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;

public class DemandeExpertiseDAO {
        private EntityManager em;

        public void add(DemandeExpertise demandeExpertise){
            em.persist(demandeExpertise);
        }

        public void update(DemandeExpertise demandeExpertise){
            em.merge(demandeExpertise);
        }

        public DemandeExpertise find(DemandeExpertise demandeExpertise){
            return em.find(DemandeExpertise.class, demandeExpertise.getId());
        }

        public void delete(DemandeExpertise demandeExpertise){
            em.remove(demandeExpertise);
        }


}
