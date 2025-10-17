package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.utils.Jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserDAO<T> {
    private final Class<T> entityClass;

    public UserDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void add(T t){
        EntityManager em = Jpa.getEntityManager();

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public void update(T t){
        EntityManager em = Jpa.getEntityManager();

        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    public T find(String id){
        EntityManager em = Jpa.getEntityManager();
        return em.find(entityClass, id);
    }

    public void delete(T t){
        EntityManager em = Jpa.getEntityManager();
        em.getTransaction().begin();
        t = em.merge(t);
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    public List<T> findAll(){
        EntityManager em = Jpa.getEntityManager();

        List<T> list = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        return list;
    }

}
