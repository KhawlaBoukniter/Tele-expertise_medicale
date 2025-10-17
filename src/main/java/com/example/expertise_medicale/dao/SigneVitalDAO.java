package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.SigneVital;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SigneVitalDAO {
    public void add(SigneVital signeVital) {
        EntityManager em = Jpa.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(signeVital);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

        public void update(SigneVital signeVital){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(signeVital);
                em.getTransaction().commit();
            } finally {
                em.close();
            }

        }

        public SigneVital find(SigneVital signeVital){
            EntityManager em = Jpa.getEntityManager();
            try {
                return em.find(SigneVital.class, signeVital.getId());
            } finally {
                em.close();
            }
        }

    public List<SigneVital> findAll() {
        EntityManager em = Jpa.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM SigneVital s", SigneVital.class).getResultList();
        } finally {
            em.close();
        }
    }

        public void delete(SigneVital signeVital){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                SigneVital sv = em.find(SigneVital.class, signeVital.getId());
                if (sv != null) em.remove(sv);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }


}
