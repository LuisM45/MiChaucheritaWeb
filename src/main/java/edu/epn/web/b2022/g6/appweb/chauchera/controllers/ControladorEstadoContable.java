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
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
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
        
        String action = StaticUtils.tryParse(Object::toString,request.getParameter("action"),"quick_list");
        
        switch (action) {
            case "query": consultarEstadoContable(request, response);
                break;
            case "generate": generarEstadoContable(request, response);
                break;
            case "delete": eliminarEstadoContable(request, response);
                break;
            case "list":
                listarEstadosContables(request, response);
                break;
            case "quick_list":
            default:
                listarVelozEstadosContables(request, response);
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

    private void listarVelozEstadosContables(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = getUser(request, response);
        if(user == null) return;
        
        Collection<Movimiento> movimientos = user.getCuentasEgreso().stream().flatMap(t->t.getMovimientosGeneradosView().stream()).toList();
        String thisMes = LocalDate.now().toString().substring(0,7);
        request.setAttribute("startMes", thisMes);
        request.setAttribute("endMes", thisMes);
        
        LocalDate startDate = StaticUtils.tryParse(t->LocalDate.parse(t+"-01"),request.getParameter("start_date"));
        LocalDate endDate = StaticUtils.tryParse(t->LocalDate.parse(t+"-01").minusDays(1).plusMonths(1),request.getParameter("end_date"));
        
        
        if(startDate!=null && endDate!=null){
            request.setAttribute("startMes", startDate.toString().substring(0,7));
            request.setAttribute("endMes", endDate.toString().substring(0,7));
            movimientos = user.getMovimientosVeloz(startDate, endDate);
        }
        
        request.setAttribute("movimientos", movimientos);
        request.getRequestDispatcher("jsp/QuickConsultarEstadoContableVW.jsp").forward(request, response);
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
    
    private void consultarEstadoContable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = getUser(request, response);
        LocalDate now= LocalDate.now();
        LocalDate low = now.minusDays(now.getDayOfMonth()-1);
        LocalDate high = now.minusDays(now.getDayOfMonth()).plusMonths(1);
        
        Collection<Movimiento> movimientos = user.getMovimientosVeloz(low, high);
        TipoCuenta ingreso = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("INGRESO");
        TipoCuenta egreso = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("EGRESO");
        
        
        Double ingresos = movimientos
                .stream()
                .filter(t->t.getCuentaGeneradora().getTipoCuenta().equals(ingreso))
                .mapToDouble(t->t.getValor())
                .sum();
        Double egresos = movimientos
                .stream()
                .filter(t->t.getCuentaReceptora().getTipoCuenta().equals(egreso))
                .mapToDouble(t->t.getValor())
                .sum();
        
        Double ingresosEgresos = ingresos-egresos;
        request.setAttribute("fecha", now.toString().substring(0, 7));
        request.setAttribute("movimientos", movimientos);
        request.setAttribute("ingresos", ingresos);
        request.setAttribute("egresos", egresos);
        request.setAttribute("ingresosEgresos", ingresosEgresos);
        
        request.getRequestDispatcher("jsp/ConsultarEstadoContableVW.jsp").forward(request, response);
    }
    
    private void consultarEstadoContableOld(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Persona user = getUser(request, response);
        Integer id = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id"));
        
        if(id==null){
            response.sendRedirect("estado_contable");
            return;
        }
        
        EstadoContable estado = user.consultarEstadoContable(id);
        List<Movimiento> movimientos = estado.getmovimientosRegistradosPorCuentaView()
                .values()
                .stream()
                .flatMap(t->t.stream())
                .sorted((t1,t2)->t1.getFecha().compareTo(t2.getFecha()))
                .toList();
        
        user.getEstadosContablesView().forEach(t->t.setEstadoContable());//Tiene que actualizarse y no se porque. Whatevs
        request.setAttribute("estado", estado);
        request.setAttribute("movimientos", movimientos);
        request.getRequestDispatcher("jsp/ConsultarEstadoContableVW.jsp").forward(request, response);
    }
    
    private void generarEstadoContable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = getUser(request, response);
        LocalDate startDate = StaticUtils.tryParse(LocalDate::parse,request.getParameter("start_date"));
        LocalDate endDate = StaticUtils.tryParse(LocalDate::parse,request.getParameter("end_date"));
        
        if(startDate ==null || endDate == null){
            request.getRequestDispatcher("jsp/GenerarEstadoContableVW.jsp").forward(request, response);
            return;
        }
        
        EstadoContable estadoContable = user.generarEstadoContable(startDate, endDate);
        DaoFactory.getDaoFactory().getEstadoContableDAO().create(estadoContable);
        
        response.sendRedirect("estado_contable");

    }
    
    private void eliminarEstadoContable(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Persona user = getUser(request, response);
        Integer id = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id"));
        
        if(id==null){
            response.sendRedirect("estado_contable");
            return;
        }
        
        DaoFactory.getDaoFactory().getEstadoContableDAO().delete(id);
        user.eliminarEstadoContable(id);
        
        listarEstadosContables(request, response);
    }
}
