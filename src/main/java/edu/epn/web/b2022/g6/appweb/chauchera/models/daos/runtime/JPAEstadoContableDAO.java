package edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.EstadoContable;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.*;
import java.util.Collection;

public class JPAEstadoContableDAO extends JPAGeneric<EstadoContable, Integer>implements EstadoContableDAO{

    public JPAEstadoContableDAO() {
        super(EstadoContable.class);
    }
}
