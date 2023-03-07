package edu.epn.web.b2022.g6.appweb.chauchera.rest;

import MiscTools.StaticUtils;
import edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON.ParserJSONProxy;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.EstadoContable;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.time.LocalDate;
import java.util.Collection;
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
@Path("/estados")
public class ControladorEstadoContables extends Application { 
    
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstado(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("start_date") String startDate,
            @FormParam("end_date") String endDate
    ){
        if(username==null || password==null || startDate==null || endDate == null)
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();

        final LocalDate fstartDate = StaticUtils.tryParse(LocalDate::parse,startDate);
        final LocalDate  fendDate = StaticUtils.tryParse(LocalDate::parse,endDate);
        
        if(fstartDate==null || fendDate == null)
            return Response.status(400).entity("Wrong parameters").build();
        
        EstadoContable ec =new EstadoContable(0,p, fstartDate, fendDate);
        ec.setEstadoContable();
        
        return Response.status(200).entity(ParserJSONProxy.parseEstadoContableBase(ec)).build();
    }
    
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEstado(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("start_date") String startDate,
            @FormParam("end_date") String endDate
    ){
        if(username==null || password==null || startDate==null || endDate == null)
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();

        final LocalDate fstartDate = StaticUtils.tryParse(LocalDate::parse,startDate);
        final LocalDate  fendDate = StaticUtils.tryParse(LocalDate::parse,endDate);
        
        if(fstartDate==null || fendDate == null)
            return Response.status(400).entity("Wrong parameters").build();
        
        EstadoContable ec =p.generarEstadoContable(fstartDate, fendDate);
        ec.setEstadoContable();
        DaoFactory.getDaoFactory().getEstadoContableDAO().create(ec);
        
        
        return Response.status(200).entity(ParserJSONProxy.parseEstadoContableBase(ec)).build();
    }
    
    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEstado(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("id") Integer id
    ){
        if(username==null || password==null || id==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        EstadoContable estadoContable = p.consultarEstadoContable(id);
        if(null==estadoContable)
            return Response.status(401).entity("Incorrect id").build();
        
        p.eliminarEstadoContable(estadoContable.getId());
        
        DaoFactory.getDaoFactory().getEstadoContableDAO().delete(estadoContable.getId());
        return Response.status(200).entity("SUCCESS").build();
    }
}
