package edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.*;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import java.time.Instant;
import java.util.Collection;

public class JPAMovimientoDAO extends JPAGeneric<Movimiento, Integer> implements MovimientoDAO{

    @Override
    public Collection<Movimiento> getByDateRange(Instant startDate, Instant endDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
