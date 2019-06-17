/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name = "tb_pedido")
@SequenceGenerator(name = "sequencia_pedido", sequenceName = "tb_pedido_pe_numpedido_seq")
public class Pedido implements Serializable {

    @Id
    @Column(name = "pe_numpedido")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia_pedido")
    private int id;
    @Transient
    private Map<Roupa, Integer> itens = new HashMap();
    @Transient
    private List<ItemPedido> itensPedido;
    @Column(name = "pe_qtd_itens")
    private int qtdItens;
    @Column(name = "pe_status")
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pe_prazo")
    private Date prazo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pe_datapedido")
    private Date horaPedido;
    @Column(name = "pe_cliente")
    private int idCli;
    @Column(name = "pe_preco")
    private double total;
    @Transient
    private String endereco;

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Roupa, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<Roupa, Integer> itens) {
        this.itens = itens;
        this.qtdItens = itens.values().stream().reduce(0, Integer::sum);
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtd) {
        this.qtdItens = qtd;
    }

    public String getStatus() {
        Status st = Status.fromInt(status);
        return st.toString();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
