package com.caelum.livraria.bean;

import com.caelum.livraria.dao.AutorDao;
import com.caelum.livraria.dao.LivroDao;
import com.caelum.livraria.modelo.Autor;
import com.caelum.livraria.modelo.Livro;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LivroBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    LivroDao livroDao;
    @Inject
    AutorDao autorDao;
    @Inject
    FacesContext context;
    private Livro livro = new Livro();
    private Integer autorId;
    private List<Livro> livros;

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Livro getLivro() {
        return livro;
    }

    public List<Livro> getLivros() {
        if (this.livros == null) {
            this.livros = livroDao.listaTodos();
        }
        return livros;
    }

    public List<Autor> getAutores() {
        return autorDao.listaTodos();
    }

    public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutores();
    }

    public void carregarLivroPelaId() {
        this.livro = livroDao.buscaPorId(this.livro.getId());
    }

    public void gravarAutor() {
        Autor autor = autorDao.buscaPorId(this.autorId);
        this.livro.adicionaAutor(autor);
        System.out.println("Escrito por: " + autor.getNome());
    }

    // begin
    @Transactional
    public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            context.addMessage("autor", new FacesMessage(
                "Livro deve ter pelo menos um Autor."));
            return;
        }

        if (this.livro.getId() == null) {
            livroDao.adiciona(this.livro);
            this.livros = livroDao.listaTodos();
        } else {
            livroDao.atualiza(this.livro);
        }

        this.livro = new Livro();
    }

    // commit

    @Transactional
    public void remover(Livro livro) {
        System.out.println("Removendo livro");
        livroDao.remove(livro);
        this.livros = livroDao.listaTodos();
    }

    public void removerAutorDoLivro(Autor autor) {
        this.livro.removeAutor(autor);
    }

    public void carregar(Livro livro) {
        System.out.println("Carregando livro");
        this.livro = this.livroDao.buscaPorId(livro.getId());
    }

    public String formAutor() {
        System.out.println("Chamanda do formulário do Autor.");
        return "autor?faces-redirect=true";
    }

    public void comecaComDigitoUm(FacesContext fc, UIComponent component,
                                  Object value) throws ValidatorException {

        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new ValidatorException(new FacesMessage(
                "ISBN deveria começar com 1"));
        }

    }
}
