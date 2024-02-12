package com.caelum.livraria.bean;

import com.caelum.livraria.dao.AutorDao;
import com.caelum.livraria.modelo.Autor;
import com.caelum.livraria.tx.Transacional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AutorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Autor autor = new Autor();

    private Integer autorId;

    @Inject
    private AutorDao dao;

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public void carregarAutorPelaId() {
        this.autor = this.dao.buscaPorId(autorId);
    }

    @Transacional
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

    @Transacional
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
