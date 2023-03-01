package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Persona {
    static PersonaDAO dao;

    private final Integer id;
    private Collection<Cuenta> cuentas;
    private Collection<EstadoContable> estadosContables;
    private String nombre;
    private String apellido;
    
    public Persona() {}
   
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
    
    
    
    public void abrirCuenta(Cuenta cuenta){
    	int max = 0;
		for (Cuenta cuenta: this.getCuentasView()) {
			if(max < cuenta.getId()) {
				max = cuenta.getId();
			}
		}
		cuenta.setId(max);
		
		
		cuentas.add(cuenta);
    }
    
    public void cerrarCuenta(int id){
            Cuenta cuenta = this.getById(id);
            if(Cuenta != null) {
            	cuentas.remove(cuenta);
            }
    }
    
    public Cuenta getById(int id) {
		List<Cuenta> cuentas = this.getCuentasView();
		for (Cuenta cuenta : cuentas) {
			if(cuenta.getId() == id ) {
				return cuenta;
			}
		}
		return null;
	}
}
