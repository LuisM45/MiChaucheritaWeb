/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luism
 */
public class PersonaJSONParser implements GenericJSONParser<Persona>{

    @Override
    public Map<String, Object> topParse(Persona e) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("nombre", e.getNombre());
        return map;
    }

    @Override
    public Map<String, Object> baseParse(Persona e) {
        Map<String,Object> map = topParse(e);
        map.put("cuentas", e.getCuentasView()
                .stream()
                .map(ParserJSONProxy::parseCuentaTop)
                .toList());
        map.put("estados_contables", e.getEstadosContablesView()
                .stream()
                .map(ParserJSONProxy::parseEstadoContableTop)
                .toList());
        return map;
    }
    
}
