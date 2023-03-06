package edu.epn.web.b2022.g6.appweb.chauchera.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class Hello extends Application{
 
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String message) {
        String output = "Hello " + message + "!";
        return Response.status(200).entity(output).build();
    }
}