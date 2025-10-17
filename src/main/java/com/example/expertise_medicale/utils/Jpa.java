package com.example.expertise_medicale.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Jpa {
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
