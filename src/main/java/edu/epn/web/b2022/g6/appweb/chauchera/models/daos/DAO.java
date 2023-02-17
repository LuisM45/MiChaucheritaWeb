/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.models.daos;

import java.util.Collection;

/**
 *
 * @author luism
 */
public interface DAO<E,K> {
    public E create(E object);
    public E get(K key);
    public Collection<E> getAll();
    public boolean update(E object);
    public boolean delete(K key);
}
