package com.caelum.livraria.util;

import com.caelum.livraria.modelo.Usuario;

import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class Autorizador implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent evento) {
        FacesContext context = evento.getFacesContext();
        String nomePagina = context.getViewRoot().getViewId();
        System.out.println(nomePagina);

        if ("/login.xhtml".equals(nomePagina)) {
            return;
        }

        Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

        if (usuarioLogado != null) {
            return;
        }

        // redirecionamento para login.xhtml
        NavigationHandler handler = context.getApplication().getNavigationHandler();
        handler.handleNavigation(context, null, "/login?faces-redirect=true");
        context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
