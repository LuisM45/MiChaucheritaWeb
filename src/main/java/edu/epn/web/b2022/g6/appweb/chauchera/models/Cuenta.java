package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.CuentaDAO;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

public class Cuenta {
    static CuentaDAO dao;

    private final Integer id;
    private String nombre;
    private double valorTotal;
    private Collection<Movimiento> movimientosRealizados;
    private TipoCuenta tipoCuenta;

    public Cuenta(Integer id, String nombre, double valorTotal, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.valorTotal = valorTotal;
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta(String nombre, double valorTotal, TipoCuenta tipoCuenta) {
        this(null,nombre, valorTotal, tipoCuenta);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public Collection getMovimientosRealizadosView() {
        return Collections.unmodifiableCollection(movimientosRealizados);
    }
    
    public void registrarIngreso(double valor){
        throw new RuntimeException("Not implemented method");
    }
    
    public void registrarEgreso(double valor){
        throw new RuntimeException("Not implemented method");
    }
    
    /**
     * Filtra los movimientos por fecha y cuenta y los retorna en una Collection
     * @param fechaInicio
     * @param fechaFin
     * @return Movimientos dentro de un margen de tiempo de la cuenta
     */
    public Collection<Movimiento> obtenerMovimientoPorFechas(Instant fechaInicio, Instant fechaFin){
        throw new RuntimeException("Not implemented method");
    }
}
