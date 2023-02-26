package edu.epn.web.b2022.g6.appweb.chauchera.models;

import java.time.Instant;
import java.util.Collection;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.EstadoContableDAO;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class EstadoContable {
    static EstadoContableDAO dao;

    private final Integer id;
    private Persona personaDuenia;
    private Instant fechaInicio;
    private Instant fechaFin;
    private Map<Cuenta,Collection<Movimiento>> movimientoPorCuenta;
    private double ingresos;
    private double egresos;

    public EstadoContable(Integer id, Persona personaDuenia, Instant fechaInicio, Instant fechaFin, Map<Cuenta,Collection<Movimiento>> movimientoPorCuenta, double ingresos, double egresos) {
        this.id = id;
        this.personaDuenia = personaDuenia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.movimientoPorCuenta = movimientoPorCuenta;
        this.ingresos = ingresos;
        this.egresos = egresos;
    }



    public EstadoContable(Persona personaDuenia, Instant fechaInicio, Instant fechaFin, Map<Cuenta,Collection<Movimiento>> movimientoPorCuenta, double ingresos, double egresos) {
        this(null,personaDuenia,fechaInicio,fechaFin,movimientoPorCuenta,ingresos,egresos);
    }
    
    public EstadoContable(Persona personaDuenia, Instant fechaInicio, Instant fechaFin) {
        this(null,personaDuenia,fechaInicio,fechaFin,null,0,0);
        generarEstadoContable();
    }

    private void generarEstadoContable(){
        movimientoPorCuenta = new HashMap<>();
        
        for(Cuenta c: this.personaDuenia.getCuentasView()){
            Collection<Movimiento> movimientos = Collections
                    .unmodifiableCollection(c.obtenerMovimientoPorFechas(fechaInicio, fechaFin));
            movimientoPorCuenta.put(c,movimientos);
        }
        setIngresos();
        setEgresos();
    }
    
    private void setIngresos(){
        ingresos = movimientoPorCuenta
                .values()
                .stream()
                .flatMap(t->t.stream())
                .filter(t->t.getCuentaOrigen()==null)
                .mapToDouble(t->t.getValor())
                .sum();
    }
    
        private void setEgresos(){
        ingresos = movimientoPorCuenta
                .values()
                .stream()
                .flatMap(t->t.stream())
                .filter(t->t.getCuentaDestino()==null)
                .mapToDouble(t->t.getValor())
                .sum();
    }
    
    
    public Integer getId() {
        return id;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public Instant getFechaFin() {
        return fechaFin;
    }

    public Map<Cuenta,Collection<Movimiento>> getMovimientoPorCuentaView() {
        return Collections.unmodifiableMap(movimientoPorCuenta);
    }

    public double getIngresos() {
        return ingresos;
    }

    public double getEgresos() {
        return egresos;
    }

    public Persona getPersonaDuenia() {
        return personaDuenia;
    }

    
    
    
}
