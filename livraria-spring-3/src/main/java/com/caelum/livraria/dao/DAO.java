package com.caelum.livraria.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private final EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(T t) {

		// persiste o objeto
		em.persist(t);

	}

	public void remove(T t) {

		em.remove(em.merge(t));

	}

	public void atualiza(T t) {

		em.merge(t);

	}

	public List<T> listaTodos() {

		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

        return em.createQuery(query).getResultList();
	}

	public T buscaPorId(Integer id) {

		T instancia = em.find(classe, id);

		return instancia;
	}

	public int contaTodos() {

		long result = (Long) em.createQuery("select count(n) from livro n")
				.getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {

		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

        return em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
	}

}
