package test;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.TipoCuentaDAO;
import java.time.Instant;

/**
 *
 * @author luism
 */
public class Main {
    public static void main(String[] args) {
        TipoCuentaDAO tc = DaoFactory.getDaoFactory().getTipoCuentaDAO();
        
        Persona p = new Persona(1,"Marcelo");
        p.abrirCuenta(new Cuenta(1,"Nomina",0,tc.getByName("INGRESO")));
        p.abrirCuenta(new Cuenta(2,"Banco",0,tc.getByName("INGRESO_EGRESO")));
        p.abrirCuenta(new Cuenta(3,"Universidad",0,tc.getByName("EGRESO")));
        
        for(var v: p.getCuentasView()) System.out.println(v);
        
        p.consultarCuenta(1).registrarIngreso(1000, Instant.parse("2020-01-01T20:00:00Z"),"Nomina enero" );
        p.consultarCuenta(1).registrarIngreso(1000, Instant.parse("2020-01-02T20:00:00Z"),"Nomina enero" );
        p.consultarCuenta(1).registrarIngreso(1000, Instant.parse("2020-01-03T20:00:00Z"),"Nomina enero" );
        p.consultarCuenta(1).transferirDinero(1000, p.consultarCuenta(2), Instant.parse("2020-01-04T20:00:00Z"), "Sample movement");
        p.consultarCuenta(2).transferirDinero(500, p.consultarCuenta(3), Instant.parse("2020-01-05T20:00:00Z"), "Pagos diversos de matriculas");
        p.consultarCuenta(3).registrarEgreso(250, Instant.parse("2020-01-06T20:00:00Z"), "Pagos diversos de matriculas");
        p.cerrarCuenta(1);
        p.consultarCuenta(2).setCuenta(new Cuenta(null,"Mi vecino",0,tc.getByName("INGRESO")));
        for(var v: p.getCuentasView()) System.out.println(v);
        
        p.generarEstadoContable(Instant.parse("2020-01-02T20:00:00Z"), Instant.parse("2020-01-05T20:00:00Z"));
        p.generarEstadoContable(Instant.parse("2020-01-04T20:00:00Z"), Instant.parse("2020-01-08T20:00:00Z"));
        
        for(var v: p.getEstadosContablesView()) System.out.println(v);
        
    }
}
