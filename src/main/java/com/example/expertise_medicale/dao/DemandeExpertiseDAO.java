package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.DemandeExpertise;
import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.Specialiste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

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
        }

        public void update(DemandeExpertise demandeExpertise){
            em.getTransaction().begin();
            em.merge(demandeExpertise);
            em.getTransaction().commit();
        }

        public DemandeExpertise find(Long id){
            return em.find(DemandeExpertise.class, id);
        }

        public void delete(DemandeExpertise demandeExpertise){
            em.remove(demandeExpertise);
        }

        public List<DemandeExpertise> findAll(){
            return em.createQuery("from DemandeExpertise ", DemandeExpertise.class).getResultList();

        }

}
