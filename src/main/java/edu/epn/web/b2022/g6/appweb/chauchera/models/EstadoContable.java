package edu.epn.web.b2022.g6.appweb.chauchera.models;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.time.Instant;
import java.util.Collection;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.EstadoContableDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JPAInstantAttributeConverter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="statement")
public class EstadoContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "start_datetime")
    private LocalDate fechaInicio;
    
    @Column(name = "end_datetime")
    private LocalDate fechaFin;
    
    @Transient
    private double ingresosTotales;
    
    @Transient
    private Map<Cuenta,Double> ingresosPorCuenta;
    
    @Transient
    private Map<Cuenta,Double> egresosPorCuenta;
    
    @Transient
    private double egresosTotales;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Persona personaDuenia;
    
    @Transient
    private Map<Cuenta,Collection<Movimiento>> movimientosRegistradosPorCuenta;

    public EstadoContable() {
        movimientosRegistradosPorCuenta = new HashMap<>();
    }
    
    public EstadoContable(Integer id, Persona personaDuenia, LocalDate fechaInicio, LocalDate fechaFin, Map<Cuenta,Collection<Movimiento>> movimientosRegistradosPorCuenta, double ingresos, double egresos) {
        this.id = id;
        this.personaDuenia = personaDuenia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.movimientosRegistradosPorCuenta = movimientosRegistradosPorCuenta;
        this.ingresosTotales = ingresos;
        this.egresosTotales = egresos;
    }



    public EstadoContable(Persona personaDuenia, LocalDate fechaInicio, LocalDate fechaFin, Map<Cuenta,Collection<Movimiento>> movimientosRegistradosPorCuenta, double ingresos, double egresos) {
        this(null,personaDuenia,fechaInicio,fechaFin,movimientosRegistradosPorCuenta,ingresos,egresos);
    }
    
    public EstadoContable(Persona personaDuenia, LocalDate fechaInicio, LocalDate fechaFin) {
        this(null,personaDuenia,fechaInicio,fechaFin,null,0,0);
        setEstadoContable();
    }
    
    public EstadoContable(int id, Persona personaDuenia, LocalDate fechaInicio, LocalDate fechaFin) {
        this(id,personaDuenia,fechaInicio,fechaFin,null,0,0);
        setEstadoContable();
    }
    
    
    public void setEstadoContable(){
        if(personaDuenia==null||
                fechaFin==null||
                fechaInicio==null) return;
        
        movimientosRegistradosPorCuenta = new HashMap<>();
        for(Cuenta c: this.personaDuenia.getCuentasView()){
            Collection<Movimiento> movimientos = Collections
                    .unmodifiableCollection(c.obtenerMovimientoPorFechas(fechaInicio, fechaFin));
            movimientosRegistradosPorCuenta.put(c,movimientos);
        }
        setEgresosPorCuenta();
        setIngresosPorCuenta();
        setEgresosTotales();
        setIngresosTotales();
    
    }

    private void setIngresosPorCuenta(){
        ingresosPorCuenta = new HashMap<>();
        for(var v: movimientosRegistradosPorCuenta.entrySet()){
            Cuenta cuenta = v.getKey();
            Collection<Movimiento> movimientos = v.getValue();
            ingresosPorCuenta.put(v.getKey(),
                movimientos.stream()
                .filter(t->t.getCuentaReceptora()==cuenta)
                .mapToDouble(t->t.getValor())
                .sum());
        }
    }
    
    
    
    private void setEgresosPorCuenta(){
        egresosPorCuenta = new HashMap<>();
        for(var v: movimientosRegistradosPorCuenta.entrySet()){
            Cuenta cuenta = v.getKey();
            Collection<Movimiento> movimientos = v.getValue();
            egresosPorCuenta.put(v.getKey(),
                movimientos.stream()
                .filter(t->t.getCuentaGeneradora()==cuenta)
                .mapToDouble(t->t.getValor())
                .sum());
        }
    }
    
    private void setIngresosTotales(){
        TipoCuenta ingresoType = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("INGRESO");
        ingresosTotales = egresosPorCuenta.entrySet().stream()
                .filter(t->t.getKey().getTipoCuenta().equals(ingresoType))
                .mapToDouble(t->t.getValue())
                .sum();
    }
    
    
    
    private void setEgresosTotales(){
        TipoCuenta egresoType = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("EGRESO");
        egresosTotales = ingresosPorCuenta.entrySet().stream()
                .filter(t->t.getKey().getTipoCuenta().equals(egresoType))
                .mapToDouble(t->t.getValue())
                .sum();
    }
    
    
    public Integer getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Map<Cuenta,Collection<Movimiento>> getmovimientosRegistradosPorCuentaView() {
        return Collections.unmodifiableMap(movimientosRegistradosPorCuenta);
    }
    
    public Map<Cuenta,Double> getIngresosPorCuentaView() {
        return Collections.unmodifiableMap(ingresosPorCuenta);
    }
    
    public Map<Cuenta,Double> getEgresosPorCuentaView() {
        return Collections.unmodifiableMap(egresosPorCuenta);
    }

    public double getIngresosTotales() {
        return ingresosTotales;
    }

    public double getEgresosTotales() {
        return egresosTotales;
    }

    public Persona getPersonaDuenia() {
        return personaDuenia;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        setEstadoContable();
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        setEstadoContable();
    }

    public void setPersonaDuenia(Persona personaDuenia) {
        this.personaDuenia = personaDuenia;
        setEstadoContable();
    }

    @Override
    public String toString() {
        return "EstadoContable{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", ingresos=" + ingresosTotales + ", egresos=" + egresosTotales + ", personaDuenia=" + personaDuenia.getNombre() + '}';
    }
    
    
}
