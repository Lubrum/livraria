package com.caelum.livraria.bean;

import com.caelum.livraria.dao.UsuarioDao;
import com.caelum.livraria.modelo.Usuario;
import org.springframework.stereotype.Controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;

@Controller
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDao dao;

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("fazendo login do usuario "
				+ this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
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
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
}
