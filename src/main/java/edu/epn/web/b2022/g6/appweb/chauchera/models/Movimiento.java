package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.MovimientoDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JPAInstantAttributeConverter;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movement")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "concept")
    private String concepto;
    
    @Column(name = "datetime")
    @Convert(converter = edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JPAInstantAttributeConverter.class)
    private Instant fecha;
    
    @Column(name = "ammount")
    private double valor;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id", nullable = true)
    private Cuenta cuentaGeneradora;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "recipient_id", nullable = true)
    private Cuenta cuentaReceptora;

    public Movimiento() {
    }
    
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCuentaGeneradora(Cuenta cuentaGeneradora) {
        this.cuentaGeneradora = cuentaGeneradora;
    }

    public void setCuentaReceptora(Cuenta cuentaReceptora) {
        this.cuentaReceptora = cuentaReceptora;
    }
    
    
    public Persona getDuenio(){
        Persona duenio;
        if(cuentaGeneradora!=null) duenio=cuentaGeneradora.getDuenio();
        else duenio=cuentaReceptora.getDuenio();
        return duenio;
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
