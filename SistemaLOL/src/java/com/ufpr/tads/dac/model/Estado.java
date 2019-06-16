/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name="tb_estado")
public class Estado implements Serializable {
    @Id
    @Column(name="es_id")
    private int id;
    @Column(name="es_descricao")
    private String descricao;
    @Column(name="es_sigla")
    private String sigla;
    @OneToMany(mappedBy="estado", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Cidade> cidades;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public List<Cidade> getCidades() {
        return cidades;
    }
    
    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
}
