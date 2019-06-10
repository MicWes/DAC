/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.utils;

import com.ufpr.tads.dac.beans.LoginMB;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Marcos
 */
public class Autenticador implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        if ("/index.xhtml".equals(context.getViewRoot().getViewId())) {
            return;
        }
        if ("/cadastro.xhtml".equals(context.getViewRoot().getViewId())) {
            return;
        }
        if ("/pedidos_cli.xhtml".equals(context.getViewRoot().getViewId())) {
            return;
        }
        if ("/visualizar_pedidos.xhtml".equals(context.getViewRoot().getViewId())) {
            return;
        }
        if ("/novo_pedido.xhtml".equals(context.getViewRoot().getViewId())) {
            return;
        }

        LoginMB lmb = context.getApplication().
                evaluateExpressionGet(context, "#{login}", LoginMB.class);
        if (!lmb.isLogado()) {
            NavigationHandler handler = context.getApplication().
                    getNavigationHandler();
            handler.handleNavigation(context, null, "login?faces-redirect=true");
            context.renderResponse();
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
