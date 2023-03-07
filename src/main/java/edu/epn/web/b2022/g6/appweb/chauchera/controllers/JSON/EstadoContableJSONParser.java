/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import edu.epn.web.b2022.g6.appweb.chauchera.models.EstadoContable;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luism
 */
public class EstadoContableJSONParser implements GenericJSONParser<EstadoContable>{

    @Override
    public Map<String, Object> topParse(EstadoContable e) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("egresos_totales", e.getEgresosTotales());
        map.put("ingresos_totales", e.getIngresosTotales());
        map.put("fecha_fin", e.getFechaFin().toString());
        map.put("fecha_inicio", e.getFechaInicio().toString());
        return map;
    }

    @Override
    public Map<String, Object> baseParse(EstadoContable e) {
        Map<String,Object> map = topParse(e);
        map.put("movimientos", e.getmovimientosRegistradosPorCuentaView()
                .values()
                .stream()
                .flatMap(t->t.stream())
                .distinct()
                .map(ParserJSONProxy::parseMovimientoTop)
                .toList());
        map.put("duenio", ParserJSONProxy.parsePersonaTop(e.getPersonaDuenia()));
        return map;
    }
    
}
