package edu.epn.web.b2022.g6.appweb.chauchera.rest;

import edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON.ParserJSONProxy;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
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
@Path("/cuenta")
public class ControladorCuenta extends Application { 
    
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCuenta(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("q") String query,
            @FormParam("type") String type
    ){
        if(username==null || password==null)
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();

        final String ftype = type==null?"":type;
        final String fquery = query==null?"":query;
        Collection<Cuenta> cuentas= switch(ftype){
            case "id"-> p.getCuentasView().stream().filter(t->t.getId().toString().equals(fquery)).toList();
            case "name"->p.getCuentasView().stream().filter(t->t.getNombre().toLowerCase().contains(fquery.toLowerCase())).toList();
            default-> p.getCuentasView();
        };
        
        return Response.status(200).entity(cuentas.stream().map(ParserJSONProxy::parseCuentaBase).toList()).build();
    }
    
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCuenta(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("name") String name,
            @FormParam("type") String type
    ){
        if(username==null || password==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        TipoCuenta tipoCuenta = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName(type);
        if(tipoCuenta==null)
            return Response.status(400).entity("Wrong parameters").build();
        
        Cuenta cuenta = new Cuenta(name, tipoCuenta);
        p.abrirCuenta(cuenta);
        DaoFactory.getDaoFactory().getCuentaDAO().create(cuenta);
        
        
        return Response.status(200).entity(ParserJSONProxy.parseCuentaBase(cuenta)).build();
    }
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putCuenta(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("id") Integer id,
            @FormParam("name") String name
    ){
        if(username==null || password==null || id==null || name ==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        Cuenta cuenta = p.consultarCuenta(id);
        if(null==cuenta)
            return Response.status(401).entity("Incorrect id").build();
        
        cuenta.setNombre(name);
        // p.setPassword(password);
        
        DaoFactory.getDaoFactory().getCuentaDAO().update(cuenta);
        return Response.status(200).entity(ParserJSONProxy.parseCuentaBase(cuenta)).build();
    }
    
    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("id") Integer id
    ){
        if(username==null || password==null || id==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        Cuenta cuenta = p.consultarCuenta(id);
        if(null==cuenta)
            return Response.status(401).entity("Incorrect id").build();
        
        p.cerrarCuenta(cuenta.getId());
        
        DaoFactory.getDaoFactory().getCuentaDAO().delete(cuenta.getId());
        return Response.status(200).entity("SUCCESS").build();
    }
}
