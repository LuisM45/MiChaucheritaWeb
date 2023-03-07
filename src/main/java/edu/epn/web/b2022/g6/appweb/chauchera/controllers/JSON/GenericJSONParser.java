package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import java.util.Map;

/**
 *
 * @author luism
 */
public interface GenericJSONParser<E>{
    public Map<String,Object> topParse(E e);
    public default Map<String,Object> baseParse(E e){
        return topParse(e);
    }
}
