package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@GestionarCuentas
@Entity
@Table(name="user")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Collection<Cuenta> cuentas;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Collection<EstadoContable> estadosContables;
    
    @Column(name = "name")
    private String nombre;

    public Persona() {
    }
   
    public Persona(String nombre) {
        this(null,nombre);
    }
    
    public Persona(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        
        this.cuentas = new ArrayList<>();
        this.estadosContables = new ArrayList<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCuentas(Collection<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void setEstadosContables(Collection<EstadoContable> estadosContables) {
        this.estadosContables = estadosContables;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    


    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
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
        cuenta.setDuenio(this);
    }
    
    //@EliminarCuenta
    public void cerrarCuenta(int id){
        Cuenta porBorrar = cuentas.stream().filter(t->t.getId()==id).findFirst().orElse(null);
        cuentas.remove(porBorrar);
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
    
    public EstadoContable generarEstadoContable(LocalDate fechaInicio, LocalDate fechaFin){
        EstadoContable estadoContable = new EstadoContable(this,fechaInicio, fechaFin);
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
    

}
