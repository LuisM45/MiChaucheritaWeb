package edu.epn.web.b2022.g6.appweb.chauchera.models;

import java.time.Instant;
import java.util.Collection;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.EstadoContableDAO;
import java.util.Collections;

public class EstadoContable {
    static EstadoContableDAO dao;

    private final Integer id;
    private Instant fechaInicio;
    private Instant fechaFin;
    private Collection<Movimiento> movimientoPorCuenta;
    private double ingresos;
    private double egresos;

    public EstadoContable(Integer id, Instant fechaInicio, Instant fechaFin, Collection<Movimiento> movimientoPorCuenta, double ingresos, double egresos) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.movimientoPorCuenta = movimientoPorCuenta;
        this.ingresos = ingresos;
        this.egresos = egresos;
    }

    public EstadoContable(Instant fechaInicio, Instant fechaFin, Collection<Movimiento> movimientoPorCuenta, double ingresos, double egresos) {
        this(null,fechaInicio,fechaFin,movimientoPorCuenta,ingresos,egresos);
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

    public Collection<Movimiento> getMovimientoPorCuentaView() {
        return Collections.unmodifiableCollection(movimientoPorCuenta);
    }

    public double getIngresos() {
        return ingresos;
    }

    public double getEgresos() {
        return egresos;
    }

    
    
}
