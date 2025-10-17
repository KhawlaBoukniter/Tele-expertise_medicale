package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.DemandeExpertise;
import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.Specialiste;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DemandeExpertiseDAO {

        public void add(DemandeExpertise demandeExpertise){
            EntityManager em = Jpa.getEntityManager();

            em.getTransaction().begin();
            em.persist(demandeExpertise);
            em.getTransaction().commit();
        }

        public void update(DemandeExpertise demandeExpertise){
            EntityManager em = Jpa.getEntityManager();
            em.getTransaction().begin();
            em.merge(demandeExpertise);
            em.getTransaction().commit();
        }

        public DemandeExpertise find(Long id){
            EntityManager em = Jpa.getEntityManager();
            return em.find(DemandeExpertise.class, id);
        }

        public void delete(DemandeExpertise demandeExpertise){
            EntityManager em = Jpa.getEntityManager();
            em.remove(demandeExpertise);
        }

        public List<DemandeExpertise> findAll(){
            EntityManager em = Jpa.getEntityManager();
            return em.createQuery("from DemandeExpertise ", DemandeExpertise.class).getResultList();

        }

}
