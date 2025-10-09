package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Creneau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CreneauDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public CreneauDAO() {
        em = emf.createEntityManager();
    }

        public void add(Creneau creneau){
            em.getTransaction().begin();
            em.persist(creneau);
            em.getTransaction().commit();
            em.close();
        }

        public void update(Creneau creneau){
            em.getTransaction().begin();
            em.merge(creneau);
            em.getTransaction().commit();
            em.close();
        }

        public Creneau find(Creneau creneau){
            return em.find(Creneau.class, creneau.getId());
        }

        public void delete(Creneau creneau){
            em.remove(creneau);
        }


}
