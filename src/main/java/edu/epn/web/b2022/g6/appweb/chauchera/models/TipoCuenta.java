package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.TipoCuentaDAO;

public class TipoCuenta {
    static TipoCuentaDAO dao;

    private final Integer id;
    private String nombre;

    public TipoCuenta(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoCuenta(String nombre) {
        this(null,nombre);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
