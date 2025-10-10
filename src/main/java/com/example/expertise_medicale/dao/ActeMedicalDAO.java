package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.ActeMedical;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ActeMedicalDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public ActeMedicalDAO() {
        em = emf.createEntityManager();
    }

        public void add(ActeMedical acteMedical){
            em.getTransaction().begin();
            em.persist(acteMedical);
            em.getTransaction().commit();
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

        public List<ActeMedical> findAll(){
            return em.createQuery("select a from ActeMedical a").getResultStream().toList();
        }

}
