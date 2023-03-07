package edu.epn.web.b2022.g6.appweb.chauchera.rest;

import MiscTools.StaticUtils;
import edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON.ParserJSONProxy;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.time.LocalDate;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author luism
 */
@Path("/movimiento")
public class ControladorMovimientos extends Application { 
    
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovimiento(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("cuenta_id") Integer cuentaId,
            @FormParam("id") Integer id
    ){
        if(StaticUtils.anyNull(username,password,cuentaId,id))
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        Cuenta cuenta = p.consultarCuenta(id);
        Movimiento m = StaticUtils.tryParse(cuenta::getMovimientos,id);
        
        if(cuenta ==null || m == null)
            return Response.status(400).entity("Incorrect ids").build();
        
        return Response.status(200).entity(ParserJSONProxy.parseMovimientoBase(m)).build();
    }
    
    @POST
    @Path("/ingreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMovimientoIngreso(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("origin_id") Integer originId,
            @FormParam("recipient_id") Integer recipientId,
            @FormParam("concept") String concepto,
            @FormParam("date") String date,
            @FormParam("value") Integer value
    ){
        if(StaticUtils.anyNull(username,password,originId,recipientId,date,value))
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        LocalDate fdate = StaticUtils.tryParse(LocalDate::parse,date);
        Cuenta origen = p.consultarCuenta(originId);
        Cuenta destino = p.consultarCuenta(recipientId);
        
        if(StaticUtils.anyNull(fdate,origen,destino))
            return Response.status(400).entity("Wrong parameters").build();
        
        Movimiento m = origen.registrarIngreso(value, destino, fdate, concepto);
        
        return Response.status(200).entity(ParserJSONProxy.parseMovimientoBase(m)).build();
    }
    
    @POST
    @Path("/egreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMovimientoEgreso(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("origin_id") Integer originId,
            @FormParam("recipient_id") Integer recipientId,
            @FormParam("concept") String concepto,
            @FormParam("date") String date,
            @FormParam("value") Integer value
    ){
        if(StaticUtils.anyNull(username,password,originId,recipientId,date,value))
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        LocalDate fdate = StaticUtils.tryParse(LocalDate::parse,date);
        Cuenta origen = p.consultarCuenta(originId);
        Cuenta destino = p.consultarCuenta(recipientId);
        
        if(StaticUtils.anyNull(fdate,origen,destino))
            return Response.status(400).entity("Wrong parameters").build();
        
        Movimiento m = destino.registrarEgreso(value, origen, fdate, concepto);
        
        return Response.status(200).entity(ParserJSONProxy.parseMovimientoBase(m)).build();
    }
    
    @POST
    @Path("/tranferencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMovimientoTransferencia(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("origin_id") Integer originId,
            @FormParam("recipient_id") Integer recipientId,
            @FormParam("concept") String concepto,
            @FormParam("date") String date,
            @FormParam("value") Integer value
    ){
        if(StaticUtils.anyNull(username,password,originId,recipientId,date,value))
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        LocalDate fdate = StaticUtils.tryParse(LocalDate::parse,date);
        Cuenta origen = p.consultarCuenta(originId);
        Cuenta destino = p.consultarCuenta(recipientId);
        
        if(StaticUtils.anyNull(fdate,origen,destino))
            return Response.status(400).entity("Wrong parameters").build();
        
        Movimiento m = origen.transferirDinero(value, destino, fdate, concepto);
        
        return Response.status(200).entity(ParserJSONProxy.parseMovimientoBase(m)).build();
    }
    
}
