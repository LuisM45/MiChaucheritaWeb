package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.CuentaDAO;

public class Cuenta {
    static CuentaDAO dao;

    private final Integer id;
    private String nombre;
    private double valorTotal;
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
    
    
}
