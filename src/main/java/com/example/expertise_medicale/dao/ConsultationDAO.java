package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Consultation;
import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ConsultationDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public ConsultationDAO() { em = emf.createEntityManager(); }

        public void add(Consultation consultation){
            em.getTransaction().begin();
            em.persist(consultation);
            em.getTransaction().commit();
            em.close();
        }

        public void update(Consultation consultation){
            em.merge(consultation);
        }

        public Consultation find(Consultation consultation){
            return em.find(Consultation.class, consultation.getId());
        }

        public void delete(Consultation consultation){
            em.remove(consultation);
        }

        public List<Consultation> findAll(){
            return em.createQuery("from Consultation", Consultation.class).getResultList();
        }


}
