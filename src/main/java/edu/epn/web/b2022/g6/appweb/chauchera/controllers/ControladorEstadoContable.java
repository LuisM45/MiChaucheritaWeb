/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers;

import MiscTools.StaticUtils;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.EstadoContable;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "ControladorEstadoContable", urlPatterns = {"/estado_contable"})
public class ControladorEstadoContable extends HttpServlet {

    private Persona getUser(HttpServletRequest request, HttpServletResponse response){
        try {
            Persona user = (Persona) request.getSession().getAttribute("user");
            if(user==null) response.sendRedirect("login");
            return user;
        } catch (IOException ex) {
            Logger.getLogger(ControladorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Persona user = (Persona) getUser(request, response);
        if(user==null) return;
        
        String action = StaticUtils.tryParse(Object::toString,request.getParameter("action"),"list");
        
        switch (action) {
            case "query": consultarEstadoContable(request, response);
                break;
            case "generate": generarEstadoContable(request, response);
                break;
            case "delete": eliminarEstadoContable(request, response);
                break;
            case "list":
            default:
                listarEstadosContables(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void listarEstadosContables(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = getUser(request, response);
        user.getEstadosContablesView().forEach(t->t.setEstadoContable());//Tiene que actualizarse y no se porque. Whatevs
        
                BiFunction<Cuenta,Collection<Movimiento>,Double> sumarizer = (cuenta,movimientos)->{
                double egresos = movimientos.stream()
                        .filter(m->m.getCuentaGeneradora().equals(cuenta))
                        .mapToDouble(t->t.getValor())
                        .sum();
                double ingresos = movimientos.stream()
                        .filter(m->m.getCuentaReceptora().equals(cuenta))
                        .mapToDouble(t->t.getValor())
                        .sum();
                return ingresos-egresos;
        };
        
        Map<String,List<Cuenta>> cuentasByTipo = user.getCuentasView().stream()
            .collect(Collectors.groupingBy(c->c.getTipoCuenta().getNombre()));
        
        Map<EstadoContable,Map<Cuenta,Double>> valorByCuentaByEstadoContable = new HashMap<>();
        for(EstadoContable ec: user.getEstadosContablesView()){
            Map<Cuenta,Double>  valorByCuenta = ec.getmovimientosRegistradosPorCuentaView()
                    .entrySet().stream()
                    .map(t->Map.entry(t.getKey(),sumarizer.apply(t.getKey(), t.getValue())))
                    .collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
            
            valorByCuentaByEstadoContable.put(ec, valorByCuenta);
        }
        
        user.getEstadosContablesView().forEach(t->t.setEstadoContable());//Tiene que actualizarse y no se porque. Whatevs
        
        request.setAttribute("cuentasByTipo", cuentasByTipo);
        request.setAttribute("valorByCuentaByEstadoContable", valorByCuentaByEstadoContable);
        
        request.getRequestDispatcher("jsp/ListarEstadoContableVW.jsp").forward(request, response);
    }
    
    private void consultarEstadoContable(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Persona user = getUser(request, response);
        Integer id = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id"));
        
        if(id==null){
            response.sendRedirect("estado_contable");
            return;
        }
        
        
        BiFunction<Cuenta,Collection<Movimiento>,Double> sumarizer = (cuenta,movimientos)->{
                double egresos = movimientos.stream()
                        .filter(m->m.getCuentaGeneradora().equals(cuenta))
                        .mapToDouble(t->t.getValor())
                        .sum();
                double ingresos = movimientos.stream()
                        .filter(m->m.getCuentaReceptora().equals(cuenta))
                        .mapToDouble(t->t.getValor())
                        .sum();
                return ingresos-egresos;
        };
        
        Map<String,List<Cuenta>> cuentasByTipo = user.getCuentasView().stream()
            .collect(Collectors.groupingBy(c->c.getTipoCuenta().getNombre()));
        
        Map<EstadoContable,Map<Cuenta,Double>> valorByCuentaByEstadoContable = new HashMap<>();
        for(EstadoContable ec: user.getEstadosContablesView()){
            Map<Cuenta,Double>  valorByCuenta = ec.getmovimientosRegistradosPorCuentaView()
                    .entrySet().stream()
                    .map(t->Map.entry(t.getKey(),sumarizer.apply(t.getKey(), t.getValue())))
                    .collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
            

        }
        
        user.getEstadosContablesView().forEach(t->t.setEstadoContable());//Tiene que actualizarse y no se porque. Whatevs
        request.setAttribute("cuentasByTipo", cuentasByTipo);
        request.setAttribute("user", user);
        request.setAttribute("valorByCuentaByEstadoContable", valorByCuentaByEstadoContable);
        request.getRequestDispatcher("jsp/ConsultarEstadoContableVW.jsp").forward(request, response);
    }
    
    private void generarEstadoContable(HttpServletRequest request, HttpServletResponse response){
        
    }
    
    private void eliminarEstadoContable(HttpServletRequest request, HttpServletResponse response){
    }
}
