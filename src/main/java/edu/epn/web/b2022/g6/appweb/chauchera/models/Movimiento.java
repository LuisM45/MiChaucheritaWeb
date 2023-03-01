package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.MovimientoDAO;
import java.time.Instant;

public class Movimiento {
    static MovimientoDAO dao;

    private final Integer id;
    private String concepto;
    private Instant fecha;
    private double valor;
    
    private Cuenta cuentaGeneradora;
    private Cuenta cuentaReceptora;

    public Movimiento(Integer id, String concepto, Cuenta cuentaOrigen, Cuenta cuentaDestino, Instant fecha, double valor) {
        this.id = id;
        this.cuentaGeneradora = cuentaOrigen;
        this.cuentaReceptora = cuentaDestino;
        this.concepto = concepto;
        this.fecha = fecha;
        this.valor = valor;
    }

    public Movimiento(Cuenta cuentaOrigen,String concepto, Cuenta cuentaDestino, Instant fecha, double valor) {
        this(null,concepto,cuentaOrigen,cuentaDestino,fecha,valor);
    }

    public Integer getId() {
        return id;
    }

    public Cuenta getCuentaGeneradora() {
        return cuentaGeneradora;
    }

    public Cuenta getCuentaReceptora() {
        return cuentaReceptora;
    }

    public Instant getFecha() {
        return fecha;
    }

    public double getValor() {
        return valor;
    }

    public String getConcepto() {
        return concepto;
    }
    
    
}
