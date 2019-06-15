/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.ItemPedido;
import com.ufpr.tads.dac.model.Roupa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "novoPedido")
@ViewScoped
public class NovoPedidoMB implements Serializable {

    private List<ItemPedido> itens;
    private Date prazo;
    private double total;
    private List<Roupa> roupas;
    private int qtd;
    private String idRoupa;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Roupa> getRoupas() {
        return roupas;
    }

    public void setRoupas(List<Roupa> roupas) {
        this.roupas = roupas;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(String idRoupa) {
        this.idRoupa = idRoupa;
    }

    @PostConstruct
    public void init() {
        itens = new ArrayList<>();
        roupas = SystemFacade.getListaRoupas();
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Alterado");//, ((ItemPedido) event.getObject()).getRoupa().getTipo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Removido");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        itens.remove((ItemPedido) event.getObject());
    }

    public void adicionar() {
        // Add one new car to the table:
        ItemPedido i = new ItemPedido();
        i.setRoupa(roupas.get(Integer.parseInt(idRoupa)-1));
        i.setQtd(qtd);
        itens.add(i);
        FacesMessage msg = new FacesMessage("Roupa adicionada", i.getRoupa().getTipo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
