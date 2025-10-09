package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.DossierMedical;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DossierMedicalDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public DossierMedicalDAO() {
        em = emf.createEntityManager();
    }

        public void add(DossierMedical dossierMedical){
            em.getTransaction().begin();
            em.persist(dossierMedical);
            em.getTransaction().commit();
            em.close();
        }

        public void update(DossierMedical dossierMedical){
            em.getTransaction().begin();
            em.merge(dossierMedical);
            em.getTransaction().commit();
            em.close();
        }

        public DossierMedical find(DossierMedical dossierMedical){
            return em.find(DossierMedical.class, dossierMedical.getId());
        }

        public void delete(DossierMedical dossierMedical){
            em.remove(dossierMedical);
        }


}
