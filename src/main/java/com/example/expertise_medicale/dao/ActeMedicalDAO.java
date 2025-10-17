package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.ActeMedical;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ActeMedicalDAO {

        public void add(ActeMedical acteMedical){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(acteMedical);
                em.getTransaction().commit();
            } finally {
                em.close();
            }

        }

        public void update(ActeMedical acteMedical){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(acteMedical);
                em.getTransaction().commit();
            }  finally {
                em.close();
            }
        }

        public ActeMedical find(ActeMedical acteMedical){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.find(ActeMedical.class, acteMedical.getId());
            }  finally {
                em.close();
            }
        }

        public void delete(ActeMedical acteMedical){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                ActeMedical sv = em.find(ActeMedical.class, acteMedical.getId());
                if (sv != null) em.remove(sv);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        public List<ActeMedical> findAll(){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.createQuery("select a from ActeMedical a").getResultStream().toList();
            } finally {
                em.close();
            }

        }

}
