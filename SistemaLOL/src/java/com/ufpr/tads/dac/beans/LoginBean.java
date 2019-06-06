/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Funcionario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

    private String usuario = "";
    private String senha = "";
    private String test = "teste";
    private Funcionario funcionario = new Funcionario();
    private Cliente cliente = new Cliente();

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }
    
    public String getTeste() {
        return test;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String autenticar() {
        Object obj = SystemFacade.autenticar(usuario, senha);
        if (obj instanceof Funcionario) {
            funcionario = (Funcionario) obj;
            return "pedidos_func";
        } else if (obj instanceof Cliente) {
            cliente = (Cliente) obj;
            return "pedidos_cli";
        }
        return "index";
    }
}
