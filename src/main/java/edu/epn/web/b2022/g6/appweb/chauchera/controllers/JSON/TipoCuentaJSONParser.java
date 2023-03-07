/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luism
 */
public class TipoCuentaJSONParser implements GenericJSONParser<TipoCuenta>{

    @Override
    public Map<String, Object> topParse(TipoCuenta e) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("nombre", e.getNombre());
        return map;
    }

    @Override
    public Map<String, Object> baseParse(TipoCuenta e) {
        Map<String,Object> map = topParse(e);
        return map;
    }
    
}
