/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.models.daos;

import java.util.Collection;

/**
 * Esta es una interfaz genérica que debe obedecer las siguientes especificaciones
 * 
 * Los objetos siempre tienen que atravesar por el dao para asegurar la consistencia
 * de los datos.
 * 
 * Se recomienda al crear los objetos en memoria (Ocupar un new Clase()) no asignar
 * su ID. Y delegar al metodo create para que le asigne su ID
 * 
 * @param E es la clase del Objeto en la unidad de persistencia
 * @param K es la clase de la PK del objeto en la unidad de persistencia
 * 
 * @author luism
 */
public interface DAO<E,K> {
    
    /**
     * Este método guarda el objeto recibido en la unidad de persistencia
     * y retorna el Objeto con su ID respectiva;
     * @param object objeto a guardar
     * @return Objeto que se encuentra en la unidad de persistencia (Con ID)
     */
    public E create(E object);
    
    /**
     * Este método extrae un objeto de la unidad de persistencia a partir
     * de la key/ID proporcionada
     * @param key key/ID del objeto a ser recuperado
     * @return Objeto recuperado de la unidad de persistencia. Null si no existiese
     */
    public E get(K key);
    
    /**
     * Este método extrae todos los objetos de la unidad de persistencia
     * y retorna una colleccion de estos
     * @return Colleccion con los objetos en la unidad de persistencia
     */
    public Collection<E> getAll();
    
    /**
     * Recibe un objeto para realizar la actualización respectiva
     * Se recomienda que el objeto no hay sido producto de una instancia con new
     * sino resultado de un get o create.
     * 
     * @param object Objeto a actualizar
     * @return True si se realizó algún cambio. False de otra manera
     */
    public boolean update(E object);
    
    /**
     * Recibe la llave ID/key para realizar eliminar el objeto de la unidad de persistencia
     * Se recomienda que el objeto no hay sido producto de una instancia con new
     * sino resultado de un get o create.
     * 
     * @param key Objeto a eliminar
     * @return True si se realizó algún cambio. False de otra manera
     */
    public boolean delete(K key);
}
