/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Roupa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "roupaMB")
@ViewScoped
public class RoupaMB implements Serializable {

    private List<Roupa> roupas;
    private Roupa roupa;
    private String valor;
    private String prazo;

    public List<Roupa> getRoupas() {
        return roupas;
    }

    public void setRoupas(List<Roupa> roupas) {
        this.roupas = roupas;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    @PostConstruct
    public void init() {
        roupas = SystemFacade.getListaRoupas();
        roupa = new Roupa();
    }

    public void salvar() {
        roupa.setValor(Double.parseDouble(valor.replace("R$ ", "")));
        roupa.setPrazoFromString(prazo);
        SystemFacade.inserir(roupa);
    }
}
