package test;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.TipoCuentaDAO;
import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author luism
 */
public class Main {
    public static void main(String[] args) {
        TipoCuentaDAO tc = DaoFactory.getDaoFactory().getTipoCuentaDAO();
        
        Persona p = new Persona(1,"Marcelo");
        
        Cuenta nomina = new Cuenta(1,"Nomina",tc.getByName("INGRESO"));
        Cuenta banco1 = new Cuenta(2,"Banco1",tc.getByName("INGRESO_EGRESO"));
        Cuenta banco2 = new Cuenta(3,"Banco2",tc.getByName("INGRESO_EGRESO"));
        Cuenta universidad = new Cuenta(4,"Universidad",tc.getByName("EGRESO"));
        
        
        
        p.abrirCuenta(nomina);
        p.abrirCuenta(banco1);
        p.abrirCuenta(banco2);
        p.abrirCuenta(universidad);
        
        for(var v: p.getCuentasView()) System.out.println(v);
        
        nomina.registrarIngreso(1000,banco1, LocalDate.parse("2020-01-01"),"Nomina enero1" );
        nomina.registrarIngreso(1000,banco1, LocalDate.parse("2020-01-02"),"Nomina enero2" );
        nomina.registrarIngreso(1000,banco1, LocalDate.parse("2020-01-03"),"Nomina enero3" );
        banco1.transferirDinero(1000, banco2, LocalDate.parse("2020-01-04"), "Sample movement");
        banco1.transferirDinero(500, banco2, LocalDate.parse("2020-01-05"), "Pagos diversos de matriculas-Movement");
        universidad.registrarEgreso(250, banco2, LocalDate.parse("2020-01-06"), "Pagos diversos de matriculas");
        p.cerrarCuenta(1);
        p.consultarCuenta(2).setCuenta(new Cuenta(null,"Mi vecino",tc.getByName("INGRESO")));
        for(var v: p.getCuentasView()) System.out.println(v);
        
        p.generarEstadoContable(LocalDate.parse("2020-01-02"), LocalDate.parse("2020-01-05"));
        p.generarEstadoContable(LocalDate.parse("2020-01-04"), LocalDate.parse("2020-01-08"));
        
        for(var v: p.getEstadosContablesView()) System.out.println(v);
        
    }
}
