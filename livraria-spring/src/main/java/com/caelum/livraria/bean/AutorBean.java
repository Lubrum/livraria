package com.caelum.livraria.bean;

import com.caelum.livraria.dao.AutorDao;
import com.caelum.livraria.modelo.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Controller
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
