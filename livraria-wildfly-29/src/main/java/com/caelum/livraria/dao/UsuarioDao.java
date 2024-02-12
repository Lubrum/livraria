package com.caelum.livraria.dao;

import com.caelum.livraria.modelo.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;

@Stateless
public class UsuarioDao implements Serializable {

    @PersistenceContext
    EntityManager manager;

    public boolean existe(Usuario usuario) {

        TypedQuery<Usuario> query = manager.createQuery(
            " select u from Usuario u "
                + " where u.email = :pEmail and u.senha = :pSenha",
            Usuario.class);

        query.setParameter("pEmail", usuario.getEmail());
        query.setParameter("pSenha", usuario.getSenha());
        try {
            Usuario resultado = query.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }

        return true;
    }

}
