package edu.epn.web.b2022.g6.appweb.chauchera.models.daos;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime.JpaDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory instance;
    
    public static DaoFactory getDaoFactory(){
        if(instance==null) instance = new JpaDaoFactory();
        return instance;
    }
    
    public abstract CuentaDAO getCuentaDAO();
    public abstract EstadoContableDAO getEstadoContableDAO();
    public abstract MovimientoDAO getMovimientoDAO();
    public abstract PersonaDAO getPersonaDAO();
    public abstract TipoCuentaDAO getTipoCuentaDAO();
}
