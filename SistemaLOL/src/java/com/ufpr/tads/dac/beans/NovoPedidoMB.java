/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.PedidoFacade;
import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.ItemPedido;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import com.ufpr.tads.dac.model.Status;
import com.ufpr.tads.dac.utils.Utils;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "novoPedido")
@ViewScoped
public class NovoPedidoMB implements Serializable {

    @ManagedProperty(value = "#{login.cliente}")
    private Cliente cli;
    private List<ItemPedido> itens;
    private double total;
    private Map<String, String> roupas;
    private Map<String, Roupa> roupaSelect;
    private int qtd;
    private String idRoupa;
    private Date prazo;

    @PostConstruct
    public void init() {
        itens = new ArrayList<>();
        roupas = new HashMap<>();
        roupaSelect = new HashMap<>();
        List<Roupa> rps = SystemFacade.getListaRoupas();
        for (Roupa r : rps) {
            roupas.put(r.getTipo(), r.getTipo());
            roupaSelect.put(r.getTipo(), r);
        }
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Date getPrazo() {
        if (Utils.isAberto()) {
            int newHours = 0;
            int newMin = 0;
            int newSec = 0;
            for (ItemPedido ip : itens) {
                for (int i = 0; i < ip.getQtd(); i++) {
                    newHours += ip.getRoupa().getPrazoLT().getHour();
                    newMin += ip.getRoupa().getPrazoLT().getMinute();
                    newSec += ip.getRoupa().getPrazoLT().getSecond();
                }
            }
            LocalDateTime newPrazo = Utils.addTimeToLDT(newHours, newMin, newSec);
            return Date.from(newPrazo.atZone(ZoneId.systemDefault()).toInstant());
        } else {
            return Utils.horaAbertura();
        }
    }

    public double getTotal() {
        double n = 0;
        qtd = 0;
        for (ItemPedido ip : itens) {
            n += (ip.getQtd() * ip.getRoupa().getValor());
            qtd += ip.getQtd();
        }
        return n;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, String> getRoupas() {
        return roupas;
    }

    public void setRoupas(Map<String, String> roupas) {
        this.roupas = roupas;
    }

    public Map<String, Roupa> getRoupaSelect() {
        return roupaSelect;
    }

    public void setRoupaSelect(Map<String, Roupa> roupaSelect) {
        this.roupaSelect = roupaSelect;
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

    public void delete(ItemPedido i) {
        itens.remove(i);
        Utils.message("Item Removido");
    }

    public void adicionar() {
        ItemPedido i = new ItemPedido();
        i.setRoupa(roupaSelect.get(idRoupa));
        i.setQtd(qtd);
        boolean exists = false;
        String result = "Item adicionado";
        for (ItemPedido ip : itens) {
            if (ip.getIdRoupa() == i.getIdRoupa()) {
                ip.setQtd(qtd + ip.getQtd());
                exists = true;
                result = "Quantidade adicionada a item de mesmo tipo existente";
            }
        }
        if (!exists) {
            itens.add(i);
        }
        Utils.message(result, i.getRoupa().getTipo());
    }

    public String fazerPedido() {
        Pedido p = new Pedido();
        p.setIdCli(cli.getId());
        p.setPrazo(getPrazo());
        p.setStatus(Status.AGD_LAV.getNum());
        p.setTotal(getTotal());
        p.setQtdItens(qtd);
        p.setHoraPedido(Date.from(Instant.now()));
        p.setItensPedido(itens);
        if (PedidoFacade.inserirPedido(p)) {
            Utils.message("Pedido Realizado com Sucesso!");
            return "pedidos";
        } else {
            Utils.message("Erro ao salvar seu pedido");
        }
        return "";
    }
}
