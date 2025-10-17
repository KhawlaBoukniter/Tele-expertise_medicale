package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.Creneau;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CreneauDAO {

        public void add(Creneau creneau){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(creneau);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        public void update(Creneau creneau){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(creneau);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
      }

        public Creneau find(Creneau creneau){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.find(Creneau.class, creneau.getId());
            } finally {
                em.close();
            }
        }

        public void delete(Creneau creneau){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                Creneau sv = em.find(Creneau.class, creneau.getId());
                if (sv != null) em.remove(sv);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        public List<Creneau> findAll(){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.createQuery("from Creneau ", Creneau.class).getResultList();
            } finally {
                em.close();
            }
        }
}
