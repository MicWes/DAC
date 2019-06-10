/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name="tb_roupa")
@SequenceGenerator(name="sequencia_roupa", sequenceName="tb_cliente_ro_id_seq")
public class Roupa implements Serializable {
    @Id
    @Column(name="ro_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequencia_roupa")
    private int id;
    @Column(name="ro_tipo")
    private String tipo;
    @Temporal(TemporalType.TIME)
    @Column(name="ro_prazo")
    private Date prazo;
    @Column(name="ro_valor")
    private double valor;

    public Roupa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
