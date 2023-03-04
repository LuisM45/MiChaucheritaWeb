/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.GenericDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityTransaction;

/**
 *
 * @author luism
 */
public class JPAGeneric<E,K> implements GenericDAO<E, K>{
    protected EntityManager eManager;
    protected Class<E> asociatedClass;

    public JPAGeneric(Class asociatedClass) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("msql_chaucherita");
        eManager = emf.createEntityManager();
        this.asociatedClass = asociatedClass;
    }
    
    
    
    @Override
    public E create(E object) {
        EntityTransaction transaction = eManager.getTransaction();
        transaction.begin();
        eManager.persist(object);
        transaction.commit();
        return object;
    }

    @Override
    public E get(K key) {
        return eManager.find(asociatedClass, key);
    }

    @Override
    public Collection<E> getAll() {
        return eManager
                .createQuery("SELECT o FROM "+asociatedClass.getName()+" o")
                .getResultList();
    }

    @Override
    public boolean update(E object) {
        EntityTransaction transaction = eManager.getTransaction();

        try {
            transaction.begin();
            eManager.merge(object);
            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(K key) {
        EntityTransaction transaction = eManager.getTransaction();

        try {
            
            transaction.begin();
            eManager.remove(get(key));
            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }
    
}
