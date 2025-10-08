package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;

public class ConsultationDAO {
        private EntityManager em;

        public void add(Consultation consultation){
            em.persist(consultation);
        }

        public void update(Consultation consultation){
            em.merge(consultation);
        }

        public Consultation find(Consultation consultation){
            return em.find(Consultation.class, consultation.getId());
        }

        public void delete(Consultation consultation){
            em.remove(consultation);
        }


}
