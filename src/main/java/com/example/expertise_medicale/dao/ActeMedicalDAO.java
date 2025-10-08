package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.ActeMedical;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;

public class ActeMedicalDAO {
        private EntityManager em;

        public void add(ActeMedical acteMedical){
            em.persist(acteMedical);
        }

        public void update(ActeMedical acteMedical){
            em.merge(acteMedical);
        }

        public ActeMedical find(ActeMedical acteMedical){
            return em.find(ActeMedical.class, acteMedical.getId());
        }

        public void delete(ActeMedical acteMedical){
            em.remove(acteMedical);
        }


}
