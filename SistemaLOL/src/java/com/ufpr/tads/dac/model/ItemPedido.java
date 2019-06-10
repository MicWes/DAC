/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name="tb_pedido_roupa")
@SequenceGenerator(name="sequencia_pr", sequenceName="tb_pedido_roupa_pr_id_seq")
public class ItemPedido implements Serializable {
    @Id
    @Column(name="pr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequencia_pr")
    private int id;
    @Column(name="pr_roupa")
    private int idRoupa;
    @Column(name="pr_pedido")
    private int idPedido;
    @Column(name="pr_qtd")
    private int qtd;
    @Transient
    private Roupa roupa;

    public ItemPedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(int idRoupa) {
        this.idRoupa = idRoupa;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }
    
}
