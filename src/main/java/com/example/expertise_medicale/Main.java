package com.example.expertise_medicale;

import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("✅ Connexion Hibernate établie avec succès !");
        em.close();
        emf.close();
    }
}
