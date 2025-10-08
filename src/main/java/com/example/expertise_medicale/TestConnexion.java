package com.example.expertise_medicale;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConnexion {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
            EntityManager em = emf.createEntityManager();
            System.out.println("✅ Connexion Hibernate établie avec succès !");

            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("❌ Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
