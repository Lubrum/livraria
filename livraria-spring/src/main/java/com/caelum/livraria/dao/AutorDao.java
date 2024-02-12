package com.caelum.livraria.dao;

import com.caelum.livraria.modelo.Autor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository
public class AutorDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    EntityManager em;

    private DAO<Autor> dao;

    @PostConstruct
    void init() {
        this.dao = new DAO<>(this.em, Autor.class);
    }

    public Autor buscaPorId(Integer autorId) {
        return this.dao.buscaPorId(autorId);
    }

    public void adiciona(Autor autor) {
        this.dao.adiciona(autor);
    }

    public void atualiza(Autor autor) {
        this.dao.atualiza(autor);
    }

    public void remove(Autor autor) {
        this.dao.remove(autor);
    }

    public List<Autor> listaTodos() {
        return this.dao.listaTodos();
    }

}