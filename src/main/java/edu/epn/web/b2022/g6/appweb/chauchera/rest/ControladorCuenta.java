package edu.epn.web.b2022.g6.appweb.chauchera.rest;

import edu.epn.web.b2022.g6.appweb.chauchera.controllers.*;
import com.sun.net.httpserver.HttpServer;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author luism
 */
public class ControladorCuenta extends HttpServlet { 
    
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
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = (Persona) getUser(request, response);
        if(user==null) return;
        
        String action = request.getParameter("action");
        if(action==null) action="list";
        
        switch (action) {
            case "create": crearCuenta(request, response);
                break;
            case "update": actualizarCuenta(request, response);
                break;
            case "delete": eliminarCuenta(request, response);
                break;
            case "list":
            default: listarCuenta(request, response);
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
    
    
    private void listarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Persona user = getUser(request, response);
        user.getCuentasView().forEach(t->t.setValorTotal());
        request.setAttribute("user", user);
        Map<String,List<Cuenta>> cuentasPorTipo = user.getCuentasView()
                .stream()
                .collect(Collectors.groupingBy(t->t.getTipoCuenta().getNombre()));
        
        request.setAttribute("cuentasIngreso", cuentasPorTipo.get("INGRESO"));
        request.setAttribute("cuentasEgreso", cuentasPorTipo.get("EGRESO"));
        request.setAttribute("cuentasIngresoEgreso", cuentasPorTipo.get("INGRESO_EGRESO"));
        request.getRequestDispatcher("jsp/ListarCuentasVW.jsp").forward(request, response);
    }
    
    
    
    private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        if(name==null || type==null){
            request.getRequestDispatcher("jsp/AbrirCuentaVW.jsp").forward(request, response);
            return;
        }
        
        Persona user =  getUser(request, response);
        user.abrirCuenta(new Cuenta(name,DaoFactory.getDaoFactory().getTipoCuentaDAO().getByName(type)));
        DaoFactory.getDaoFactory().getPersonaDAO().update(user);
        response.sendRedirect("cuentas");
        
    }
    
    private void actualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Integer id = null;
        if(request.getParameter("id")!=null) id=Integer.valueOf(request.getParameter("id"));
        
        String name = request.getParameter("name");
        if(name==null || id==null){
            Persona user = getUser(request, response);
            request.setAttribute("cuenta", user.consultarCuenta(id));
            request.getRequestDispatcher("jsp/ActualizarCuentaVW.jsp").forward(request, response);
            return;
        }
        
        Persona user =  getUser(request, response);
        Cuenta cuenta = user.consultarCuenta(id);
        cuenta.setNombre(name);
        DaoFactory.getDaoFactory().getCuentaDAO().update(cuenta);
        response.sendRedirect("cuentas");
        
    }
    
    private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Integer id = null;
        if(request.getParameter("id")!=null) id=Integer.valueOf(request.getParameter("id"));
        if(id==null){
            Persona user = getUser(request, response);
            request.setAttribute("cuentas", user.getCuentasView());
            request.getRequestDispatcher("jsp/CerrarCuentaVW.jsp").forward(request, response);
            return;
        }
        
        Persona user =  getUser(request, response);
        Cuenta cuenta = user.consultarCuenta(id);
        
        DaoFactory.getDaoFactory().getCuentaDAO().delete(id);
        user.cerrarCuenta(id);
        response.sendRedirect("cuentas");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
