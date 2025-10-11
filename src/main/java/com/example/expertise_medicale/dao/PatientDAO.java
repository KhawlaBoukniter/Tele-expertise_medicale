package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PatientDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;

    public PatientDAO() {
        em = emf.createEntityManager();
    }

    public void add(Patient p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }


        public void update(Patient patient){
            em.getTransaction().begin();
            em.merge(patient);
            em.getTransaction().commit();
        }

        public Patient find(Patient patient){
            return em.find(Patient.class, patient.getId());
        }

        public void delete(Patient patient){
            em.remove(patient);
        }

        public List<Patient> findAll(){
            em = emf.createEntityManager();
            List<Patient> list = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
            return list;
        }
}
