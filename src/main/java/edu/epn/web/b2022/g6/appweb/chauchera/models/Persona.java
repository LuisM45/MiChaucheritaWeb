package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

//@GestionarCuentas
public class Persona {
    static PersonaDAO dao;

    private Integer estadoIdx =0;
    private final Integer id;
    private Collection<Cuenta> cuentas;
    private Collection<EstadoContable> estadosContables;
    private String nombre;
    private String apellido;
   
    public Persona(Integer id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        
        this.cuentas = new ArrayList<>();
        this.estadosContables = new ArrayList<>();
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

    //@ListarCuentas
    public Collection<Cuenta> getCuentasView() {
        return Collections.unmodifiableCollection(cuentas);
    }
    
    public Collection<EstadoContable> getEstadosContablesView() {
        return Collections.unmodifiableCollection(estadosContables);
    }
    
    //@CrearCuenta
    public void abrirCuenta(Cuenta cuenta){
    	cuentas.add(cuenta);
    }
    
    //@EliminarCuenta
    public void cerrarCuenta(int id){
            cuentas.removeIf((t)->t.getId()==id);
    }
    
    //@ActualizarCuenta
    public void actualizarCuenta(Cuenta c){
        cuentas.stream()
                .filter(t->Objects.equals(t.getId(), c.getId()))
                .findFirst().orElse(null)
                .setCuenta(c);
    }
    
    //@GetCuenta. NO ES USADO
    public Cuenta consultarCuenta(int id) {
        return cuentas.stream()
                .filter(t->t.getId()==id)
                .findFirst().orElse(null);
    }
    
    public EstadoContable generarEstadoContable(Instant fechaInicio, Instant fechaFin){
        EstadoContable estadoContable = new EstadoContable(estadoIdx++,this,fechaInicio, fechaFin);
        estadosContables.add(estadoContable);
        return estadoContable;
    }
    
    public void eliminarEstadoContable(int id){
        estadosContables.removeIf(t->t.getId()==id);
    }
    
    public EstadoContable consultarEstadoContable(int id){
        return estadosContables.stream()
                .filter(t->t.getId()==id)
                .findFirst().orElse(null);
    }
    
    public EstadoContable generar(int id){
        return estadosContables.stream()
                .filter(t->t.getId()==id)
                .findFirst().orElse(null);
    }
}
