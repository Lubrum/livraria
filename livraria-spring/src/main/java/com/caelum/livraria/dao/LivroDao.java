package com.caelum.livraria.dao;

import com.caelum.livraria.modelo.Livro;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository
public class LivroDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<>(this.em, Livro.class);
	}

	public Livro buscaPorId(Integer livroId) {
		return this.dao.buscaPorId(livroId);
	}

	public void adiciona(Livro livro) {
		this.dao.adiciona(livro);
	}

	public void atualiza(Livro livro) {
		this.dao.atualiza(livro);
	}

	public void remove(Livro livro) {
		this.dao.remove(livro);
	}

	public List<Livro> listaTodos() {
		return this.dao.listaTodos();
	}

}