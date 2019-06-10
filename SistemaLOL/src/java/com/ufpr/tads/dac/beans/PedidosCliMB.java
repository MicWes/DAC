/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.PedidoFacade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Status;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "pedidoCli")
@ViewScoped
public class PedidosCliMB implements Serializable {

    @ManagedProperty(value="#{login.cliente}")
    private Cliente cli;
    private List<Pedido> pedidos;
    private final Status[] status = Status.values();;
    int teste;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente getCli() {
        return cli;
    }
    
    public Status[] getStatus() {
        return status;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }
    
    public void setTeste(int teste) {
        this.teste = teste;
    }
    
    public int getTeste() {
        return teste;
    }

    @PostConstruct
    public void init() {
        pedidos = PedidoFacade.getPedidosCliente(cli.getId());
    }
    
    public void check() {
        int a = 0;
    }

}
