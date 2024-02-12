package com.caelum.livraria.dao;

import com.caelum.livraria.modelo.Livro;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class LivroDao implements Serializable {

    @PersistenceContext
    EntityManager manager;

    private DAO<Livro> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<>(this.manager, Livro.class);
    }

    public void adiciona(Livro t) {
        dao.adiciona(t);
    }

    public void remove(Livro t) {
        dao.remove(t);
    }

    public void atualiza(Livro t) {
        dao.atualiza(t);
    }

    public List<Livro> listaTodos() {
        return dao.listaTodos();
    }

    public Livro buscaPorId(Integer id) {
        return dao.buscaPorId(id);
    }

}
