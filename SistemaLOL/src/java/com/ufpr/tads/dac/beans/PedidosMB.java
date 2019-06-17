/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.PedidoFacade;
import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Status;
import com.ufpr.tads.dac.utils.Utils;
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
@ManagedBean(name = "pedidoMB")
@ViewScoped
public class PedidosMB implements Serializable {

    @ManagedProperty(value = "#{login.cliente}")
    private Cliente cli;
    private List<Pedido> pedidos;
    private final Status[] status = Status.values();
    private Status newStatus;

    @PostConstruct
    public void init() {
        pedidos = (cli != null) ? PedidoFacade.getPedidosCliente(cli.getId())
                : PedidoFacade.getListaPedidos();
    }

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

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

    public void alterarStatus(Pedido pedido) {
        pedido.setStatus(newStatus.getNum());
        SystemFacade.alterar(pedido);
        Utils.message("Info:", "Status Alterado");
    }

    public void solicitarEntrega(Pedido pedido) {
        PedidoFacade.solicitarEntrega(pedido);
        Utils.message("Info:", "Entrega solicitada");
    }

    public void realizarPagamento(Pedido pedido) {
        pedido.setStatus(Status.AGD_ENT.getNum());
        SystemFacade.alterar(pedido);
        Utils.message("Info:", "Pagamento confirmado!");
    }

    public void cancelarPedido(Pedido pedido) {
        if (!"Aguardando Lavagem".equals(pedido.getStatus())) {
            Utils.message("Info", "Esse pedido não pode mais ser cancelado");
        } else {
            pedido.setStatus(Status.CANCEL.getNum());
            SystemFacade.alterar(pedido);
            Utils.message("Info:", "Pedido excluído com sucesso!");
        }
    }

}
