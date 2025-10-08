package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.DossierMedical;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;

public class DossierMedicalDAO {
        private EntityManager em;

        public void add(DossierMedical dossierMedical){
            em.persist(dossierMedical);
        }

        public void update(DossierMedical dossierMedical){
            em.merge(dossierMedical);
        }

        public DossierMedical find(DossierMedical dossierMedical){
            return em.find(DossierMedical.class, dossierMedical.getId());
        }

        public void delete(DossierMedical dossierMedical){
            em.remove(dossierMedical);
        }


}
