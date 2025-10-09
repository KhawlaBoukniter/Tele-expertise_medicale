package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.DemandeExpertise;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DemandeExpertiseDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public DemandeExpertiseDAO() {
        em = emf.createEntityManager();
    }

        public void add(DemandeExpertise demandeExpertise){
            em.getTransaction().begin();
            em.persist(demandeExpertise);
            em.getTransaction().commit();
            em.close();
        }

        public void update(DemandeExpertise demandeExpertise){
            em.getTransaction().begin();
            em.merge(demandeExpertise);
            em.getTransaction().commit();
            em.close();
        }

        public DemandeExpertise find(DemandeExpertise demandeExpertise){
            return em.find(DemandeExpertise.class, demandeExpertise.getId());
        }

        public void delete(DemandeExpertise demandeExpertise){
            em.remove(demandeExpertise);
        }


}
