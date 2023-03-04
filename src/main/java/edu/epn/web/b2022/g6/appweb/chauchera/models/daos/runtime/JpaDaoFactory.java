/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime;

import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.CuentaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.DaoFactory;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.EstadoContableDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.MovimientoDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.PersonaDAO;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.TipoCuentaDAO;

/**
 *
 * @author luism
 */
public class JpaDaoFactory extends DaoFactory{
    private JPACuentaDAO cuentaDAO;
    private JPAEstadoContableDAO estadoContableDAO;
    private JPAMovimientoDAO movimientoDAO;
    private JPAPersonaDAO personaDAO;
    private JPATipoCuentaDAO tipoCuentaDAO;

    public JpaDaoFactory() {
        cuentaDAO = new JPACuentaDAO();
        estadoContableDAO = new JPAEstadoContableDAO();
        movimientoDAO = new JPAMovimientoDAO();
        personaDAO = new JPAPersonaDAO();
        tipoCuentaDAO = new JPATipoCuentaDAO();
    }
    
    @Override
    public CuentaDAO getCuentaDAO() {
        return cuentaDAO;
    }

    @Override
    public EstadoContableDAO getEstadoContableDAO() {
        return estadoContableDAO;
    }

    @Override
    public MovimientoDAO getMovimientoDAO() {
        return movimientoDAO;
    }

    @Override
    public PersonaDAO getPersonaDAO() {
        return personaDAO;
    }

    @Override
    public TipoCuentaDAO getTipoCuentaDAO() {
        return tipoCuentaDAO;
    }
    
}
