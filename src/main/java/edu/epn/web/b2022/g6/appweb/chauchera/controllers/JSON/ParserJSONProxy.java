package edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.EstadoContable;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import java.util.HashMap;
import java.util.Map;

public class ParserJSONProxy {
    private static ParserJSONProxy parser;
    
    private PersonaJSONParser personaJSONParser;
    private CuentaJSONParser cuentaJSONParser;
    private MovimientoJSONParser movimientoJSONParser;
    private EstadoContableJSONParser estadoContableJSONParser;
    private TipoCuentaJSONParser tipoCuentaJSONParser;
    
    
    private static ParserJSONProxy getParser(){
        if(parser==null) parser = new ParserJSONProxy();
        return parser;
    }
    
    private ParserJSONProxy() {
        personaJSONParser = new PersonaJSONParser();
        cuentaJSONParser = new CuentaJSONParser();
        movimientoJSONParser = new MovimientoJSONParser();
        estadoContableJSONParser = new EstadoContableJSONParser();
        tipoCuentaJSONParser = new TipoCuentaJSONParser();
    }
    
    
    public static Map<String,Object> parsePersonaBase(Persona p){
        return getParser().personaJSONParser.baseParse(p);
    }
    
    public static Map<String,Object> parsePersonaTop(Persona p){
        return getParser().personaJSONParser.topParse(p);
    }
    
    public static Map<String,Object> parseCuentaBase(Cuenta c){
        return getParser().cuentaJSONParser.baseParse(c);
    }
    
    public static Map<String,Object> parseCuentaTop(Cuenta c){
        return getParser().cuentaJSONParser.topParse(c);
    }
    
    public static Map<String,Object> parseMovimientoBase(Movimiento c){
        return getParser().movimientoJSONParser.baseParse(c);
    }
    
    public static Map<String,Object> parseMovimientoTop(Movimiento c){
        return getParser().movimientoJSONParser.topParse(c);
    }
    
    public static Map<String,Object> parseTipoCuentaBase(TipoCuenta c){
        return getParser().tipoCuentaJSONParser.baseParse(c);
    }
    
    public static Map<String,Object> parseTipoCuentaTop(TipoCuenta c){
        return getParser().tipoCuentaJSONParser.topParse(c);
    }
    
    public static Map<String,Object> parseEstadoContableBase(EstadoContable c){
        return getParser().estadoContableJSONParser.baseParse(c);
    }
    
    public static Map<String,Object> parseEstadoContableTop(EstadoContable c){
        return getParser().estadoContableJSONParser.topParse(c);
    }
    
}
