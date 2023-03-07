package edu.epn.web.b2022.g6.appweb.chauchera.rest;

import edu.epn.web.b2022.g6.appweb.chauchera.controllers.JSON.ParserJSONProxy;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
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
@Path("/user")
public class ControladorPersona extends Application { 
    
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        if(username==null || password==null)
            return Response.status(401).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        return Response.status(200).entity(ParserJSONProxy.parsePersonaBase(p)).build();
    }
    
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        if(username==null || password==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = new Persona(username);
        DaoFactory.getDaoFactory().getPersonaDAO().create(p);
        
        
        return Response.status(200).entity(ParserJSONProxy.parsePersonaBase(p)).build();
    }
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        if(username==null || password==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        // p.setPassword(password);
        
        DaoFactory.getDaoFactory().getPersonaDAO().update(p);
        return Response.status(200).entity(ParserJSONProxy.parsePersonaBase(p)).build();
    }
    
    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        if(username==null || password==null)
            return Response.status(400).entity("Missing parameters").build();
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null)
            return Response.status(401).entity("Incorrect credentials").build();
        
        DaoFactory.getDaoFactory().getPersonaDAO().delete(p.getId());
        return Response.status(200).entity("SUCCESS").build();
    }
}
