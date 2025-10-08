package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.SigneVital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SigneVitalDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public SigneVitalDAO() {
        em = emf.createEntityManager();
    }


    public void add(SigneVital signeVital) {
        em.getTransaction().begin();
        em.persist(signeVital);
        em.getTransaction().commit();
    }

        public void update(SigneVital signeVital){
            em.getTransaction().begin();
            em.merge(signeVital);
            em.getTransaction().commit();
        }

        public SigneVital find(SigneVital signeVital){
            return em.find(SigneVital.class, signeVital.getId());
        }

        public void delete(SigneVital signeVital){
            em.remove(signeVital);
        }


}
