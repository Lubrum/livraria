package com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.caelum.livraria.modelo.Livro;

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
