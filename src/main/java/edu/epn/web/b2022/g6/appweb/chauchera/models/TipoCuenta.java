package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.TipoCuentaDAO;
import java.util.ArrayList;
import java.util.Collection;

public class TipoCuenta {
    static TipoCuentaDAO dao;

    private static final Collection<TipoCuenta> tiposCuenta;
    static{
        tiposCuenta = new ArrayList<>();
        tiposCuenta.add(new TipoCuenta(1,"INGRESO"));
        tiposCuenta.add(new TipoCuenta(2,"EGRESO"));
        tiposCuenta.add(new TipoCuenta(3,"INGRESO_EGRESO"));
    }
    
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
    
    public static TipoCuenta getTipoCuenta(String nombre){
        return tiposCuenta.stream().filter(t->t.nombre.equals(nombre)).findAny().orElse(null);
    }
}
