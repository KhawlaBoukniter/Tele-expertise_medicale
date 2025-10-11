package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserDAO<T> {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private final Class<T> entityClass;

    public UserDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void add(T t){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public void update(T t){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    public T find(String id){
        EntityManager em = emf.createEntityManager();
        return em.find(entityClass, id);
    }

    public void delete(T t){
        EntityManager em = emf.createEntityManager();
        em.remove(t);
    }

    public List<T> findAll(){
        EntityManager em = emf.createEntityManager();

        List<T> list = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        return list;
    }

}
