package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.CuentaDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cuenta {
    static CuentaDAO dao;

    private final Integer id;
    private String nombre;
    private double valorTotal;
    private TipoCuenta tipoCuenta;
    private Collection<Movimiento> movimientosGenerados;
    private Collection<Movimiento> movimientosRecibidos;

    public Cuenta(Integer id, String nombre, double valorTotal, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.valorTotal = valorTotal;
        this.tipoCuenta = tipoCuenta;
    }

    public Cuenta(String nombre, double valorTotal, TipoCuenta tipoCuenta) {
        this(null,nombre, valorTotal, tipoCuenta);
    }
    
    public void setCuenta(Cuenta c){
        this.nombre = c.nombre;
        this.tipoCuenta = c.tipoCuenta;
        this.valorTotal = c.valorTotal;
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

    public Collection getMovimientosGeneradosView() {
        return Collections.unmodifiableCollection(movimientosGenerados);
    }
    
    public Collection getMovimientosRecibidosView() {
        return Collections.unmodifiableCollection(movimientosRecibidos);
    }
    
    /**
     * @Registrar ingreso
     * Esta funcion debe ser solo realizable por cuentas tipo INGRESO.
     * Podría lanzarse una RuntimeException.
     * El valorTotal debe cambiar
     * La acción se registra en movimientosRealizados.
     * El movimiento realizado debe tener a null como su cuenta de origen
     * @param valor 
     */
    public void registrarIngreso(double valor,  Instant fecha, String concepto){
        transferirDineroGenerico(valor, null, this, fecha, concepto);
    }
    
    /**
     * @RegistrarEgreso
     * Esta funcion debe ser solo realizable por cuentas tipo EGRESO.
     * Podría lanzarse una RuntimeException.
     * La acción se registra en movimientosRealizados.
     * El valorTotal debe cambiar
     * El movimiento realizado debe tener a null como su cuenta de destino
     * @param valor 
     */
    public void registrarEgreso(double valor,  Instant fecha, String concepto){
        transferirDineroGenerico(valor, this, null, fecha, concepto);
    }
    
    /**
     * @TransferirDinero
     * La funcion debe ser solo realizable por cuentas tipo INGRESO e INGRESOEGRESO.
     * Esta funcion debe evitar valores negativos en la cuenta tipo INGRESO
     * Podría lanzarse una RuntimeException.
     * La acción se registra en movimientosRealizados.
     * El movimiento realizado debe tener a esta cuenta y a la cuenta de destino
     * @param valor
     * @param cuentaDesinto 
     */
    public void transferirDinero(double valor, Cuenta cuentaDesinto, Instant fecha, String concepto){
        transferirDineroGenerico(valor, this, cuentaDesinto, fecha, concepto);
    }
    
    public static void transferirDineroGenerico(double valor, Cuenta cuentaOrigen, Cuenta cuentaDestino, Instant fecha, String concepto){
        Movimiento mov = new Movimiento(cuentaDestino, concepto, cuentaDestino, fecha, valor);
        
        if (cuentaOrigen!=null){
            cuentaOrigen.valorTotal-=valor;
            cuentaOrigen.movimientosGenerados.add(mov);
        }
        
        if (cuentaDestino!=null){
            cuentaDestino.valorTotal += valor;
            cuentaDestino.movimientosRecibidos.add(mov);
        }
    }
    
    /**
     * Filtra los movimientos por fecha y cuenta y los retorna en una Collection
     * @param fechaInicio inclusiva
     * @param fechaFin inclusiva
     * @return Movimientos dentro de un margen de tiempo de la cuenta
     */
    public Collection<Movimiento> obtenerMovimientoPorFechas(Instant fechaInicio, Instant fechaFin){
        List<Movimiento> response = new ArrayList<>();
        
        response.addAll(movimientosGenerados.stream()
            .filter(m->fechaInicio.isBefore(m.getFecha()))
            .filter(m->fechaFin.isAfter(m.getFecha()))
            .toList());
        
        response.addAll(movimientosRecibidos.stream()
            .filter(m->fechaInicio.isBefore(m.getFecha()))
            .filter(m->fechaFin.isAfter(m.getFecha()))
            .toList());
        
        return response;
    }
}
