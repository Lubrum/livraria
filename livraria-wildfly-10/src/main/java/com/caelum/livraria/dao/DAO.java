package com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class DAO<T> implements Serializable {

    private final Class<T> classe;
    private final EntityManager em;

    public DAO(EntityManager manager, Class<T> classe) {
        this.em = manager;
        this.classe = classe;
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
        return em.find(classe, id);
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
