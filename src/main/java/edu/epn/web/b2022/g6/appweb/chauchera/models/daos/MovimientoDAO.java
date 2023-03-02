package edu.epn.web.b2022.g6.appweb.chauchera.models.daos;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import java.time.Instant;
import java.util.Collection;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer>{
    
    /**
     * Este método devuelve una Colección de movimientos filtrados por fecha/tiempo.
     * 
     * @param startDate Tiempo desde el cual incluir los movimientos (Inclusivo)
     * @param endDate Tiempo hasta el cual incluir los movimientos (Inclusivo)
     * @return 
     */
    public Collection<Movimiento> getByDateRange(Instant startDate, Instant endDate);
}
