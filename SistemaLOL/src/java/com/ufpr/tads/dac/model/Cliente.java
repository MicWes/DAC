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

/**
 *
 * @author Marcos
 */

@Entity
@Table(name="tb_cliente")
@SequenceGenerator(name="sequencia_cliente", sequenceName="tb_cliente_cl_id_seq")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequencia_cliente")
    @Column(name="cl_id", updatable = false, nullable = false)
    private int idCli;
    @Column(name="cl_nome")
    private String nome;
    @Column(name="cl_cpf", unique=true)
    private String cpf;
    @Column(name="cl_senha")
    private String senha;
    @Column(name="cl_email", unique=true)
    private String email;
    @Column(name="cl_cidade")
    private int cidade;
    @Column(name="cl_endereco")
    private String endereco;
    @Column(name="cl_sexo")
    private char sexo; 
    @Column (name="cl_telefone")
    private String telefone;

    public Cliente() {
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return String.valueOf(sexo);
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setCidade(int cid) {
        this.cidade = cid;
    }
    
    public int getCidade() {
        return cidade;
    }

}
