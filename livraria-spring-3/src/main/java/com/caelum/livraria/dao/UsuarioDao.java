package com.caelum.livraria.dao;

import com.caelum.livraria.modelo.Usuario;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;

@Repository
public class UsuarioDao implements Serializable {

	@PersistenceContext
	EntityManager em;

	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = em.createQuery(
				" select u from Usuario u where u.email = :pEmail and u.senha = :pSenha",
				Usuario.class
		);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado =  query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		return true;
	}
}