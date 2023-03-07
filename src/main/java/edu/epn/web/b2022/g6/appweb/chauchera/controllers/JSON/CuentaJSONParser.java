/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import java.util.HashMap;
import java.util.Map;

public class CuentaJSONParser implements GenericJSONParser<Cuenta>{

    @Override
    public Map<String, Object> topParse(Cuenta e) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("nombre", e.getNombre());
        map.put("tipo_cuenta", ParserJSONProxy.parseTipoCuentaTop(e.getTipoCuenta()));
        map.put("valor_total", e.getValorTotal());
        return map;
    }

    @Override
    public Map<String, Object> baseParse(Cuenta e) {
        Map<String,Object> map = topParse(e);
        map.put("duenio", ParserJSONProxy.parsePersonaTop(e.getDuenio()));
        map.put("movimientos_generados", e.getMovimientosGeneradosView()
                .stream()
                .map(ParserJSONProxy::parseMovimientoTop)
                .toList());
        map.put("movimientos_recibidos", e.getMovimientosRecibidosView()
                .stream()
                .map(ParserJSONProxy::parseMovimientoTop)
                .toList());
        return map;
    }
    
}
