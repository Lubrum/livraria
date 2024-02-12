package com.caelum.livraria.bean;

import com.caelum.livraria.dao.UsuarioDao;
import com.caelum.livraria.modelo.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    UsuarioDao dao;
    @Inject
    FacesContext context;
    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public String efetuaLogin() {
        System.out.println("fazendo login do usuario "
            + this.usuario.getEmail());

        boolean existe = dao.existe(this.usuario);
        if (existe) {
            context.getExternalContext().getSessionMap()
                .put("usuarioLogado", this.usuario);
            return "livro?faces-redirect=true";
        }

        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Usuário não encontrado"));

        return "login?faces-redirect=true";
    }

    public String deslogar() {
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "login?faces-redirect=true";
    }
}
