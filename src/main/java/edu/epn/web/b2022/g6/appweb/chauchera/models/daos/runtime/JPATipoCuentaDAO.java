package edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.*;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import java.util.Collection;

public class JPATipoCuentaDAO extends JPAGeneric<TipoCuenta, Integer>implements TipoCuentaDAO{
    
    public JPATipoCuentaDAO() {
        super(TipoCuenta.class);
    }

    @Override
    public TipoCuenta getByName(String name) {
        String jpql = "SELECT t FROM TipoCuenta t WHERE t.nombre=:name";
        return (TipoCuenta) eManager.createQuery(jpql)
                .setParameter("name", name)
                .getResultStream()
                .findAny()
                .orElse(null);
    }
    
}
