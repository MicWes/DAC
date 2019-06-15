/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.PedidoFacade;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "verPedido")
@ViewScoped
public class VerPedidoMB {

    private int idPedido;
    private Pedido pedido;
    private Map<Roupa, Integer> map;
    private ArrayList<Roupa> keys = new ArrayList<>();

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Map<Roupa, Integer> getMap() {
        return map;
    }
    
    public ArrayList<Roupa> getKeys() {
        return keys;
    }

    private void myKeys() {
        for (Roupa key : map.keySet()) {
            keys.add(key);
        }
    }

    @PostConstruct
    public void init() {
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        idPedido = Integer.parseInt(param);
        pedido = PedidoFacade.getPedido(idPedido);
        map = pedido.getItens();
        myKeys();
    }
}
