package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.MovimientoDAO;
import java.time.Instant;

public class Movimiento {
    static MovimientoDAO dao;

    private final Integer id;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Instant fecha;
    private double valor;

    public Movimiento(Integer id, Cuenta cuentaOrigen, Cuenta cuentaDestino, Instant fecha, double valor) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.fecha = fecha;
        this.valor = valor;
    }

    public Movimiento(Cuenta cuentaOrigen, Cuenta cuentaDestino, Instant fecha, double valor) {
        this(null,cuentaOrigen,cuentaDestino,fecha,valor);
    }
    
    
}
