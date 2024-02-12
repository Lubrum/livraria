package com.caelum.livraria.dao;

import com.caelum.livraria.modelo.Autor;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless //EJB
public class AutorDao implements Serializable {

    @PersistenceContext
    EntityManager em;

    private DAO<Autor> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<>(this.em, Autor.class);
    }

    public void adiciona(Autor t) {
        dao.adiciona(t);
    }

    public void remove(Autor t) {
        dao.remove(t);
    }

    public void atualiza(Autor t) {
        dao.atualiza(t);
    }

    public List<Autor> listaTodos() {
        return dao.listaTodos();
    }

    public Autor buscaPorId(Integer id) {
        return dao.buscaPorId(id);
    }

}
