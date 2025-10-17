package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ConsultationDAO {

        public void add(Consultation consultation){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(consultation);
                em.getTransaction().commit();
            } finally {
                em.close();
            }

        }

        public void update(Consultation consultation){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(consultation);
                em.getTransaction().commit();
            } finally {
                em.close();
            }

        }

        public Consultation find(Long id){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.find(Consultation.class,  id);
            }  finally {
                em.close();
            }

        }

        public void delete(Consultation consultation){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                Consultation sv = em.find(Consultation.class, consultation.getId());
                if (sv != null) em.remove(sv);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        public List<Consultation> findAll(){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.createQuery("from Consultation", Consultation.class).getResultList();
            } finally {
                em.close();
            }

        }


}
