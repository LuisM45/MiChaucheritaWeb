package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.CuentaDAO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="account")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")
    private String nombre;
    
    @Transient
    private double valorTotal;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private TipoCuenta tipoCuenta;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Persona duenio;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private Collection<Movimiento> movimientosGenerados;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private Collection<Movimiento> movimientosRecibidos;

    public Cuenta() {
        valorTotal = 0;
    }
    
    public Cuenta(Integer id, String nombre, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.valorTotal = 0;
        this.tipoCuenta = tipoCuenta;
        movimientosGenerados = new ArrayList<>();
        movimientosRecibidos = new ArrayList<>();
    }

    public Cuenta(String nombre, TipoCuenta tipoCuenta) {
        this(null,nombre, tipoCuenta);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorTotal() {
        if(movimientosGenerados==null||movimientosRecibidos==null) return;
        valorTotal = 0;
        valorTotal -= movimientosGenerados.stream()
                .mapToDouble(t->t.getValor())
                .sum();
        valorTotal += movimientosRecibidos.stream()
                .mapToDouble(t->t.getValor())
                .sum();
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setMovimientosGenerados(Collection<Movimiento> movimientosGenerados) {
        this.movimientosGenerados = movimientosGenerados;
        setValorTotal();
    }

    public void setMovimientosRecibidos(Collection<Movimiento> movimientosRecibidos) {
        this.movimientosRecibidos = movimientosRecibidos;
        setValorTotal();
    }
    
    
    
    public void setCuenta(Cuenta c){
        this.nombre = c.nombre;
        this.tipoCuenta = c.tipoCuenta;
        this.valorTotal = c.valorTotal;
    }

    public Persona getDuenio() {
        return duenio;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
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
    public void registrarIngreso(double valor, Cuenta cuentaDesinto,  Instant fecha, String concepto){
        if(!tipoCuenta.getNombre().equals("INGRESO"))
                throw new RuntimeException("This action be performed onyl by INGRESO accounts");
        if(cuentaDesinto.getTipoCuenta().getNombre().equals("INGRESO"))
                throw new RuntimeException("An INGRESO account cannot be the target for this action");
        transferirDineroGenerico(valor, this, cuentaDesinto, fecha, concepto);
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
    public void registrarEgreso(double valor, Cuenta cuentaOrigen,  Instant fecha, String concepto){
        if(!tipoCuenta.getNombre().equals("EGRESO"))
            throw new RuntimeException("This action be performed onyl by EGRESO accounts");
        if(cuentaOrigen.getTipoCuenta().getNombre().equals("EGRESO"))
            throw new RuntimeException("An EGRESO account cannot be the origin for this action");
        transferirDineroGenerico(valor, cuentaOrigen, this, fecha, concepto);
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
        if(!tipoCuenta.getNombre().equals("INGRESO_EGRESO"))
                throw new RuntimeException("This action is allowed only for INGRESOEGRESO accounts");
        if(!cuentaDesinto.getTipoCuenta().getNombre().equals("INGRESO_EGRESO"))
                throw new RuntimeException("Only INGRESOEGRESO account can be the target for this action");
        transferirDineroGenerico(valor, this, cuentaDesinto, fecha, concepto);
    }
    
    private static void transferirDineroGenerico(double valor, Cuenta cuentaOrigen, Cuenta cuentaDestino, Instant fecha, String concepto){
        Movimiento mov = new Movimiento(cuentaOrigen, concepto, cuentaDestino, fecha, valor);
        
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

    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
}
