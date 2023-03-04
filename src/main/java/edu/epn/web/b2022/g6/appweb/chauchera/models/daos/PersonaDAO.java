package edu.epn.web.b2022.g6.appweb.chauchera.models.daos;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;

public interface PersonaDAO extends GenericDAO<Persona, Integer>{
    public Persona getByCredentials(String username, String password);
}
