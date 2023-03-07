/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import java.util.HashMap;
import java.util.Map;

public class MovimientoJSONParser implements GenericJSONParser<Movimiento>{

    @Override
    public Map<String, Object> topParse(Movimiento e) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("concepto", e.getConcepto());
        map.put("fecha", e.getFecha().toString());
        map.put("valor", (Double)e.getValor());
        return map;
    }

    @Override
    public Map<String, Object> baseParse(Movimiento e) {
        Map<String,Object> map = topParse(e);
        map.put("cuenta_generadora", ParserJSONProxy.parseCuentaTop(e.getCuentaGeneradora()));
        map.put("cuenta_receptora", ParserJSONProxy.parseCuentaTop(e.getCuentaReceptora()));
        map.put("duenio", ParserJSONProxy.parsePersonaTop(e.getDuenio()));
        return map;
    }
    
}
