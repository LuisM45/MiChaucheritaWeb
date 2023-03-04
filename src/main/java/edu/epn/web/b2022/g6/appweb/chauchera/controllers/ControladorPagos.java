/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.controllers;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Movimiento;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.TipoCuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import MiscTools.StaticUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luism
 */
@WebServlet(name = "ControladorPagos", urlPatterns = {"/pagos"})
public class ControladorPagos extends HttpServlet {

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
    
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = (Persona) getUser(request, response);
        if(user==null) return;
        
        String action = request.getParameter("action");
        Integer id_source = StaticUtils.tryParse(Integer::valueOf, request.getParameter("id_source"));
        
        if(action==null||id_source==null){
            response.sendRedirect("cuentas");
            return;
        }
        
        request.setAttribute("account_source", user.consultarCuenta(id_source));
        
        switch (action) {
            case "earning" -> registrarIngreso(request, response,user);
            case "transfer" -> transferirDinero(request, response,user);
            case "spending" -> registrarEgreso(request, response,user);
            default -> response.sendRedirect("cuentas");
        }
        
    }

    private void registrarIngreso(HttpServletRequest request, HttpServletResponse response, Persona user) throws IOException, ServletException {
        Integer idSource = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id_source"));
        Integer idRecipient = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id_recipient"));
        Double value = StaticUtils.tryParse(Double::valueOf,request.getParameter("value"));
        String concept = request.getParameter("concept");
        Instant date = StaticUtils.tryParse(StaticUtils::parseDateStringToInstant,request.getParameter("date"));
        
        if(idRecipient==null || value==null || concept==null || date==null){
            TipoCuenta tipoIngreso = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("INGRESO");
            List<Cuenta> potentialRecipients = user.getCuentasView().stream()
                .filter((t) -> !t.getTipoCuenta().equals(tipoIngreso))
                .toList();
            
            request.setAttribute("sourceCuenta", user.consultarCuenta(idSource));
            request.setAttribute("potentialRecipients", potentialRecipients);
            request.getRequestDispatcher("jsp/RegistrarIngresoVW.jsp").forward(request, response);
            return;
        }
        
        Cuenta earningCuenta = user.consultarCuenta(idSource);
        Cuenta otherCuenta = user.consultarCuenta(idRecipient);
        
        Movimiento m =earningCuenta.registrarIngreso(value, otherCuenta, date, concept);
        DaoFactory.getDaoFactory().getMovimientoDAO().create(m);
                
        response.sendRedirect("cuentas");
    }

    private void transferirDinero(HttpServletRequest request, HttpServletResponse response, Persona user) throws IOException, ServletException {
        Integer idSource = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id_source"));
        Integer idRecipient = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id_recipient"));
        Double value = StaticUtils.tryParse(Double::valueOf,request.getParameter("value"));
        String concept = request.getParameter("concept");
        Instant date = StaticUtils.tryParse(StaticUtils::parseDateStringToInstant,request.getParameter("date"));
        
        
        
        if(idRecipient==null || value==null || concept==null || date==null){
            TipoCuenta tipoIngreso = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("INGRESO");
            List<Cuenta> potentialRecipients = user.getCuentasView().stream()
                .filter((t) -> t.getTipoCuenta().equals(DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("INGRESO_EGRESO")))
                .toList(); 
            
            request.setAttribute("sourceCuenta", user.consultarCuenta(idSource));
            request.setAttribute("potentialRecipients", potentialRecipients);
            request.getRequestDispatcher("jsp/TransferirVW.jsp").forward(request, response);
            return;
        }
        
        Cuenta earningCuenta = user.consultarCuenta(idSource);
        Cuenta otherCuenta = user.consultarCuenta(idRecipient);
        
        Movimiento m =earningCuenta.transferirDinero(value, otherCuenta, date, concept);
        DaoFactory.getDaoFactory().getMovimientoDAO().create(m);
        
        response.sendRedirect("cuentas");
    }

    private void registrarEgreso(HttpServletRequest request, HttpServletResponse response, Persona user) throws IOException, ServletException {
        Integer idSource = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id_source"));
        Integer idRecipient = StaticUtils.tryParse(Integer::valueOf,request.getParameter("id_recipient"));
        Double value = StaticUtils.tryParse(Double::valueOf,request.getParameter("value"));
        String concept = request.getParameter("concept");
        Instant date = StaticUtils.tryParse(StaticUtils::parseDateStringToInstant,request.getParameter("date"));
        
        if(idRecipient==null || value==null || concept==null || date==null){
            TipoCuenta tipoIngreso = DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("INGRESO");
            List<Cuenta> potentialRecipients = user.getCuentasView().stream()
                .filter((t) -> !t.getTipoCuenta().equals(DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName("EGRESO")))
                .toList();
            
            request.setAttribute("sourceCuenta", user.consultarCuenta(idSource));
            request.setAttribute("potentialRecipients", potentialRecipients);
            request.getRequestDispatcher("jsp/RegistrarEgresoVW.jsp").forward(request, response);
            return;
        }
        
        Cuenta earningCuenta = user.consultarCuenta(idSource);
        Cuenta otherCuenta = user.consultarCuenta(idRecipient);
        
        Movimiento m =earningCuenta.registrarEgreso(value, otherCuenta, date, concept);
        DaoFactory.getDaoFactory().getMovimientoDAO().create(m);
        
        response.sendRedirect("cuentas");
    }
}
