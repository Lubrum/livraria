package com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.caelum.livraria.dao.AutorDao;
import com.caelum.livraria.modelo.Autor;

@Named
@ViewScoped
// javax.faces.view.ViewScoped
public class AutorBean implements Serializable {

	private Autor autor = new Autor();

	@Inject
	private AutorDao dao;// CDI faz new AutorDao() e injeta

	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	@Transactional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	@Transactional
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		this.dao.remove(autor);
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
