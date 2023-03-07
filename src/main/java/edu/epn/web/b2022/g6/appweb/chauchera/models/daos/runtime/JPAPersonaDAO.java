package edu.epn.web.b2022.g6.appweb.chauchera.models.daos.runtime;

import edu.epn.web.b2022.g6.appweb.chauchera.models.Cuenta;
import edu.epn.web.b2022.g6.appweb.chauchera.models.daos.*;
import edu.epn.web.b2022.g6.appweb.chauchera.models.Persona;
import java.util.Collection;

public class JPAPersonaDAO extends JPAGeneric<Persona, Integer> implements PersonaDAO{
    
    public JPAPersonaDAO() {
        super(Persona.class);
    }
    @Override
    public Collection<Persona> getAll() {
        Collection<Persona> personas = super.getAll();
        personas.stream()
                .flatMap(t->t.getCuentasView().stream())
                .forEach(c->c.setValorTotal());
        return personas;
    }

    @Override
    public Persona get(Integer key) {
        Persona p = super.get(key);
        p.getCuentasView().forEach(c->c.setValorTotal());
        return p;
    }
    
    @Override
    public Persona getByCredentials(String username, String password) {
        String jpql = "SELECT p FROM Persona p WHERE p.nombre=:username";
        Persona persona = (Persona) eManager.createQuery(jpql)
                .setParameter("username", username)
                .getResultList().stream()
                .findAny().orElse(null);
        persona.getCuentasView().forEach(c->c.setValorTotal());
        return persona;
    }
    
}
