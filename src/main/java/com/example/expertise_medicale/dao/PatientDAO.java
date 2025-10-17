package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.xml.bind.JAXBPermission;

import java.util.List;

public class PatientDAO {

    public void add(Patient p) {
        EntityManager em = Jpa.getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }


        public void update(Patient patient){
            EntityManager em = Jpa.getEntityManager();
            em.getTransaction().begin();
            em.merge(patient);
            em.getTransaction().commit();
        }

        public Patient find(Patient patient){
            EntityManager em = Jpa.getEntityManager();
            return em.find(Patient.class, patient.getId());
        }

        public void delete(Patient patient){
            EntityManager em = Jpa.getEntityManager();
            em.remove(patient);
        }

        public List<Patient> findAll(){
            EntityManager em = Jpa.getEntityManager();
            List<Patient> list = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
            return list;
        }
}
