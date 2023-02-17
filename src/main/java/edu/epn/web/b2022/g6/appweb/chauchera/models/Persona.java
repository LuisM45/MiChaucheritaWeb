package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Persona {
    static PersonaDAO dao;

    private final Integer id;
    private Collection<Cuenta> cuentas;
    private String nombre;
    private String apellido;

    public Persona(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        
        this.cuentas = new ArrayList<>();
    }

    public Persona(String nombre, String apellido) {
        this(null,nombre,apellido);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Collection<Cuenta> getCuentasView() {
        return Collections.unmodifiableCollection(cuentas);
    }
    
    
    
    public void abrirCuenta(String nombre, TipoCuenta tipoCuenta){
        throw new RuntimeException("Not implemented method");
    }
    
    public void cerrarCuenta(Cuenta cuenta){
            throw new RuntimeException("Not implemented method");
    }
    
    public void generarEstadi(Instant fehcaIncio, Instant fechaFin){
            throw new RuntimeException("Not implemented method");
    }
}
