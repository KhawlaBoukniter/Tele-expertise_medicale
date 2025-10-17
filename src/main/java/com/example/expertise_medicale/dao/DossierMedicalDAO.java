package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.DossierMedical;
import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DossierMedicalDAO {

        public void add(DossierMedical dossierMedical){
            EntityManager em = Jpa.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(dossierMedical);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }

        public void update(DossierMedical dossierMedical){
            EntityManager em = Jpa.getEntityManager();
            em.getTransaction().begin();
            em.merge(dossierMedical);
            em.getTransaction().commit();
        }

        public DossierMedical find(DossierMedical dossierMedical){
            EntityManager em = Jpa.getEntityManager();
            return em.find(DossierMedical.class, dossierMedical.getId());
        }

        public void delete(DossierMedical dossierMedical){
            EntityManager em = Jpa.getEntityManager();
            em.remove(dossierMedical);
        }


    public List<DossierMedical> findAll() {
            EntityManager em = Jpa.getEntityManager();
            return em.createQuery("from DossierMedical ", DossierMedical.class).getResultList();

    }
}
