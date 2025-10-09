package com.example.expertise_medicale.dao;

import com.example.expertise_medicale.models.Generaliste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserDAO<T> {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("expertiseMedicale");
    private EntityManager em;
    private final Class<T> entityClass;

    public UserDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        em = emf.createEntityManager();
    }

    public void add(T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public void update(T t){
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    public T find(String id){
        return em.find(entityClass, id);
    }

    public void delete(T t){
        em.remove(t);
    }

    public List<T> findAll(){
        List<T> list = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        return list;
    }

}
