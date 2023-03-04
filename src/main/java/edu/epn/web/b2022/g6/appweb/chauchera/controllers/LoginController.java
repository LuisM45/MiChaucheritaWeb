package edu.epn.web.b2022.g6.appweb.chauchera.controllers;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/LoginVW.jsp").forward(request, response);;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username==null || password==null){
            doGet(request, response);
            return;
        }
        
        Persona p = DaoFactory.getDaoFactory().getPersonaDAO().getByCredentials(username, password);
        if(p==null){
            doGet(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", p);
        response.sendRedirect("cuentas");
        
    }

    

}
