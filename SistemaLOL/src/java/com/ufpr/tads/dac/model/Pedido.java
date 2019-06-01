/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
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
@Table(name="tb_pedido")
@SequenceGenerator(name="sequencia_pedido", sequenceName="tb_cliente_pe_numpedido_seq")
public class Pedido implements Serializable {
    @Id
    @Column(name="pe_numpedido")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequencia_pedido")
    private int numPedido;
    @Transient 
    private Map<Roupa, Integer> itens = new HashMap();
    @Column(name="pe_qtd_itens")
    private int qtd_itens;
    @Column(name="pe_status")
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pe_prazo")
    private Date prazo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pe_datapedido")
    private Date horaPedido;
    @Column(name="pe_cliente")
    private int idCli;
    
    public Pedido() {
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Map<Roupa, Integer> getItens() {
        return itens;
    }

    public void setItens(Map<Roupa, Integer> itens) {
        this.itens = itens;
        this.qtd_itens = itens.size();
    }
    
    public int getQtdItens() {
        return qtd_itens;
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
        
}
