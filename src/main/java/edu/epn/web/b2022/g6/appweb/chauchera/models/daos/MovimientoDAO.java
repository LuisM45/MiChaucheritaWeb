package edu.epn.web.b2022.g6.appweb.chauchera.models.daos;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import java.time.Instant;
import java.util.Collection;

public interface MovimientoDAO extends DAO<Movimiento, Integer>{
    public Collection<Movimiento> getByDateRange(Instant startDate, Instant endDate);
}
