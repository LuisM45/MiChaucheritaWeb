package test;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.EstadoContable;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.CuentaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.TipoCuentaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JPACuentaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JPAPersonaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JPATipoCuentaDAO;
import java.time.Instant;

public class JPAMain {
    private static TipoCuentaDAO tipoCuentaDAO = new JPATipoCuentaDAO();
    private static PersonaDAO personaDAO = new JPAPersonaDAO();
    private static CuentaDAO cuentaDAO = new JPACuentaDAO();

    public static void initTiposCuenta(){
        TipoCuenta tci = new TipoCuenta("INGRESO");
        TipoCuenta tcie = new TipoCuenta("INGRESO_EGRESO");
        TipoCuenta tce = new TipoCuenta("EGRESO");
        tipoCuentaDAO.create(tci);
        tipoCuentaDAO.create(tcie);
        tipoCuentaDAO.create(tce);
    }
    
    public static void checkTiposCuenta(){
        for(var v: tipoCuentaDAO.getAll()) System.out.println(v.getNombre());
    }
    
    public static void initPersonas(){
        Persona p1 = new Persona("Pepe");
        Persona p2 = new Persona("Pedro");
        personaDAO.create(p1);
        personaDAO.create(p2);
    }
    
    public static void checkPersonas(){
        for(var v: personaDAO.getAll()) System.out.println(v.getNombre());

    }
    public static void checkPersonaFields(int id){
        Persona p =  personaDAO.get(id);
        for(var v: p.getCuentasView()) System.out.println(v.getNombre());
        for(EstadoContable c: p.getEstadosContablesView())
            System.out.println(c.getFechaInicio().toString()+" to "+c.getFechaFin().toString());

    }
    
    public static void initCuentas(){
        Cuenta c1 = new Cuenta("Nomina", tipoCuentaDAO.getByName("INGRESO"));
        Cuenta c2 = new Cuenta("Banco_P", tipoCuentaDAO.getByName("INGRESO_EGRESO"));
        Cuenta c3 = new Cuenta("Banco_B", tipoCuentaDAO.getByName("INGRESO_EGRESO"));
        Cuenta c4 = new Cuenta("Universidad", tipoCuentaDAO.getByName("EGRESO"));
        Persona p1 = personaDAO.get(1001);
        p1.abrirCuenta(c1);
        p1.abrirCuenta(c2);
        p1.abrirCuenta(c3);
        p1.abrirCuenta(c4);
        cuentaDAO.update(c1);
//        cuentaDAO.update(c2);
//        cuentaDAO.update(c3);
        
    }
    
    public static void checkCuentas(){
        Persona p1 = personaDAO.get(1001);
            for(var v: p1.getCuentasView())
                System.out.println(v.getNombre());
        
    }
    
    public static void initMovimientos(){
        Persona p =  personaDAO.get(1001);
        
        Cuenta nomina = p.consultarCuenta(1001);
        Cuenta banco1 = p.consultarCuenta(1002);
        Cuenta banco2 = p.consultarCuenta(1003);
        Cuenta universidad = p.consultarCuenta(1004);
        
        System.out.println(nomina.getTipoCuenta());
        System.out.println(banco1.getTipoCuenta());
        System.out.println(universidad.getTipoCuenta());
        
        nomina.registrarIngreso(1000,banco1, Instant.parse("2020-01-01T20:00:00Z"),"Nomina enero1" );
        nomina.registrarIngreso(1000,banco1, Instant.parse("2020-01-02T20:00:00Z"),"Nomina enero2" );
        nomina.registrarIngreso(1000,banco1, Instant.parse("2020-01-03T20:00:00Z"),"Nomina enero3" );
        banco1.transferirDinero(1000, banco2, Instant.parse("2020-01-04T20:00:00Z"), "Sample movement");
        banco1.transferirDinero(500, banco2, Instant.parse("2020-01-05T20:00:00Z"), "Sample movement 2");
        universidad.registrarEgreso(250,banco2, Instant.parse("2020-01-06T20:00:00Z"), "Pagos diversos de matriculas");
        
        personaDAO.update(p);
    }
    
    public static void initEstatutos(){
        Persona p =  personaDAO.get(1001);
        
        
        p.generarEstadoContable(Instant.parse("2020-01-02T20:00:00Z"), Instant.parse("2020-01-05T20:00:00Z"));
        p.generarEstadoContable(Instant.parse("2020-01-04T20:00:00Z"), Instant.parse("2020-01-08T20:00:00Z"));
        
        personaDAO.update(p);
        

    }
    
    public static void checkEstatutos(){
        Persona p =  personaDAO.get(1001);
        
        p.getEstadosContablesView().forEach(t->t.setEstadoContable());
        System.out.println(p.getEstadosContablesView().stream().findAny().get().getmovimientosRegistradosPorCuentaView());
        
        for(var v: p.getEstadosContablesView()){
            System.out.println(v.toString());
            System.out.println(v.getIngresosPorCuentaView());
            System.out.println(v.getEgresosPorCuentaView());
        }
            
    }
    
    
        
    public static void main(String[] args) {
//        initTiposCuenta();
//        checkTiposCuenta();
//        initPersonas();
//        checkPersonas();
//        checkPersonaFields(1001);
//        initCuentas();
//           checkCuentas();
            initMovimientos();
            initEstatutos();
            checkEstatutos();
    }
}
