package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;

public class Persona {
    static PersonaDAO dao;

    private final Integer id;
    private String nombre;
    private String apellido;

    public Persona(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(String nombre, String apellido) {
        this(null,nombre,apellido);
    }
    
    
}
