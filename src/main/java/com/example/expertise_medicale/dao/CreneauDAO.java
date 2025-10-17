package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.Creneau;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

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
        }

        public void update(Creneau creneau){
            em.getTransaction().begin();
            em.merge(creneau);
            em.getTransaction().commit();
      }

        public Creneau find(Creneau creneau){
            return em.find(Creneau.class, creneau.getId());
        }

        public void delete(Creneau creneau){
            em.remove(creneau);
        }

        public List<Creneau> findAll(){
            return em.createQuery("from Creneau ", Creneau.class).getResultList();
        }
}
